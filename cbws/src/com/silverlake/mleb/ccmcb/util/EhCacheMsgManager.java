package com.silverlake.mleb.ccmcb.util;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 
import com.silverlake.mleb.mcb.bean.ObGeneralCodeBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;






public class EhCacheMsgManager
{
	

	private static Ehcache msgcache_en ;  
	private static Ehcache msgcache_id ;  
	private static Ehcache msgcache_cn ;
	private static Logger log = LogManager.getLogger(EhCacheMsgManager.class);
	
	public void initialCache()
	{
		UserLoginSession userSession = new UserLoginSession();
		CacheManager cacheManager = userSession.getCacheManager();
		msgcache_en  = cacheManager.getEhcache("msgcache_en"); 
		msgcache_id = cacheManager.getEhcache("msgcache_id"); 
		msgcache_cn = cacheManager.getEhcache("msgcache_cn"); 
		
	}
	
	
	public void reloadMsg(List<ObGeneralCodeBean> msgList)
	{
		
		
		if(msgList!=null)
		{
			log.info("Reload Msg Cache :  "+msgList.size());
			for(ObGeneralCodeBean temp:msgList)
			{
				//log.info("["+temp.getGnCode()+"]["+temp.getGnCodeDescription()+"]["+temp.getGnCodeType()+"]");
				
				if(temp.getGnCodeType().equalsIgnoreCase(MiBConstant.LOCALE_IN))
				{
					  
					Element elm = new Element(temp.getGnCode(),temp.getGnCodeDescription());
					msgcache_id.put(elm);
					
					
				}
				else if(temp.getGnCodeType() != null && temp.getGnCodeType().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
				{
					Element elm = new Element(temp.getGnCode(),temp.getGnCodeDescription());
					msgcache_cn.put(elm);
				}
				else
				{
					Element elm = new Element(temp.getGnCode(),temp.getGnCodeDescription());
					msgcache_en.put(elm);
				}
				
				
			}
			log.info("Reload Msg Cache Done");
		}
		
		
	}
	
	
	
	public String getMsg(String statusCode, String locale)
	{
		
		 log.info("locale msg resp : "+locale);
		
		if(null!=locale && locale.equalsIgnoreCase(MiBConstant.LOCALE_IN))
		{
			Element msgTemp =  msgcache_id.get(statusCode);
			if(null!=msgTemp && msgTemp.getObjectValue()!=null)
			{
				return (String) msgTemp.getObjectValue();
			}
		}
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
		{
			Element msgTemp =  msgcache_cn.get(statusCode);
			if(null!=msgTemp && msgTemp.getObjectValue()!=null)
			{
				return (String) msgTemp.getObjectValue();
			}
		}
		else
		{
 
			Element msgTemp =  msgcache_en.get(statusCode);
			if(null!=msgTemp && msgTemp.getObjectValue()!=null)
			{
				return (String) msgTemp.getObjectValue();
			}
			
		}
		
		
		return statusCode;

	}
	
	
	
	
}