package com.silverlake.mleb.mcb.module.func.deviceBinding;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
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
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.common.MCBServices;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;
import com.silverlake.mleb.mcb.module.vc.user.List_device;
 






@Service
public class DeviceCIFBindingAckv2 extends FuncServices  
{

	private static Logger log = LogManager.getLogger(DeviceCIFBindingAckv2.class);

	@Autowired
	MLEBMIBDAO dao;

	@Autowired
	DeviceCIFDao custDao;

	@Autowired
	GeneralCodeDAO gnDao;
	
	@Autowired 
	CustomerDAO profileDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {

		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse tagDeviceResponse = new ObAuthenticationResponse();
		tagDeviceResponse.setObHeader(new ObHeaderResponse());
		tagDeviceResponse.setUserContext(new ObUserContext());

		try {
			MCBServices mcbServices = new MCBServices();
			ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();	
			String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			//verify TAC first
			TokenRequestData vcLoginRequest = new TokenRequestData();
			vcLoginRequest.setOrg_cd(requestData.getObUser().getOrgId());
			vcLoginRequest.setUsr_cd(requestData.getObUser().getUserId());
			vcLoginRequest.setDevice_id(deviceId);
			vcLoginRequest.setOtp(requestData.getAuthorizationCode());
			vcLoginRequest.setRequest_id(requestData.getTokenReqId());
			
			TokenResponseData vcResponseData = new TokenResponseData();
			VCService tokenService = new VCService(appContext);
			VCResponse vcResponse = tokenService.callOmniVC(TokenRequestData.method_token_otp_verify,vcLoginRequest, vcResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);

			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				String cif = requestData.getObUser().getCifNumber();
				String deviceID =  null==arg0.getDeviceBean()?null:arg0.getDeviceBean().getDeviceId();
				String deviceType =  null==arg0.getDeviceBean()?"":arg0.getDeviceBean().getType();
				String pnsToken = requestData.getObHeader()==null?null:requestData.getObHeader().getPnsDeviceToken();
				List<DeviceProfile>  extDevList = custDao.getDeviceByCIFAndStatus(cif);
	
				DeviceProfile devPro = custDao.getCIFByDeviceIDAndStatus(deviceID);
	
				GeneralCode gnRs = gnDao.findByGnCode(MiBConstant.MAXDEVICEBINDKEY);
	
				int maxDevBind = gnRs==null?1:Integer.parseInt(gnRs.getGnCodeDescription());
				String UntagDeviceID = requestData.getUntagDeviceID();
				UntagDeviceID = UntagDeviceID==null?null:(UntagDeviceID.trim().length()==0?null:UntagDeviceID);
				
				if(null==devPro && (maxDevBind>extDevList.size() || (UntagDeviceID!=null && maxDevBind>=extDevList.size() ) ))
				{
					HashMap defaultDevType = new HashMap();
					defaultDevType.put("android", "android");
					defaultDevType.put("ios", "ios");
					 
					//check device type - only accept android, iphone, ipad
					if(deviceType==null || (null!=deviceType && !defaultDevType.containsKey(deviceType.toLowerCase())))
					{
						tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MCB_BIND_DEVICE_INVALID_DEVICE_TYPE);
					}
					else
					{
						String deviceModel = null==arg0.getDeviceBean()?null:arg0.getDeviceBean().getModel();
						String isRooted = null==arg0.getDeviceBean()?"":String.valueOf(arg0.getDeviceBean().isRooted());
						String deviceOs = null==arg0.getDeviceBean()?null:arg0.getDeviceBean().getOs();
						
						DeviceCIFUnbindAck deviceUnbind = new DeviceCIFUnbindAck();
						if(UntagDeviceID!=null)
						{
							String untagDeviceOs = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getOs();;
							String untagDeviceType = deviceType;
							String untagDevicePushToken = requestData.getObHeader()==null?null:requestData.getObHeader().getPnsDeviceToken();;
							String untagDeviceModel = deviceModel;
							for(DeviceProfile deviceProfile:extDevList){
								if(deviceProfile.getDeviceId().equals(UntagDeviceID)){
									untagDeviceOs = deviceProfile.getOs();
									untagDeviceType = deviceProfile.getDeviceType();
									untagDevicePushToken = deviceProfile.getPnsToken();
									untagDeviceModel = deviceProfile.getDeviceModel();
									break;
								}
							}
							mcbServices.unbindDevice(custDao, cif,UntagDeviceID,arg0.getTranxID());
							deviceUnbind.sendDeviceInfo(appContext, "D", requestData.getObUser().getOrgId(), requestData.getObUser().getUserId(), 
									UntagDeviceID, untagDeviceOs, untagDeviceType, untagDeviceModel, untagDevicePushToken, requestData.getUserContext().getSessionId(), arg0.getTranxID(),
									requestData.getUserContext().getLocaleCode(), null, ipAddress);
						}
						
						//Generate token for finger print/biometric for FE to encrypt the password
						//This token will store in device_profile table which to be retrieved during retrieveSecToken
						String biometricKey = generateRandomString(8);
						DeviceProfile newDev = new DeviceProfile();

						log.info("new binding process with approval");
						boolean sendDeviceInfo = false;
							
						newDev.setCif(cif);
						newDev.setDeviceId(deviceID);
						newDev.setTransId(arg0.getTranxID());
						newDev.setDateBind(new Date());
						newDev.setDeviceModel(deviceModel);
						newDev.setDeviceType(deviceType);
						newDev.setIsRooted(isRooted);
						newDev.setOs(deviceOs);
						newDev.setBindBy(cif);
						newDev.setPnsToken(pnsToken);
						newDev.setBiometricKey(biometricKey);
							
						if(extDevList == null || extDevList.size() <= 0) {
							//primary device
							sendDeviceInfo = true;
							newDev.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE);
							newDev.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY);
						} else {
							//secondary device
							newDev.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING);
							newDev.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY);
						}
							
						custDao.insertEntity(newDev);
						tagDeviceResponse.setBinded(true);
						tagDeviceResponse.setBindingStatus(newDev.getStatus());
						tagDeviceResponse.setDeviceLevel(newDev.getDeviceLevel());
						
						if(sendDeviceInfo) {
							//send device info to velo in order to send push notification. 
							deviceUnbind.sendDeviceInfo(appContext, "A", requestData.getObUser().getOrgId(), requestData.getObUser().getUserId(), 
									deviceID, deviceOs, deviceType, deviceModel, pnsToken, requestData.getUserContext().getSessionId(), arg0.getTranxID(),
									requestData.getUserContext().getLocaleCode(), newDev.getDeviceLevel(), ipAddress);
						}
						
						// 3.9 sync device (active & pending device)
						List<DeviceProfile> syncList = custDao.getDeviceByCIFAndStatus(cif);
						List<List_device> list_device = new ArrayList<List_device>();
						if(syncList != null){
							SimpleDateFormat sdf = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
							for(DeviceProfile syncDeviceProfile:syncList){
								List_device device = new List_device();
								device.setDevice_id(syncDeviceProfile.getDeviceId());
								device.setDevice_os(syncDeviceProfile.getOs());
								device.setPush_token(syncDeviceProfile.getPnsToken());
								device.setDevice_alias(syncDeviceProfile.getAliasName());
								device.setDevice_type(syncDeviceProfile.getDeviceModel());
								
								if(syncDeviceProfile.getDeviceType()!=null && syncDeviceProfile.getDeviceType().equalsIgnoreCase("android")) {
									device.setDevice_os("Android "+syncDeviceProfile.getOs());
									device.setPush_token_type("GCM");
								} else if(syncDeviceProfile.getDeviceType()!=null && syncDeviceProfile.getDeviceType().equalsIgnoreCase("ios")) {
									device.setPush_token_type("APNS");
								}
								
								if(syncDeviceProfile.getDeviceLevel()!=null && syncDeviceProfile.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY)) {
									device.setIs_primary("Y");
								} else if (syncDeviceProfile.getDeviceLevel()!=null && syncDeviceProfile.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY)) {
									device.setIs_primary("N");
								}
								
								if(syncDeviceProfile.getStatus()!=null && syncDeviceProfile.getStatus().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE)) {
									device.setDevice_status("A");
								} else if (syncDeviceProfile.getStatus()!=null && syncDeviceProfile.getStatus().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING)) {
									device.setDevice_status("P");
								}
								
								device.setBind_date(sdf.format(syncDeviceProfile.getDateBind()));
								CustomerProfile profileDB = profileDao.getCustomerProfileByCif(syncDeviceProfile.getCif());
								device.setLast_login_date(sdf.format(profileDB.getLastLoginDT()));
								list_device.add(device);
							}
						}
						deviceUnbind.syncDevice(appContext, requestData.getObUser().getOrgId(), requestData.getObUser().getUserId(), requestData.getUserContext().getSessionId(), arg0.getTranxID(), list_device, ipAddress);
						
						/*
						 * Push notification to all binded device when new device binded successful.
						 */
						try{
							List<DeviceProfile>  bindedDevList = custDao.getDeviceByCIF(cif);
							for(DeviceProfile devProfile:bindedDevList){
								String title = RetrieveAppStatInfo_mcb.getMessageTable(requestData.getUserContext().getLocaleCode()).get("PUSH_NOTI_TITLE");
								String message = RetrieveAppStatInfo_mcb.getMessageTable(requestData.getUserContext().getLocaleCode()).get("PUSH_NOTI_BOUND_DEVICE");
								message = message.replace("$1", deviceModel);
								
								com.silverlake.mleb.mcb.module.vc.notification.RequestData vcPushNotificationRequest = new com.silverlake.mleb.mcb.module.vc.notification.RequestData();
								com.silverlake.mleb.mcb.module.vc.notification.ResponseData vcPushNotificationResponseData = new com.silverlake.mleb.mcb.module.vc.notification.ResponseData();
								vcPushNotificationRequest.setOrg_cd(requestData.getObUser().getOrgId());
								vcPushNotificationRequest.setUsr_cd(requestData.getObUser().getUserId());
								vcPushNotificationRequest.setDevice_id(devProfile.getDeviceId());
								vcPushNotificationRequest.setSubject(title);
								vcPushNotificationRequest.setMessage(message);
								VCResponse vcPushResponse = tokenService.callOmniVC(VCMethodConstant.REST_METHODS.SEND_PUSH_NOTIFICATION, vcPushNotificationRequest, vcPushNotificationResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
							}
						}catch(Exception e){
							//Don't effect the process result due to fail of push notification only
							log.catching(e);
						}
						tagDeviceResponse.setBiometricKey(biometricKey);
						tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					}
	
				}
				else if(null!=devPro && !devPro.getCif().equalsIgnoreCase(cif))
				{
					//bind rejected, device bind with other cif
					tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MCB_BIND_DEVICE_BINDED);
				}
				else if( null!=devPro && devPro.getCif().equalsIgnoreCase(cif))
				{
					//bind rejected, device is binded to the cif
					tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MCB_BIND_DEVICE_BINDED);
				}
				else if(maxDevBind==extDevList.size())
				{
					//bind rejected, exceed bind limit;
					tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MCB_BIND_DEVICE_EXCEED_LIMIT);
				}
				else if(maxDevBind<extDevList.size())
				{
					//swap rejected, exceed bind limit;
					tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MCB_SWAPDEVICE_OVERLIMIT);
				}
			}
			else
			{
				tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
			}


		} catch (Exception e) {
			log.info(this.getClass().toString(), e);
			tagDeviceResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			tagDeviceResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		tagDeviceResponse.getObHeader().setId(arg0.getTranxID());
		response.setBDObject(tagDeviceResponse);

		return response;
	}


	public void bindDevice(String cif, String deviceID, String trxId, String deviceModel, String deviceType, String isRooted,String deviceOs)
	{
		DeviceProfile newDev = new DeviceProfile();
		newDev.setCif(cif);
		newDev.setDeviceId(deviceID);
		newDev.setTransId(trxId);
		newDev.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE);
		newDev.setDateBind(new Date());
		newDev.setDeviceModel(deviceModel);
		newDev.setDeviceType(deviceType);
		newDev.setIsRooted(isRooted);
		newDev.setOs(deviceOs);
		newDev.setBindBy(cif);
		custDao.insertEntity(newDev);
	}
	
	private String generateRandomString(int length) {
		boolean useLetters = false;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString;
	}
}
