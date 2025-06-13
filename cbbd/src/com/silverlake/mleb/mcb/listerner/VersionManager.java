package com.silverlake.mleb.mcb.listerner;


import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.MiBServices;

@Service("VersionManager")
public class VersionManager {
	
	
	private static Logger log = LogManager.getLogger(VersionManager.class);
	
	@Autowired
	MLEBMIBDAO dao;
	
	public Object onCall(MICBRequestBean arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Object objBean = arg0;
		
		if(null!=objBean && objBean instanceof MICBRequestBean)
		{
			
			MICBRequestBean micbRequestBean = (MICBRequestBean) objBean;
			ObAuthenticationResponse obResponse = new ObAuthenticationResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			
			
			//bypass specific function checking 
			//"android","ios","blackberry","window"
			//example below is allow version 1.0 access for ios when request faq service:
			
			String requestFAQ = MiBConstant.FUNC_REQUEST_FAQ;
			if(requestFAQ.equalsIgnoreCase(micbRequestBean.getHeaderBean().getServiceID()))
			{
				String deviceType = micbRequestBean.getDeviceBean()==null?null:micbRequestBean.getDeviceBean().getType();
				//only allow ios
				if(deviceType.equalsIgnoreCase("ios"))
				{
					String requestInfo = micbRequestBean.getHeaderBean().getRequestInfo();
					//1 is allow 1.x version
					if(null!=requestInfo && requestInfo.split("\\.")[0].startsWith("1"))
					{
						return objBean;
					}
					
				}
			}
			
			
			
			
			
			
			String requestInfo_serviceID = MiBConstant.FUNC_REQUEST_INFO;
			String appStatInfo_serviceID = MiBConstant.FUNC_MCB_APP_STAT_INFO;
			
			if(!requestInfo_serviceID.equalsIgnoreCase(micbRequestBean.getHeaderBean().getServiceID()) && !appStatInfo_serviceID.equalsIgnoreCase(micbRequestBean.getHeaderBean().getServiceID()) )
			{
				
				String deviceType = micbRequestBean.getDeviceBean()==null?null:micbRequestBean.getDeviceBean().getType();
				boolean rooted = micbRequestBean.getDeviceBean().isRooted();
				MiBServices mibService = new MiBServices(dao);
				
				String checkVersion = mibService.checkAccessVersion(deviceType, micbRequestBean.getHeaderBean().getRequestInfo(),rooted);
				if(null == checkVersion)
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_DEVICE_TYPE);
				}
				else if("false".equalsIgnoreCase(checkVersion))
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_VERSION_ERROR);				
				}
				else if("rooted".equalsIgnoreCase(checkVersion))
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_BLOCK_ROOTED_DEVICE);				
				}
				else if("true".equalsIgnoreCase(checkVersion))
				{
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
	


}
