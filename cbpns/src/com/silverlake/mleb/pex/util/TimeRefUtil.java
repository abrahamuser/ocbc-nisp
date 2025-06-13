package com.silverlake.mleb.pex.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import net.sf.ehcache.Element;



public class TimeRefUtil 
{
	
	private static EhcacheSession ehCache = new EhcacheSession();
	//private static ConcurrentMap records = new ConcurrentHashMap();  
	private static PropertiesManager property = new PropertiesManager();

	public static void main (String [] args) throws Exception
	{
		
	
	}
	
	public void run() {
		// TODO Auto-generated method stub
		TimeRefUtil ref = new TimeRefUtil();
		System.out.println(ref.genTimeRef(2));
	}
	
	
	
	
	
	public static String genTimeRef(int offset)
	{
		StringDataUtil strUtil = new StringDataUtil();
		String server_index = property.getProperty("server.index");
		if(null==server_index)
		{
			server_index = "00";
		}
		else
		{
			server_index = strUtil.getFulllength_frontBlank(2, server_index, "0");
		}
		
		Date transDate = new Date();
		Calendar myCal = new GregorianCalendar();
		myCal.setTime(transDate);
		
		int hour = myCal.get(Calendar.HOUR_OF_DAY)*60*60;
		int minute = myCal.get(Calendar.MINUTE)*60;
		int second = myCal.get(Calendar.SECOND);
		
		
		long daytime = hour+minute+second;
		Random rnd = new Random();
		
		int rndNo = rnd.nextInt((int)Math.pow(10, offset));
		String rndNumber = strUtil.getFulllength_frontBlank(offset, rndNo+"", "0");
		
		
		
		Element keydata = ehCache.getTimeRefSession().putIfAbsent(new Element(rndNumber,daytime));
		
	
		if(null == keydata)
		{
			return server_index+daytime+""+rndNumber;
		}
		else
		{
			Element oldDaytime = ehCache.getTimeRefSession().get(rndNumber);
			
			if(null == oldDaytime)
			{
				return genTimeRef(offset);
			}
			else if((Long)oldDaytime.getObjectValue() == daytime)
			{
				
				try{Thread.sleep(50);}catch(Exception ex){}
						
				return genTimeRef(offset);
				
			}else
			{
				ehCache.getTimeRefSession().removeElement(new Element(rndNumber, oldDaytime.getObjectValue()));
				return genTimeRef(offset);
			}
			
			
		}
	
	}
	
	
	
	   private static long m_nMaxTID = 1000;
	    private static long m_nMinTID = 0;
	    private static long m_iCount = 0;
	    private static String m_strTmp = "000";
	    private static int m_nTmpLen = 3;





	public static synchronized String generateTID(String strPrefix) {
	        strPrefix = strPrefix + "_" + (new java.util.Date()).getTime();
	        System.out.println("");
	        String strSuf = m_strTmp + m_iCount;
	        m_iCount++;
	        if (m_iCount >= m_nMaxTID) {
	            m_iCount = m_nMinTID;
	        }
	        return strPrefix + strSuf.substring(strSuf.length() - m_nTmpLen);
	    }
	
}