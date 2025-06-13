package com.silverlake.mleb.mcb.task;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.entity.dao.WLSessionDAO;


@Service
public class ClearWLSession 
{
  
	private static Logger log = LogManager.getLogger(ClearWLSession.class);
	@Autowired WLSessionDAO wlSessionDao;
	
	public void clearSession(){
		try{
			wlSessionDao.clear();
			log.info("Weblogic session cleared");
		}catch(Exception e){
			log.error("Unable to clear weblogic session due to "+e.getMessage());
		}
	}
}
