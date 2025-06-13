package com.silverlake.mleb.ccmcb.util;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.bean.ObUserDetail;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;



public class UserLoginSession 
{
	
	private static CacheManager  cacheManager;
	private static Ehcache records ;  
	private static Ehcache recordSession ; 
	private static Ehcache removeSession ; 
	private static Logger log = LogManager.getLogger(UserLoginSession.class);

	public static void main (String [] args)
	{
		
	}
	
	
	
	
	
	
	public boolean addSessionUser(HttpSession session, ObUserContext userContext, ObUserDetail obUser)
	{
		userContext.setLoginId(userContext.getLoginId().toUpperCase());
		Element data = records.putIfAbsent(new Element(userContext.getLoginId(), session.getId()));
		
		
		
		if(null== data  )
		{
			
			Element sData = recordSession.putIfAbsent(new Element(session.getId(),userContext.getLoginId()));
			
			if(null == sData)
			{
				addSessionKey(session,userContext);
				addSessionKeyData(session,obUser);
				return true;
			}
			else
			{
				records.remove(userContext.getLoginId());
			}	
		}
	
		
		
		
		
		return false;
	}
	
	

	public boolean addSessionUser_replace(HttpSession session, ObUserContext userContext,ObUserDetail obUser)
	{
		userContext.setLoginId(userContext.getLoginId().toUpperCase());
		
		if(removeUser(userContext.getLoginId()))
		{
			
			return addSessionUser(session,userContext,obUser);
		}
		
		
		
		return false;
	}
	
	
	
	
	
	public boolean isSessionUser(HttpSession session)
	{
		Object checkObUser = session.getAttribute(SecurityUtil.LOGIN_USER_SESSION_TAG);
		
		if(records.isValueInCache(session.getId()))
		{
			 
			return true;
		}
		//OCBC two DC cluster issue, will able verify http session object only instead of Ehcache value
		else if(null!=checkObUser && checkObUser instanceof ObUserDetail)
		{
			 
			return true;
		}

		return false;
	}
	
	public boolean isSessionUserCache(HttpSession session)
	{
		Object checkObUser = session.getAttribute(SecurityUtil.LOGIN_USER_SESSION_TAG);
		
		if(records.isValueInCache(session.getId()))
		{
			 
			return true;
		}

		return false;
	}
	
	public boolean isSessionRemoveUser(HttpSession session)
	{
		return removeSession.isKeyInCache(session.getId());
	}
	
	
	public boolean removeUser(String userId)
	{
		userId = userId.toUpperCase();
		log.debug("REMOVE USER : userid - "+userId);
		Element sessionID = records.get(userId);
		
		boolean data  = records.remove(userId);
		log.debug("REMOVE USER DATA : userid - "+data);
		if(data)
		{
			log.debug("REMOVE USER SUCCESS : userid - "+data);
			recordSession.remove(sessionID.getObjectValue());
			removeSession.put(new Element(sessionID.getObjectValue(), userId));
			return true;
		}
		return false;
		
	}
	
	
	
	public boolean removeSessionUser(HttpSession session)
	{
		
		ObUserContext userObj = getSessionKey(session);
		
		if(null!=userObj)
		{
			
			boolean data  = records.remove(userObj.getLoginId());
			if(data)
			{
				removeSession.remove(session.getId());
				recordSession.remove(session.getId());
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/*public void showAllRecord()
	{
		Collection c = records.values();
		
		Iterator ci = c.iterator();
		
		while(ci.hasNext())
		{
			Object data = ci.next();
			log.debug(data.toString());
		}
		
	}
	
	public void showAllRecords()
	{
		Collection c = recordSession.values();
		
		Iterator ci = c.iterator();
		
		while(ci.hasNext())
		{
			Object data = ci.next();
			log.debug(data.toString());
		}
		
	}*/
	
	

	public void  addSessionKey(HttpSession session, ObUserContext userContext)
	{
		userContext.setLoginId(userContext.getLoginId().toUpperCase());
		session.setAttribute(SecurityUtil.LOGIN_SESSION_TAG,userContext);
	}
	
	public void addSessionKeyData(HttpSession session,ObUserDetail obUser)
	{
		session.setAttribute(SecurityUtil.LOGIN_USER_SESSION_TAG,obUser);
	}
	
	
	public ObUserContext getSessionKey(HttpSession session)
	{
		Object keyID = session.getAttribute(SecurityUtil.LOGIN_SESSION_TAG);
		
		return null==keyID?null:(ObUserContext)keyID;
	}
	
	public ObUserDetail getLoginSessionKeyData(HttpSession session)
	{
		Object keyObj = session.getAttribute(SecurityUtil.LOGIN_USER_SESSION_TAG);
		
		return null==keyObj?null:(ObUserDetail)keyObj;
	}






	public static Ehcache getRecords() {
		return records;
	}






	public static void setRecords(Ehcache records) {
		UserLoginSession.records = records;
	}






	public static Ehcache getRecordSession() {
		return recordSession;
	}






	public static void setRecordSession(Ehcache recordSession) {
		UserLoginSession.recordSession = recordSession;
	}






	public static Ehcache getRemoveSession() {
		return removeSession;
	}






	public static void setRemoveSession(Ehcache removeSession) {
		UserLoginSession.removeSession = removeSession;
	}
	
	
	
	public void initialSession()
	{
		cacheManager  = new CacheManager();
		records  = cacheManager.getEhcache("records");  
		recordSession = cacheManager.getEhcache("recordSession");   
		removeSession = cacheManager.getEhcache("removeSession");  
		
	}
	
	
	public CacheManager getCacheManager()
	{
		return cacheManager;
	}
	
	public void stopSession()
	{
		cacheManager.shutdown();
	}
	
	public int getTotalRecord()
	{
		return records.getSize();
	}
}