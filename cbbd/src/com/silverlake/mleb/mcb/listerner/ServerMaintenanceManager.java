package com.silverlake.mleb.mcb.listerner;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.MaintenanceNotification;
import com.silverlake.mleb.mcb.entity.dao.MaintenanceNotificationDAO;
import com.silverlake.mleb.mcb.util.PropertiesManager;

@Service("ServerMaintenanceManager")
public class ServerMaintenanceManager{
	
	
	private static Logger log = LogManager.getLogger(ServerMaintenanceManager.class);
	private static PropertiesManager pro = new PropertiesManager();
	
	@Autowired MaintenanceNotificationDAO maintenanceNotificationDAO;
	

	public Object onCall(MICBRequestBean arg0) throws Exception {
		Object objBean = arg0;
		
		if(null!=objBean && objBean instanceof MICBRequestBean){
			MICBRequestBean micbRequestBean = (MICBRequestBean) objBean;
			ObAuthenticationResponse obResponse = new ObAuthenticationResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			
			String appStatInfo_serviceID = MiBConstant.FUNC_MCB_APP_STAT_INFO;
			//if(!requestInfo_serviceID.equalsIgnoreCase(micbRequestBean.getHeaderBean().getServiceID()))
			
			if(!checkServerMaintenanceService(micbRequestBean.getHeaderBean().getServiceID()) && !appStatInfo_serviceID.equalsIgnoreCase(micbRequestBean.getHeaderBean().getServiceID())){
				String deviceType = micbRequestBean.getDeviceBean()==null?null:micbRequestBean.getDeviceBean().getType();
				String appVersion = micbRequestBean.getHeaderBean().getRequestInfo();
				String [] checkServer = isServerUnderMaintenance(deviceType, appVersion);
				
				if(checkServer[0].equalsIgnoreCase("true")){
					obResponse.getObHeader().setMaintenanceStartDateTime(checkServer[1]);
					obResponse.getObHeader().setMaintenanceEndDateTime(checkServer[2]);
					
					String[] param = new String[2];
					param[0] = checkServer[1];
					param[1] = checkServer[2];
					obResponse.setParamValue(param);
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_SERVER_UNDER_MAINTENANCE);
				} else {
					//obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					return objBean;
				}
				
				MICBResponseBean responseBean = new MICBResponseBean();
				responseBean.setBDObject(obResponse);
				return responseBean;
			}
		}
		
		return objBean;
	}
	

	public boolean checkServerMaintenanceService(String serviceid){
		String allowServerMaintenanceService = pro.getProperty("accessUnderMaintenance");
		String allowServicesList[] = null==allowServerMaintenanceService?new String[0]:allowServerMaintenanceService.split(",");		
		
		for(String allowService:allowServicesList)
		{
			if(serviceid.equalsIgnoreCase(allowService)){
				return true;
			}
		}
				
		return false;
	}
	
	private String[] isServerUnderMaintenance(String deviceType, String appVersion) {
		// [0]true/false , [1]Start Datetime, [2] End Datetime
		String[] result = new String[3];

		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
		
		List<MaintenanceNotification> notifications = maintenanceNotificationDAO.getMaintenanceNotification(currentDate);
		for(MaintenanceNotification notif : notifications) {
			//Format: android=3,ios=4
			String[] deviceVersionWhiteList  = new String[] {};
			if(notif.getDescription() != null && !notif.getDescription().isEmpty()) {
				deviceVersionWhiteList = notif.getDescription().split(",");
			}
			for(String deviceVersionPair:deviceVersionWhiteList) {
				if(deviceVersionPair.trim().startsWith(deviceType.toLowerCase())) {
					String[] temp = deviceVersionPair.split("=");
					if(temp.length == 2) {
						int allowedVersion = -1;
						int mobileVersion = 0;
						try {
							allowedVersion = Integer.parseInt(temp[1].trim());
							mobileVersion = Integer.parseInt(appVersion.split("\\.")[0]);
						}catch(Exception e) {}
						if(mobileVersion < allowedVersion) {
							result[0] = "true";
							result[1] = sdf.format(notif.getEffectiveStartDt());
							result[2] = sdf.format(notif.getEffectiveEndDt());
							return result;
						}
					}
					break;
				}
			}
		}

		result[0] = "false";
		return result;
	}
}
