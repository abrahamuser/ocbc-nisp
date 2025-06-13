package com.silverlake.mleb.ccmcb.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.ccmcb.module.RetrieveAppStatInfo;
import com.silverlake.mleb.ccmcb.util.CacheMessageManager;
import com.silverlake.mleb.ccmcb.util.EhCacheMsgManager;
import com.silverlake.mleb.mcb.bean.ObAppStatResponse;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;

@Service
public class RefreshEhCacheMsg
{
	private static Logger log = LogManager.getLogger(RefreshEhCacheMsg.class);
	private boolean firstTime = true;
	private int count = 0;
	
	CacheMessageManager cacheMgr = new CacheMessageManager();
	
	@Scheduled(fixedDelay=(1000*60*5)) //Run every 5 min
	public void process()
	{
		log.info("check x1");
		try
		{
			boolean result = false;
			int MaxCount = 6;
			log.info("check x2");
			while(!result && MaxCount>0)
			{
				
				result = requestMib("reloadMessageProperties");
				
				Thread.sleep(10000);
 
				MaxCount--;
			}
 
		}catch(Exception e)
		{
			
		}
	
	}
	
	
	
	public boolean requestMib(String action)
	{
		RetrieveAppStatInfo appstat = new RetrieveAppStatInfo(null);
		if(null == appstat.getObResponse())
		{
			appstat.processData(null,action);
		}
		
		try
		{
		
			Object obj=  appstat.processSend(ObAppStatResponse.class);
		 
			 
			if(obj instanceof ObAppStatResponse)
			{
				//ObAuthenticationResponse resp = (ObAuthenticationResponse) obj;
				//cacheMgr.processUpdateCache(resp.getIdType());
				
				ObAppStatResponse resp = (ObAppStatResponse) obj;
				
				if(resp.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
				{
					EhCacheMsgManager cacheManager = new EhCacheMsgManager();
					cacheManager.reloadMsg(resp.getMsgList());
					return true;
				}
				
				 
			}
		
		}
		catch(Exception e)
		{
			log.info("Failed To Auto Refresh Cache Msg : "+e.getMessage());
		}
		
		
		return false;
	}
	
	
}
