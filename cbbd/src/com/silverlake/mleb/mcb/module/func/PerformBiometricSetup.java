package com.silverlake.mleb.mcb.module.func;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.RequestData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;

/**
 * Purpose: Activate or deactivate the biometric
 * 
 * Omni Web Services:
 * user/biometric_setup
 * 
 * @author Alex Loo
 */
@Service
public class PerformBiometricSetup extends SessionFuncServices<ObAuthenticationRequest, ObAuthenticationResponse>{
	private static Logger log = LogManager.getLogger(PerformBiometricSetup.class);
	
	@Autowired DeviceCIFDao deviceDao;
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObAuthenticationRequest requestBean, ObAuthenticationResponse responseBean, VCGenericService vcService) throws Exception {
		RequestData requestData = new RequestData();
		if(requestBean.getObUser() != null) {
			requestData.setOrg_cd(requestBean.getObUser().getOrgId());
			requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		}else {
			requestData.setOrg_cd(requestBean.getAccountNumber());//org_cd value parked at accountNumber when being called during pre-login
			requestData.setUsr_cd(requestBean.getAccountType());//usr_cd value parked at accountType when being called during pre-login
		}
		requestData.setDevice_id(requestBean.getObDevice().getDeviceId());
		requestData.setDevice_type(requestBean.getObDevice().getModel());
		requestData.setDevice_os(requestBean.getObDevice().getType()+" "+requestBean.getObDevice().getOs());
		requestData.setAction_cd(requestBean.getLoginType());
		if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			requestData.setLang("en");
		}
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			requestData.setLang("cn");
		}
		else {
			requestData.setLang("id");
		}
		if(requestBean.getEnrollNonce() != null && !requestBean.getEnrollNonce().isEmpty()) {
			if(requestBean.getEnrollNonce().equalsIgnoreCase(MiBConstant.MIB_BIOMETRIC_TYPE_FINGERPRINT)) {
				requestData.setBiometric_type(MiBConstant.MIB_BIOMETRIC_TYPE_FINGERPRINT);
			}else if(requestBean.getEnrollNonce().equalsIgnoreCase(MiBConstant.MIB_BIOMETRIC_TYPE_TOUCHID)) {
				requestData.setBiometric_type(MiBConstant.MIB_BIOMETRIC_TYPE_TOUCHID);
			}else if(requestBean.getEnrollNonce().equalsIgnoreCase(MiBConstant.MIB_BIOMETRIC_TYPE_FACEID)) {
				requestData.setBiometric_type(MiBConstant.MIB_BIOMETRIC_TYPE_FACEID);
			}else {
				log.error("Invalid biometric type "+requestBean.getEnrollNonce());
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				return;
			}
		}else {
			requestData.setBiometric_type(MiBConstant.MIB_BIOMETRIC_TYPE_FINGERPRINT);//Default to finger print
		}
		
		VCResponse<com.silverlake.mleb.mcb.module.vc.user.ResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_BIOMETRIC_SETUP,
				requestData,
				ResponseData.class,
				true);
		
		if(!processVCResponseError(vcResponse, responseBean, false)) {
			//Update biometric status in device_profile
			String deviceId = requestBean.getObDevice().getDeviceId();
			DeviceProfile devProfile = deviceDao.getCIFByDeviceIDAndStatus(deviceId);
			if(devProfile != null) {
				devProfile.setBiometricDateUpdated(new Date());
				if(requestBean.getLoginType().equalsIgnoreCase("D") || requestBean.getLoginType().equalsIgnoreCase("DR")){
					devProfile.setBiometricStatus("D");//D - DEACTIVATED
				}else {
					//Store corresponding biometric type into status column
					//Later this data will be used to determine which biometric type to put for deactivate biometric_setup at device unbinding ws. 
					devProfile.setBiometricStatus(requestData.getBiometric_type());
				}
				deviceDao.updateEntity(devProfile);
			}
		}
	}
}
