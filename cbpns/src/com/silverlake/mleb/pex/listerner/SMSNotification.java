package com.silverlake.mleb.pex.listerner;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.services.PExServices;

@Service("SMSNotification")
public class SMSNotification {
	
	
	private static Logger log = LogManager.getLogger(SMSNotification.class);
	
	@Autowired
	MLEBPExDAO dao;
	

	

	public Object onCall(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		//HlbIbwsTranx entityObj = new HlbIbwsTranx();
		try
		{	
			log.info("SMS Notification");
			PExServices pexServices = new PExServices(dao);
			Object payload = arg0;
			if(null!=payload)
			{
				
				List<Object> listData = (List<Object>) payload;
				int smsMsg =  Integer.parseInt(listData.get(0).toString());
				PexProcessTranx pexProcess = (PexProcessTranx) listData.get(1);
				String rs = pexServices.SMSPEx(smsMsg,pexProcess);
				log.info("SMS Notification Done : ["+rs+"]");
			}
			
			
		
			
		}
		catch(Exception ex)
		{
			log.error(this.getClass().getCanonicalName(), ex);
		}
		
		
		return null;
	}	
	


}
