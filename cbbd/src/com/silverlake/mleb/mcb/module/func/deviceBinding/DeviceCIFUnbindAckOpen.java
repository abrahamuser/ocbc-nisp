package com.silverlake.mleb.mcb.module.func.deviceBinding;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.common.MCBServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.user.RequestData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;






@Service
public class DeviceCIFUnbindAckOpen extends FuncServices  
{

	private static Logger log = LogManager.getLogger(DeviceCIFUnbindAckOpen.class);


	@Autowired
	CustomerDAO dao;

	@Autowired
	DeviceCIFDao custDao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;


	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub



		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse devResponse = new ObAuthenticationResponse();
		devResponse.setObHeader(new ObHeaderResponse());
		devResponse.setUserContext(new ObUserContext());

		try {


			MCBServices mcbServices = new MCBServices();
			ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();	
			//String cif = requestData.getCif();
			String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			DeviceProfile devPro = custDao.getCIFByDeviceID(deviceId);


			if(null!=devPro )
			{ 
				if(requestData.getApprovalUnbindFlag() != null && requestData.getApprovalUnbindFlag().equalsIgnoreCase("Y")) {
					if(devPro.getDeviceLevel() != null && devPro.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY)){
						List<DeviceProfile> bindedDevice = custDao.getDeviceByCIFAndStatus(devPro.getCif());
						if(bindedDevice != null && bindedDevice.size() > 1) {
							//Primary device cannot unbind itself if there's ACTIVE/PENDING Secondary device
							devResponse.getObHeader().setStatusCode(MiBConstant.MCB_UNBIND_PRIMARY_DEVICE);
							devResponse.getObHeader().setId(arg0.getTranxID());
							response.setBDObject(devResponse);
							return response;
						}
					}
				}
				
				CustomerProfile profileDB = dao.getCustomerProfileByCif(devPro.getCif());
				VCService usrService = new VCService(appContext);
				RequestData vcLoginRequest = new RequestData();
				vcLoginRequest.setOrg_cd(profileDB.getOrgId());
				vcLoginRequest.setUsr_cd(profileDB.getLoginId());
				vcLoginRequest.setParam_c(requestData.getcString());
				vcLoginRequest.setParam_p(requestData.getpString());
				vcLoginRequest.setRandom_number(requestData.getRandomNumber());
				vcLoginRequest.setPassword_data_block(requestData.getPasswordDataBlock());
				
				ResponseData vcResponseData = new ResponseData();
				VCResponse<ResponseData> vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.USER_VERIFY_PWD,vcLoginRequest, vcResponseData, null, arg0.getTranxID(), ipAddress);
				
				if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
				{
					DeviceCIFUnbindAck deviceUnbind = new DeviceCIFUnbindAck();
					String deviceOS = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getOs();
					String deviceType = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getType();
					String devicePushToken = requestData.getObHeader()==null?null:requestData.getObHeader().getPnsDeviceToken();
					String deviceModel = null==arg0.getDeviceBean()?null:arg0.getDeviceBean().getModel();
					
					//Unenrolled biometric, if device is enrolled before
					deviceUnbind.unenrolledBiometric(appContext, arg0.getLocaleCode(), profileDB.getOrgId(), profileDB.getLoginId(), deviceId, deviceModel, deviceType, deviceOS, devPro.getBiometricStatus(), requestData.getUserContext().getSessionId(), arg0.getTranxID(), devResponse, ipAddress);
					if(devResponse.getObHeader().getStatusCode() != null) {
						//If error stop processing futher
						devResponse.getObHeader().setId(arg0.getTranxID());
						response.setBDObject(devResponse);
						return response;
					}
					
					VCResponse fingerPrintRS = deviceUnbind.updateSoftwareTokenDelete(appContext, custDao,profileDB.getOrgId(), profileDB.getLoginId(), deviceId, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
					if(fingerPrintRS.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
					{
						mcbServices.unbindDevice(custDao, devPro.getCif(), deviceId, arg0.getTranxID());
						deviceUnbind.sendDeviceInfo(appContext, "D", profileDB.getOrgId(), profileDB.getLoginId(), deviceId, 
								deviceOS, deviceType, deviceModel, devicePushToken, requestData.getUserContext().getSessionId(), arg0.getTranxID(),
								requestData.getUserContext().getLocaleCode(), devPro.getDeviceLevel(), ipAddress);
						
						GeneralCode primaryGnCode = gnDao.findByGnCode(MiBConstant.GNCODE_PRIMARY_DEV_BINDING);
						if(primaryGnCode != null) {
							String desc = primaryGnCode.getGnCodeDescription();
							if(desc != null && desc.equalsIgnoreCase("true")) {
							//normalize device level for old app if device level is available
								if(requestData.getApprovalUnbindFlag() == null || requestData.getApprovalUnbindFlag().isEmpty()) {
									List<DeviceProfile> otherBindedDevice = custDao.getDeviceByCIF(devPro.getCif());
									if(otherBindedDevice != null && otherBindedDevice.size() > 0){
										//Normalize the primary device to one only
										//Old app able to unbind any user regardless of device level
										for(DeviceProfile otherDevice:otherBindedDevice) {
											if(custDao.isDevicePrimaryDevice(otherDevice.getDeviceId(), otherDevice.getCif()) && otherDevice.getDeviceLevel() != null && otherDevice.getDeviceLevel().equals(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY)) {
												otherDevice.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY);
												custDao.updateEntity(otherDevice);
											}
										}
									}
								}
							}
						}
						
						devResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					}
					else
					{
						devResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+fingerPrintRS.getResponse_code() );
						devResponse.getObHeader().setStatusMessage(fingerPrintRS.getResponse_desc());
					}
				
				}
				else
				{
					devResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
					devResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
					
				}
				 

			}
			else
			{
				//unbind rejected, no binded dev found
				devResponse.getObHeader().setStatusCode(MiBConstant.MCB_INVALID_BINDED_DEVICE);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"


			log.info(this.getClass().toString(), e);
			devResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			devResponse.getObHeader().setStatusMessage(e.getMessage());

		}
		devResponse.getObHeader().setId(arg0.getTranxID());
		response.setBDObject(devResponse);

		return response;
	}






}
