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
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.user.List_device;
import com.silverlake.mleb.mcb.module.vc.user.RequestData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;






@Service
public class DeviceCIFUnbindAck extends FuncServices  
{

	private static Logger log = LogManager.getLogger(DeviceCIFUnbindAck.class);


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
			String deviceID =  requestData.getUntagDeviceID();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			DeviceProfile devPro = custDao.getCIFByDeviceID(deviceID);


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
				VCResponse<ResponseData> vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.USER_VERIFY_PWD,vcLoginRequest, vcResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				
				if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
				{
					String deviceOS = devPro.getOs();
					String deviceType = devPro.getDeviceType();
					String devicePushToken = devPro.getPnsToken();
					String deviceModel = devPro.getDeviceModel();
					
					//Unenrolled biometric, if device is enrolled before
					unenrolledBiometric(appContext, arg0.getLocaleCode(), profileDB.getOrgId(), profileDB.getLoginId(), deviceID, deviceModel, deviceType, deviceOS, devPro.getBiometricStatus(), requestData.getUserContext().getSessionId(), arg0.getTranxID(), devResponse, ipAddress);
					if(devResponse.getObHeader().getStatusCode() != null) {
						//If error stop processing futher
						devResponse.getObHeader().setId(arg0.getTranxID());
						response.setBDObject(devResponse);
						return response;
					}
					
					VCResponse fingerprintRS = updateSoftwareTokenDelete(appContext,custDao,profileDB.getOrgId(), profileDB.getLoginId(), deviceID,requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
					if(fingerprintRS.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
					{
						DeviceProfile unbindDevProfile = mcbServices.unbindDevice(custDao, devPro.getCif(), deviceID, arg0.getTranxID());
						sendDeviceInfo(appContext, "D", profileDB.getOrgId(), profileDB.getLoginId(), deviceID, deviceOS, deviceType, deviceModel, devicePushToken, requestData.getUserContext().getSessionId(), arg0.getTranxID(), requestData.getUserContext().getLocaleCode(), devPro.getDeviceLevel(), ipAddress);
						
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
								
						String currentDeviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
						if(deviceID.equalsIgnoreCase(currentDeviceId))
						{
							// if unbind current device MCB.0000010
							devResponse.getObHeader().setStatusCode(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE);
							
						}
						else
						{
							devResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
						}
					}
					else
					{
						devResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+fingerprintRS.getResponse_code() );
						devResponse.getObHeader().setStatusMessage(fingerprintRS.getResponse_desc());
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


	
	public void sendDeviceInfo(ApplicationContext appContextx, String action, String orgId, String usrId, String deviceId, String deviceOs, String deviceType, String deviceModel, String push_token, String sessionID, String mlebTrxId, String lang, String deviceLevel, String ipAddress) throws Exception
	{
		//action 
		//A: Add/Bind device
		//U: Update (Normal login binded device)
		//D: Delete/Unbind device
		VCService userAsyncService_device = new VCService(appContextx); 
		com.silverlake.mleb.mcb.module.vc.user.RequestData vcDeviceRequest = new com.silverlake.mleb.mcb.module.vc.user.RequestData();
		vcDeviceRequest.setOrg_cd(orgId);
		vcDeviceRequest.setUsr_cd(usrId);
		vcDeviceRequest.setAction_cd(action);
		vcDeviceRequest.setDevice_id(deviceId);
		vcDeviceRequest.setDevice_os(deviceOs);
		//VELO2UAT-134 - switch device type to device model to omni
		vcDeviceRequest.setDevice_type(deviceModel);
		vcDeviceRequest.setPush_token(push_token);
		// if(lang != null && lang.equalsIgnoreCase(MiBConstant.LANG_EN)){
		// 	vcDeviceRequest.setLang(MiBConstant.LANG_EN.toLowerCase());
		// }

		if(lang != null){
			if(lang.equalsIgnoreCase(MiBConstant.LANG_EN)){
				vcDeviceRequest.setLang(MiBConstant.LANG_EN.toLowerCase());
			}else if(lang.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
				vcDeviceRequest.setLang(MiBConstant.LANG_CN.toLowerCase());
			}
		}
		
		if(deviceType!=null && deviceType.equalsIgnoreCase("android"))
		{
			//VELO2UAT-134 - append android in front of device os to omni
			vcDeviceRequest.setDevice_os("Android "+deviceOs);
			vcDeviceRequest.setPush_token_type("GCM");
		}
		else if(deviceType!=null && deviceType.equalsIgnoreCase("ios"))
		{
			vcDeviceRequest.setPush_token_type("APNS");
		}
		
		if(deviceLevel != null && !deviceLevel.isEmpty()) {
			if(deviceLevel.equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY)) {
				vcDeviceRequest.setIs_primary("Y");
			} else {
				vcDeviceRequest.setIs_primary("N");
			}
		}
	 
		userAsyncService_device.callOmniVC(VCMethodConstant.REST_METHODS.USER_DEVICE,vcDeviceRequest,new ResponseData() , sessionID, mlebTrxId, ipAddress);
		
	}
	
	
	
	public VCResponse updateSoftwareTokenDelete(ApplicationContext appContextx, DeviceCIFDao custDaox, String orgId, String usrId, String deviceId, String sessionID, String mlebTrxId, String ipAddress) throws Exception
	{
		
		DeviceProfile device = custDaox.getCIFByDeviceID(deviceId);
		
		if(device.getTokenFingerPrint()!=null && device.getTokenFingerPrint().trim().length()>0)
		{
			VCService vcService = new VCService(appContextx); 
			TokenRequestData requestData = new TokenRequestData();
			requestData.setOrg_cd(orgId);
			requestData.setUsr_cd(usrId);
			requestData.setDevice_id(deviceId);
			requestData.setDevice_finger_print(device.getTokenFingerPrint());
			ResponseData vcResponseData = new ResponseData();
			VCResponse<ResponseData> vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TOKEN_DELETE, 
					requestData, 
					vcResponseData,
					sessionID, mlebTrxId, ipAddress);
			
			
			return vcResponse;
		}
		else
		{
			VCResponse vcResponse = new VCResponse();
			vcResponse.setResponse_code(MiBConstant.OMNI_SUCCESS);
			return vcResponse;
		}
	}
	
	
	public void unenrolledBiometric(ApplicationContext appContextx, String locale, String orgId, String usrId, String deviceId, String deviceModel, String deviceType, String deviceOs, String biometricStatus, String sessionID, String mlebTrxId, ObAuthenticationResponse responseBean, String ipAddress) throws Exception{
		//Only unenrolled when device enrolled before
		if(biometricStatus != null && (biometricStatus.equalsIgnoreCase("A") ||
				biometricStatus.equalsIgnoreCase(MiBConstant.MIB_BIOMETRIC_TYPE_FINGERPRINT) || 
						biometricStatus.equalsIgnoreCase(MiBConstant.MIB_BIOMETRIC_TYPE_FACEID) || 
						biometricStatus.equalsIgnoreCase(MiBConstant.MIB_BIOMETRIC_TYPE_TOUCHID))) {
			RequestData requestData = new RequestData();
			requestData.setOrg_cd(orgId);
			requestData.setUsr_cd(usrId);
			requestData.setDevice_id(deviceId);
			requestData.setDevice_type(deviceModel);
			requestData.setDevice_os(deviceType+" "+deviceOs);
			requestData.setAction_cd("D");

			if(locale != null && locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
				requestData.setLang("en");
			}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
				requestData.setLang("cn");
			}else {
				requestData.setLang("id");
			}
			
			if(biometricStatus.equalsIgnoreCase("A")) {
				requestData.setBiometric_type(MiBConstant.MIB_BIOMETRIC_TYPE_FINGERPRINT);//Default to finger print for old app support where biometric status 'A' is still recorded
			}else {
				requestData.setBiometric_type(biometricStatus);
			}
			
			VCGenericService vcService = new VCGenericService(appContextx, mlebTrxId, sessionID, ipAddress);
			VCResponse<com.silverlake.mleb.mcb.module.vc.user.ResponseData> vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.USER_BIOMETRIC_SETUP,
					requestData,
					ResponseData.class,
					true);
			
			responseBean.getObHeader().setStatusCode(null);
			if(!vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
				responseBean.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
		}
	}

	public void syncDevice(ApplicationContext appContextx, String orgId, String usrId, String sessionID, String mlebTrxId, List<List_device> list_device, String ip) throws Exception
	{
		RequestData vcDeviceRequest = new RequestData();
		vcDeviceRequest.setOrg_cd(orgId);
		vcDeviceRequest.setUsr_cd(usrId);
		vcDeviceRequest.setList_active_device(list_device);
		
		VCService syncDevice = new VCService(appContextx); 
		syncDevice.callOmniVC(VCMethodConstant.REST_METHODS.USER_DEVICE_SYNC, vcDeviceRequest, new ResponseData(), sessionID, mlebTrxId, ip);
	}

}
