package com.silverlake.mleb.mcb.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;



public class EhcacheSession 
{
	
	private static CacheManager  cacheManager;
	private static Ehcache accountSummaryCache;
	private static Ehcache imageIDCache;
	private static Ehcache accessInfoData;

	public static void main (String [] args)
	{
		
	}
	
	
	
	
	
	
	
	public void initialSession()
	{
		cacheManager  = new CacheManager();
		accountSummaryCache = cacheManager.getEhcache("accountSummaryCache");
		imageIDCache =  cacheManager.getEhcache("imageIDCache");
		accessInfoData =  cacheManager.getEhcache("accessInfoData");
	}
	
	
	public void stopSession()
	{
		cacheManager.shutdown();
	}







	public static CacheManager getCacheManager() {
		return cacheManager;
	}







	public static void setCacheManager(CacheManager cacheManager) {
		EhcacheSession.cacheManager = cacheManager;
	}







	public static Ehcache getAccountSummaryCache() {
		return accountSummaryCache;
	}







	public static void setAccountSummaryCache(Ehcache accountSummaryCache) {
		EhcacheSession.accountSummaryCache = accountSummaryCache;
	}







	public static Ehcache getImageIDCache() {
		return imageIDCache;
	}







	public static void setImageIDCache(Ehcache imageIDCache) {
		EhcacheSession.imageIDCache = imageIDCache;
	}







	public static Ehcache getAccessInfoData() {
		return accessInfoData;
	}







	public static void setAccessInfoData(Ehcache accessInfoData) {
		EhcacheSession.accessInfoData = accessInfoData;
	}








	
	
	
	
}