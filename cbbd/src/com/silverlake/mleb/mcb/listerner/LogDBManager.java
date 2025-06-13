package com.silverlake.mleb.mcb.listerner;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;

@Service("LogDBManager")
public class LogDBManager  {
	
	
	private static Logger log = LogManager.getLogger(LogManager.class);
	
	@Autowired
	MLEBMIBDAO dao;
	


	public Object onCall(Object arg0){
		// TODO Auto-generated method stub
		//IbwsTranx entityObj = new HlbIbwsTranx();
		try
		{
			
			log.info(" WS LogManager onCall ");
			
			Object entityObj = arg0;

			int status = dao.insertEntity(entityObj);
		
			log.info(" WS LogManager done :: [ "+status+" ]");
		
		}
		catch(Exception ex)
		{
			log.info(this.getClass().getCanonicalName(), ex);
		}
		
		
		return null;
	}	
	


}
