package com.silverlake.mleb.pex.task;

import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.entity.dao.PEXDAO;



@Service
public class TaskRenewSession 
{
  
	private static Logger log = LogManager.getLogger(TaskRenewSession.class);
	//@Autowired HazelCache hazelCache;
	@Autowired PEXDAO pexDao;
	
	@Async
	public Future<String> processTask(String renewKey) throws InterruptedException
	{
		//log.debug("Renew Task Key : "+renewKey);
		
		//EhcacheSession echache = new EhcacheSession();
		while(renewKey.length()>0)
		{
				
				Object datae = pexDao.getLockData(renewKey);
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
			rsCheck = pexDao.putIfAbsent(task,"LOCK",60000);
			
		}catch (Exception ex)
		{
			rsCheck = false;
		}
		
		
		return rsCheck;
		
	}
	
	
	
	public boolean lockTask(String task,long time) 
	{
		boolean rsCheck=false;
		try
		{
			rsCheck = pexDao.putIfAbsent(task,"LOCK",time);
			
		}catch (Exception ex)
		{
			rsCheck = false;
		}
		
		
		return rsCheck;
		
	}
	
	

	public boolean removeTask(String task)
	{
		boolean rsCheck = false;
		try
		{
			rsCheck =  pexDao.removeLockData(task);

			
		}catch(Exception ex)
		{
			
			rsCheck = false;

		}
		
		return rsCheck;
	}
	
	
	
	
	@Async
	public void removeTask(String task, String check)
	{
		boolean rsCheck = false;
		try
		{
			rsCheck =  pexDao.removeLockData(task);
			log.debug("--------------------------------- REMOVE["+check+"] ["+task+"] --- ["+rsCheck+"]");
			
		}catch(Exception ex)
		{
			
			rsCheck = false;
			log.debug("-------------------"+ex.getMessage()+"-------------- REMOVE["+check+"] ["+task+"] -E- ["+rsCheck+"]");
		}
		
		//return rsCheck;
	}
	
	
	@Async
	public void checkTaskx(String task, String check) 
	{
		boolean rsCheck=false;
		try
		{
			rsCheck = pexDao.putIfAbsent(task,check,60000);
			log.debug("--------------------------------- PUT["+check+"] ["+task+"] --- ["+rsCheck+"]");
		}catch (Exception ex)
		{
			rsCheck = false;
			log.debug("--------------------------------- PUT["+check+"] ["+task+"] -E- ["+rsCheck+"]");
		}
		
		
		//return rsCheck;
		
	}

}
