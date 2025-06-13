package com.silverlake.mleb.pex.listerner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.hlb.mib.bean.ObAuthenticationResponse;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.MiBServices;

@Service("ServerMaintenanceManager")
public class ServerMaintenanceManager {
	
	
	private static Logger log = LogManager.getLogger(ServerMaintenanceManager.class);
	
	@Autowired
	MLEBPExDAO dao;
	

	

	public Object onCall(MICBRequestBean arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Object objBean = arg0;
		
		if(null!=objBean && objBean instanceof MICBRequestBean)
		{
			
			MICBRequestBean micbRequestBean = (MICBRequestBean) objBean;
			ObAuthenticationResponse obResponse = new ObAuthenticationResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			
			String requestInfo_serviceID = MiBConstant.FUNC_REQUEST_INFO;
			if(!requestInfo_serviceID.equalsIgnoreCase(micbRequestBean.getHeaderBean().getServiceID()))
			{
				
				MiBServices mibService = new MiBServices(dao);
				
				String [] checkServer = mibService.checkMaintenanceNotification(micbRequestBean.getDeviceBean().getDeviceId());
				
				
				
				if(checkServer[0].equalsIgnoreCase("true"))
				{
					obResponse.getObHeader().setMaintenanceStartDateTime(checkServer[1]);
					obResponse.getObHeader().setMaintenanceEndDateTime(checkServer[2]);
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_SERVER_UNDER_MAINTENANCE);
				}
				else
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
