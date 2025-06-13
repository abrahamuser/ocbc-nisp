package com.silverlake.mleb.mcb.module.func.registration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationeUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.RegCacheData;
import com.silverlake.mleb.mcb.entity.dao.RegCacheDao;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.registration.InputterDetails;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationCancelRequestData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationCancelResponseData;

/**
 * Purpose: To Cancel Registration
 * Omni Web Services:
 * others/velocity-registration/cancel
 *   
 */
@Service
public class RegistrationCancel extends CacheSessionFuncServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache>{
	private static Logger log = LogManager.getLogger(RegistrationCancel.class);
	
	@Autowired
	RegCacheDao regCacheDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean, ObRegistrationeUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
		RegistrationCancelRequestData requestData = new RegistrationCancelRequestData();
		RegistrationCancelResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<RegistrationCancelResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.REGISTRATION_CANCEL, 
				requestData, 
				RegistrationCancelResponseData.class, 
				true);
		
		if(processVCResponseError(vcResponse, responseBean, false)){
			return;
		}
			
		regCacheDao.deleteByID(RegCacheData.class, requestBean.getRecord_id());	
	}
	
	private void processRequest(String locale, RegistrationCancelRequestData requestData, ObRegistrationRequest requestBean, ObRegistrationResponse responseBean) throws Exception{
		
		String deviceId = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getDeviceId();
		String deviceOS = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getOs();
		String deviceType = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getModel();
		
		requestData.setRole(requestBean.getRole());
		requestData.setRecord_id(requestBean.getRecord_id());
		requestData.setVersion_no(requestBean.getVersion_no());
		requestData.setRegistration_no(requestBean.getRegistration_no());
		requestData.setVerification_no(requestBean.getVerificationNo());
		
		if(requestBean.getRole().equalsIgnoreCase("IN")){
			
			InputterDetails inp = new InputterDetails();
			
			inp.setName(requestBean.getName());
			inp.setKtp_no(requestBean.getKtp_no());
			inp.setAddress(requestBean.getAddress());
			inp.setEmail(requestBean.getEmail());
			inp.setPhone(requestBean.getPhone());
			
			requestData.setUser_details(inp);
			
			}
		
		if(requestBean.getRole().equalsIgnoreCase("SG")){
			
			InputterDetails inp = new InputterDetails();
			
			inp.setName(requestBean.getName());
			inp.setEmail(requestBean.getEmail());
			inp.setKtp_no(requestBean.getKtp_no());
			inp.setNpwp_no(requestBean.getNpwp_no());
			inp.setGender(requestBean.getGender());
			inp.setBirth_place(requestBean.getBirth_date());
			inp.setBirth_date(requestBean.getBirth_date());
			inp.setAddress(requestBean.getAddress());
			inp.setProvince(requestBean.getProvince());
			inp.setCity(requestBean.getCity());
			inp.setPhone(requestBean.getPhone());
			inp.setKtp_file_name(requestBean.getKtp_file_name());
			inp.setKtp_file_ext(requestBean.getKtp_file_ext());
			
			String ktpBase64 = null;
			if(requestBean.getKtpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
				ktpBase64 = new String(requestBean.getKtpPhoto());
				inp.setKtp_photo(ktpBase64);
			}else if(requestBean.getKtpPhoto() != null){
				ktpBase64 = Base64.encode(requestBean.getKtpPhoto());
				inp.setKtp_photo(ktpBase64);
			}
			
			inp.setNpwp_file_name(requestBean.getNpwp_file_name());
			inp.setNpwp_file_ext(requestBean.getNpwp_file_ext());
			
			String npwpBase64 = null;
			if(requestBean.getNpwpPhoto() != null && requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
				npwpBase64 = new String(requestBean.getNpwpPhoto());
				inp.setNpwp_photo(npwpBase64);
			}else if(requestBean.getNpwpPhoto() != null){
				npwpBase64 = Base64.encode(requestBean.getNpwpPhoto());
				inp.setNpwp_photo(npwpBase64);
			}
			
			requestData.setUser_details(inp);
			
		}
		
		requestData.setDevice_id(deviceId);
		requestData.setDevice_type(deviceType);
		requestData.setDevice_os(deviceOS);
		
	}
	
		
	
}
