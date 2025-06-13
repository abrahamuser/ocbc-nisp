package com.silverlake.mleb.pex.listerner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;

@Service("LogDBManager")
public class LogDBManager {
	
	
	private static Logger log = LogManager.getLogger(LogManager.class);
	
	@Autowired
	MLEBPExDAO dao;
	

	

	public Object onCall(Object arg0) {
		// TODO Auto-generated method stub
		//HlbIbwsTranx entityObj = new HlbIbwsTranx();
		try
		{
			
			log.info("HLB WS Mule LogManager onCall ]");
			
			Object entityObj = arg0;

			int status = dao.insertEntity(entityObj);
		
			log.info("HLB WS Mule Insert HlbIbwsTranx log done :: [ "+status+" ]");
		
		}
		catch(Exception ex)
		{
			log.error(this.getClass().getCanonicalName(), ex);
		}
		
		
		return null;
	}	
	


}
