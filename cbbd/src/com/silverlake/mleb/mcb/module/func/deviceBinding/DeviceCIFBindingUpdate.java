package com.silverlake.mleb.mcb.module.func.deviceBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObDeviceBindingRequest;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceCifBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.UnBindDeviceProfile;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.user.List_device;
import com.silverlake.mleb.mcb.util.MapperUtil;

@Service
public class DeviceCIFBindingUpdate extends SessionFuncServices<ObDeviceBindingRequest, ObDeviceBindingResponse> {
	private static Logger log = LogManager.getLogger(DeviceCIFBindingUpdate.class);

	@Autowired
	DeviceCIFDao deviceDao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired
	CustomerDAO custDao;
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObDeviceBindingRequest requestBean,
			ObDeviceBindingResponse responseBean, VCGenericService vcService) throws Exception {
		
		String ipAddress = requestBean.getObHeader() == null ? null : requestBean.getObHeader().getIp();
		
		//requestor device id
		if (requestBean.getObDevice() == null || requestBean.getObDevice().getDeviceId() == null || requestBean.getObDevice().getDeviceId().isEmpty()) {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
			log.info("Unable to get the requestor device id");
			return;
		}
		
		//requestor device id
		DeviceProfile requestorDeviceProfile = deviceDao.getCIFByDeviceID(requestBean.getObDevice().getDeviceId());
		if (requestorDeviceProfile == null) {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
			log.info("Unable to get the requestor device profile");
			return;
		}
		
		if(!requestBean.getLoginType().equalsIgnoreCase("SWITCH")) {
			if (requestorDeviceProfile.getDeviceLevel() == null || requestorDeviceProfile.getDeviceLevel().equals(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY)) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_DEVAPI_UNATHORIZED);
				log.info("Requestor is not an primary device");
				return;
			}
		}
		
		//target device id
		DeviceProfile targetDeviceProfile = deviceDao.getDevProfByDeviceIDAndCifAndStatus(requestBean.getDevId(), requestorDeviceProfile.getCif());
		if (targetDeviceProfile == null) {
			log.info("Target device profile not found.");
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
			return;
		}
		
		if (requestBean.getLoginType().equalsIgnoreCase("APPROVE") || requestBean.getLoginType().equalsIgnoreCase("REJECT")) {
			boolean isExpired = isDevicePendingExpired(requestorDeviceProfile.getCif(), trxId, targetDeviceProfile, gnDao, deviceDao);
			
			if(isExpired) {
				log.info("Target device found but expired.");
				
				syncDevice(requestBean.getObUser().getOrgId(), requestBean.getUserContext().getSessionId(), trxId, 
						requestBean.getObUser().getUserId(), ipAddress, requestorDeviceProfile.getCif(), targetDeviceProfile, isExpired);
				
				responseBean.getObHeader().setStatusCode(MiBConstant.MCB_DEVID_PEND_EXPIRED);
				return;
			}
			
			GeneralCode gnRs = gnDao.findByGnCode(MiBConstant.MAXDEVICEBINDKEY);
			int maxLimit = gnRs == null ? 1 : Integer.parseInt(gnRs.getGnCodeDescription());
			List<DeviceProfile> deviceList = deviceDao.getDeviceByCIF(requestorDeviceProfile.getCif());
			if (deviceList != null && deviceList.size() >= maxLimit) {
				log.info("Exceed the max device binding.");
				responseBean.getObHeader().setStatusCode(MiBConstant.MCB_BIND_DEVICE_EXCEED_LIMIT);
				return;
			}
		}
		
		responseBean.setDeviceListing(new ArrayList());
		DeviceCIFUnbindAck devicebind = new DeviceCIFUnbindAck();
		
		if (requestBean.getLoginType().equalsIgnoreCase("APPROVE")) {
			int updatedRecord = deviceDao.updateDeviceStatus(targetDeviceProfile.getCif(), targetDeviceProfile.getDeviceId(), MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE, MiBConstant.MIB_DEVICEPROFILE_STATUS_APPROVE);
			log.info("updaterecord :: "+updatedRecord);
			
			if(updatedRecord == 0) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
				log.info("Unable to update Approve binding");
				return;
			}
			
			ObDeviceCifBean devBean = new ObDeviceCifBean();
			devBean.setDevId(targetDeviceProfile.getDeviceId());
			devBean.setDeviceLevel(targetDeviceProfile.getDeviceLevel());
			devBean.setDeviceStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE);
			devBean.setCif(targetDeviceProfile.getCif());
			responseBean.getDeviceListing().add(devBean);
			
			devicebind.sendDeviceInfo(appContext, "A", requestBean.getObUser().getOrgId(), requestBean.getObUser().getUserId(), 
					targetDeviceProfile.getDeviceId(), targetDeviceProfile.getOs(), targetDeviceProfile.getDeviceType(), targetDeviceProfile.getDeviceModel(), 
					targetDeviceProfile.getPnsToken(), requestBean.getUserContext().getSessionId(), trxId,
					requestBean.getUserContext().getLocaleCode(), targetDeviceProfile.getDeviceLevel(), ipAddress);
			
			syncDevice(requestBean.getObUser().getOrgId(), requestBean.getUserContext().getSessionId(), trxId, 
					requestBean.getObUser().getUserId(), ipAddress, requestorDeviceProfile.getCif(), targetDeviceProfile, false);
			
			/*
			 * Push notification to all binded device when new device binded successful.
			 */
			try {
				VCService tokenService = new VCService(appContext);
				List<DeviceProfile>  bindedDevList = deviceDao.getDeviceByCIF(requestorDeviceProfile.getCif());
				for (DeviceProfile devProfile:bindedDevList){
					String title = RetrieveAppStatInfo_mcb.getMessageTable(requestBean.getUserContext().getLocaleCode()).get("PUSH_NOTI_TITLE");
					String message = RetrieveAppStatInfo_mcb.getMessageTable(requestBean.getUserContext().getLocaleCode()).get("PUSH_NOTI_BOUND_DEVICE");
					message = message.replace("$1", devProfile.getDeviceModel());
					
					com.silverlake.mleb.mcb.module.vc.notification.RequestData vcPushNotificationRequest = new com.silverlake.mleb.mcb.module.vc.notification.RequestData();
					com.silverlake.mleb.mcb.module.vc.notification.ResponseData vcPushNotificationResponseData = new com.silverlake.mleb.mcb.module.vc.notification.ResponseData();
					vcPushNotificationRequest.setOrg_cd(requestBean.getObUser().getOrgId());
					vcPushNotificationRequest.setUsr_cd(requestBean.getObUser().getUserId());
					vcPushNotificationRequest.setDevice_id(devProfile.getDeviceId());
					vcPushNotificationRequest.setSubject(title);
					vcPushNotificationRequest.setMessage(message);
					VCResponse vcPushResponse = tokenService.callOmniVC(VCMethodConstant.REST_METHODS.SEND_PUSH_NOTIFICATION, vcPushNotificationRequest, vcPushNotificationResponseData, 
							requestBean.getUserContext().getSessionId(), trxId, ipAddress);
				}
			} catch(Exception e) {
				//Don't effect the process result due to fail of push notification only
				log.catching(e);
			}
		} else if(requestBean.getLoginType().equalsIgnoreCase("REJECT")) {
			if (deviceDao.removeProfileByDevAndCif(targetDeviceProfile.getCif(), targetDeviceProfile.getDeviceId()) <= 0) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
				log.info("Failed to unbind the device");
				return;
			}
			
			UnBindDeviceProfile unbindDev = new UnBindDeviceProfile();
			MapperUtil.copyFields(targetDeviceProfile, unbindDev);
			unbindDev.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_REJECTED);
			unbindDev.setUnBindBy(requestorDeviceProfile.getCif());
			unbindDev.setDateUnBind(new Date());
			unbindDev.setTransId(trxId);
			deviceDao.insertEntity(unbindDev);
			
			ObDeviceCifBean devBean = new ObDeviceCifBean();
			devBean.setDevId(unbindDev.getDeviceId());
			devBean.setDeviceLevel(unbindDev.getDeviceLevel());
			devBean.setDeviceStatus(unbindDev.getStatus());
			devBean.setCif(unbindDev.getCif());
			responseBean.getDeviceListing().add(devBean);
			
			syncDevice(requestBean.getObUser().getOrgId(), requestBean.getUserContext().getSessionId(), trxId, 
					requestBean.getObUser().getUserId(), ipAddress, requestorDeviceProfile.getCif(), targetDeviceProfile, true);
			
		} else if (requestBean.getLoginType().equalsIgnoreCase("SWITCH")) {
			if (!targetDeviceProfile.getStatus().equals(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE)) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
				log.info("Target device is not active");
				return;
			}
			
			// target device id is the device that will become the primary device
			// the rest will be secondary device
			List<DeviceProfile> deviceList = deviceDao.getDeviceByCIF(requestorDeviceProfile.getCif());
			if(deviceList != null && deviceList.size() > 0) {
				
				//check device is on countdown or not
				for (DeviceProfile dp : deviceList) {
					if(dp.getDateBind() != null && dp.getDeviceLevel() != null && dp.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY)
							&& dp.getApprovalAction() != null && dp.getApprovalAction().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_APPROVE)
							&& dp.getDateSwitch() == null) {
						// cooling period only applicable to newly binded secondary device - exclude switch device process
						Calendar cal = Calendar.getInstance();
						cal.setTime(dp.getDateBind());
						cal.add(Calendar.HOUR_OF_DAY, 12);
						Date coolingPeriod = cal.getTime();
						Date current = new Date();
						long countdown = coolingPeriod.getTime() - current.getTime();
						if(Long.signum(countdown) > 0) {
							log.info("Device is still on countdown");
							responseBean.getObHeader().setStatusCode(MiBConstant.MCB_INVALID_SWITCH);
							return;
						} else {
							log.info("countdown ended");
						}
					}
				}
				
				//update device
				for (DeviceProfile dp : deviceList) {
					if(dp.getDeviceId().equals(requestBean.getDevId())) {
						dp.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY);
					} else {
						dp.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY);
					}
					dp.setDateSwitch(new Date());
					deviceDao.updateEntity(dp);
					devicebind.sendDeviceInfo(appContext, "U", requestBean.getObUser().getOrgId(), requestBean.getObUser().getUserId(), 
							dp.getDeviceId(), dp.getOs(), dp.getDeviceType(), dp.getDeviceModel(), dp.getPnsToken(), 
							requestBean.getUserContext().getSessionId(), trxId, requestBean.getUserContext().getLocaleCode(), dp.getDeviceLevel(), ipAddress);
				}
			}
		} else {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_INVALID_ACTION);
			return;
		}
		
		responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
	}
	
	public static boolean isDevicePendingExpired(String unbindBy, String trxId, DeviceProfile deviceProfile, GeneralCodeDAO generalCodeDao, DeviceCIFDao deviceDao) {
		if (deviceProfile == null || !deviceProfile.getStatus().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING)) {
			return false;
		}
		
		int pendingTimeLimit = MiBConstant.DEV_BINDING_DEFAULT_PENDING_TIME_LIMIT;
		
		try {
			GeneralCode setting = generalCodeDao.findByGnCode(MiBConstant.GNCODE_DEV_BINDING_PENDING_TIME_LIMIT);
			String value = setting.getGnCodeDescription();//Value in minutes
			pendingTimeLimit = Integer.parseInt(value);
		} catch(Exception e) {
			log.info("Unable to get device pending time limit setting. Defaulted to "+pendingTimeLimit + " minutes ("+e+")");
		}
		
		//Get the expiration time limit
		Calendar cal = Calendar.getInstance();
		cal.setTime(deviceProfile.getDateBind());
		cal.add(Calendar.MINUTE, pendingTimeLimit);
		Date expiredDateTime = cal.getTime();
		//Expired, put into unbind table
		if (expiredDateTime.before(new Date())) {
			if (deviceDao.removeProfileByDevAndCif(deviceProfile.getCif(), deviceProfile.getDeviceId()) >= 1) {
				UnBindDeviceProfile unbindDev = new UnBindDeviceProfile();
				MapperUtil.copyFields(deviceProfile, unbindDev);
				unbindDev.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_EXPIRED);
				unbindDev.setUnBindBy(unbindBy);
				unbindDev.setDateUnBind(new Date());
				unbindDev.setTransId(trxId);
				deviceDao.insertEntity(unbindDev);
				
				return true;
			}
		}
		return false;
	}
	
	public void syncDevice (String orgId, String sessionId, String trxId, String userId, String ip, String cif, DeviceProfile deviceProfile, boolean isRemoved) throws Exception {
		// 3.9 
		//sync active device
		List<DeviceProfile> activeList = deviceDao.getDeviceByCIF(cif);
		List<List_device> list_device = new ArrayList<List_device>();
		if(activeList != null){
			SimpleDateFormat sdf = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
			for(DeviceProfile syncDeviceProfile:activeList){
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
				}
				
				device.setBind_date(sdf.format(syncDeviceProfile.getDateBind()));
				CustomerProfile profileDB = custDao.getCustomerProfileByCif(syncDeviceProfile.getCif());
				device.setLast_login_date(sdf.format(profileDB.getLastLoginDT()));
				list_device.add(device);
			}
			
			// sync rejected/expired binding device
			if(isRemoved) {
				List_device device = new List_device();
				device.setDevice_id(deviceProfile.getDeviceId());
				device.setDevice_os(deviceProfile.getOs());
				device.setPush_token(deviceProfile.getPnsToken());
				device.setDevice_alias(deviceProfile.getAliasName());
				device.setDevice_type(deviceProfile.getDeviceModel());
				
				if(deviceProfile.getDeviceType()!=null && deviceProfile.getDeviceType().equalsIgnoreCase("android")) {
					device.setDevice_os("Android "+deviceProfile.getOs());
					device.setPush_token_type("GCM");
				} else if(deviceProfile.getDeviceType()!=null && deviceProfile.getDeviceType().equalsIgnoreCase("ios")) {
					device.setPush_token_type("APNS");
				}
				device.setIs_primary("N");
				device.setDevice_status("E");
				
				device.setBind_date(sdf.format(deviceProfile.getDateBind()));
				CustomerProfile profileDB = custDao.getCustomerProfileByCif(deviceProfile.getCif());
				device.setLast_login_date(sdf.format(profileDB.getLastLoginDT()));
				list_device.add(device);
			}
		}
		
		DeviceCIFUnbindAck syncDevice = new DeviceCIFUnbindAck();
		syncDevice.syncDevice(appContext, orgId, userId, sessionId, trxId, list_device, ip);
	}
}
