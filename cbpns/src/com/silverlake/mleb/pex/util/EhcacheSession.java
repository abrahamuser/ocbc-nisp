package com.silverlake.mleb.pex.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;



public class EhcacheSession 
{
	
	private static CacheManager  cacheManager;
	

	private static Ehcache validationPexSession;
	private static Ehcache dspCallBalancer;
	private static Ehcache timeRefSession;
	
	private static Ehcache lockTask;

	
	public static void main (String [] args)
	{
		
	}
	
	
	
	
	
	
	
	public void initialSession()
	{
		cacheManager  = new CacheManager();
		                                              
		validationPexSession = cacheManager.getEhcache("validationPexSession");
		dspCallBalancer = cacheManager.getEhcache("dspCallBalancer");
		timeRefSession = cacheManager.getEhcache("timeRefSession");
		lockTask =  cacheManager.getEhcache("lockTask");
		
		System.out.println("Ehcahce Initaill Session successfully ");
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











	public Ehcache getDspCallBalancer() {
		return dspCallBalancer;
	}







	public void setDspCallBalancer(Ehcache dspCallBalancer) {
		this.dspCallBalancer = dspCallBalancer;
	}







	public Ehcache getTimeRefSession() {
		return timeRefSession;
	}







	public void setTimeRefSession(Ehcache timeRefSession) {
		this.timeRefSession = timeRefSession;
	}







	







	public static Ehcache getValidationPexSession() {
		return validationPexSession;
	}







	public static void setValidationPexSession(Ehcache validationPexSession) {
		EhcacheSession.validationPexSession = validationPexSession;
	}







	public static Ehcache getLockTask() {
		return lockTask;
	}







	public static void setLockTask(Ehcache lockTask) {
		EhcacheSession.lockTask = lockTask;
	}







	
}