package com.silverlake.mleb.pex.task;





import java.util.Date;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.services.PExServices;


@Service
public class PExCheckEffectiveChargesTask
{
	private static Logger log = LogManager.getLogger(PExCheckEffectiveChargesTask.class);

	private static final String KEYLOCK = "EFFECTIVE_CHARGES_TASK";
	@Autowired TaskRenewSession renewSession;
	@Autowired MLEBPExDAO dao;
	
	@Scheduled(fixedDelay=10000)
	public void process()
	{
		try
		{
			/*EhcacheSession echache = new EhcacheSession();
			Element cacheData = echache.getLockTask().putIfAbsent(new Element(KEYLOCK,"1"));*/
			boolean data = renewSession.checkTask(KEYLOCK);
			if(data)
			{
				
				Future<String>future  = renewSession.processTask(KEYLOCK);
				try
				{
					taskProcess();
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}

				
				future.cancel(true);

				//boolean removeKey = echache.getLockTask().remove(KEYLOCK);
				
			}
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//taskProcess();
	}
	


	
	@PostConstruct
	public void initIt() throws Exception {
		log.info("PEx Check Effective Charges Started");
	}
	
	
	
	
	
	
	
	
	
	
	public void taskProcess()
	{
		
	
		try
		{
			//log.info("Initializing PEx Expiration Check...");
			
			PExServices pexServices = new PExServices(dao);
			
			pexServices.activeEffectiveCharges(new Date());
			
			
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	

	



}