package com.silverlake.mleb.pex.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.mleb.pex.constant.PExConstant;

public class HLBDateUtil
{
	public static final String formatXMLGregorianCalendarToString(String format, XMLGregorianCalendar cal)
	{
		String returnStr = "-";
		
		if(format != null && !format.equals("") && cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			returnStr = DATE_FORMAT.format(cal.toGregorianCalendar().getTime());
		}
		
		return returnStr;
	}
	
	
	public static final String getCurrentTime(){
		
		String currentTime=""; 
		String fmat = "HH:mm:ss";     //"HH:mm:ss.sssZ";   //"yyyy-MM-dd'T'HH:mm:ss.SSSz";
		Locale malaysia = new Locale("ms","MY","MY");
		SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(fmat,malaysia);

		currentTime = refDateTImeFormat.format(new Date());
		//currentTime =  displayDate.substring(0,displayDate.length()-2)+":00";

		return currentTime; 
	}
	
	
	
	
	public static final String formatTransactionTimeStamp(XMLGregorianCalendar cal)
	{
		//define for date		
		String returnStr = "";
		String dateOnly = ""; 
		String format = MiBConstant.DDMMMYYYY_FORMAT; 
				
		//define for time
		String currentTime=""; 
		String fmat = MiBConstant.HHMMSS_FORMAT;     
		Locale malaysia = new Locale("ms","MY","MY");

		if(cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(fmat,malaysia);

			currentTime = refDateTImeFormat.format(new Date());    //set system time 
			dateOnly = DATE_FORMAT.format(cal.toGregorianCalendar().getTime());  //set fuzion return date 
			returnStr = dateOnly+" "+currentTime; 
		}
		
		return returnStr;
	}
	
	
	public static final String formatTransactionDateOnly(XMLGregorianCalendar cal)
	{
		//define for date		
		String returnStr = "";
		String dateOnly = ""; 
		String format = MiBConstant.DDMMMYYYY_FORMAT; 

		if(cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			returnStr = DATE_FORMAT.format(cal.toGregorianCalendar().getTime());  //set fuzion return date 
		}
		
		return returnStr;
	}
	
	
	public static final String formatTransactionDateTime(XMLGregorianCalendar cal)
	{
		//define for date		
		String returnStr = "";
		String dateOnly = ""; 
		String format = MiBConstant.DDMMMYYYY_FORMAT_TIME; 

		if(cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			returnStr = DATE_FORMAT.format(cal.toGregorianCalendar().getTime());  //set fuzion return date 
		}
		
		return returnStr;
	}
	
	public static final String formatLastLoginDateTime(Date date)
	{
		String formatDate = "";
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		
		if(date != null)
		{
			formatDate = DATE_FORMAT.format(date.getTime());
		}
		
		return formatDate;
	}
	
	
	
	public  static final XMLGregorianCalendar getTodayDate() throws DatatypeConfigurationException{
		
		// ******* get today date		
    	Date today = new Date();   	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  

    	// *******  rearrange string format 
    	String strToday = df.format(today);
    	String strPassToday = strToday+"T"+MiBConstant.TIME_ELEVN_MIDNIGHT+MiBConstant.TIME_MY_TIMEZONE; 
    	
		// ******* convert back to xml format
    	XMLGregorianCalendar geoCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(strPassToday);
    	//System.out.println("xml today date ----->"+geoCalendar); 
		return geoCalendar; 
	}
	
	
	
	public  static final XMLGregorianCalendar getFromDate(int transPeriod) throws ParseException,  DatatypeConfigurationException{
		
		System.out.println("Passing valid transperiod is?   ----> "+transPeriod); 
		
		// ******* get today date		
    	Date today = new Date();   	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");    	
   	
    	// ******* find x days before
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(today);
    	cal.add(Calendar.DAY_OF_MONTH, -transPeriod);
    	Date before7 = cal.getTime();
    	
    	// ******* convert to string dateOnly
    	String strToday = df.format(today);
    	String strBefore7 = df.format(before7); 
		
		// ******* format to complete accurate string		
		String strPassToday = strToday+"T"+MiBConstant.TIME_ELEVN_MIDNIGHT+MiBConstant.TIME_MY_TIMEZONE; 
//		System.out.println("correct today date format---->"+strPassToday);
		
		String strBefore = strBefore7+"T"+MiBConstant.TIME_TWELVE_MIGNIGHT+MiBConstant.TIME_MY_TIMEZONE; 
//		System.out.println("correct before date format---->"+strBefore);

//		// ******* convert string to XMLGregorianCalendar
		XMLGregorianCalendar geoCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(strBefore);
//		System.out.println("xml from date ----->"+geoCalendar); 
		
		return geoCalendar; 
	}
	
	
	
	public static final String formatTransactionDateOnly(String format, Date date)
	{
			
		String returnStr = "";
		String dateOnly = ""; 
		
		SimpleDateFormat sd = new SimpleDateFormat(format);
		dateOnly = sd.format(date);
		
		return dateOnly;
	}
	
	

	
//	public static void main (String args[]) throws DatatypeConfigurationException
//	{
//
//	
//		Date d = new Date();
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sd.format(d)); 
	
		//System.out.println(formatTransactionDateOnly("dd MMM yyyy", new Date())); 
		
////		// ******* convert string to XMLGregorianCalendar
//		String text = "2013-08-03T00:00:00+08:00";     //"2011-06-07T14:08:59.697-07:00";
//		XMLGregorianCalendar geoCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(text);
//		System.out.println("xml date ----->"+geoCalendar); 
//	}
	
	
	public static String getTransactionDateByLocale(Date transactionDate, String localeCode )
	{
		
		Locale cambodia = new Locale("km","KH","KH");
		
	
		if(localeCode.equalsIgnoreCase("km_KH"))
		{
			//km_KH
			SimpleDateFormat df;
			df = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST, cambodia);
			return df.format(transactionDate);
		}
		else
		{
			//en
			SimpleDateFormat df;
			df = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST);
			return df.format(transactionDate);
		}
		
		
		 
	
	}
	
}
