package com.silverlake.mleb.mcb.task;


import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.entity.dao.InterbankDAO;


@Service
public class TaskRenewSession 
{
  
	private static Logger log = LogManager.getLogger(TaskRenewSession.class);
	//@Autowired HazelCache hazelCache;
	@Autowired InterbankDAO dao;
	
	@Async
	public Future<String> processTask(String renewKey) throws InterruptedException
	{
		//log.debug("Renew Task Key : "+renewKey);
		
		//EhcacheSession echache = new EhcacheSession();
		while(renewKey.length()>0)
		{
				
				Object datae = dao.getLockData(renewKey);
				//log.debug("Renew Task Key Loop: "+(null==datae?null:datae.toString()));
				Thread.sleep(5000);

		}
		
		return new AsyncResult<String>("Success");


	}
	
	
	
	
	public boolean checkTask(String task) 
	{
		boolean rsCheck=false;
		try
		{
			rsCheck = dao.putIfAbsent(task,"LOCK",60000);
			
		}catch (Exception ex)
		{
			log.info("Exception :: "+ex);
			ex.printStackTrace();
			rsCheck = false;
		}
		
		
		return rsCheck;
		
	}
	

}
