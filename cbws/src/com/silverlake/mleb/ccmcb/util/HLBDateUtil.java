package com.silverlake.mleb.ccmcb.util;

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

import com.silverlake.mleb.mcb.constant.MiBConstant;

public class HLBDateUtil
{
	public final String formatXMLGregorianCalendarToString(String format, XMLGregorianCalendar cal, String locale)
	{
		String returnStr = "-";
		
		if(format != null && !format.equals("") && cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			returnStr = DATE_FORMAT.format(cal.toGregorianCalendar().getTime());
			
			returnStr = cambodiaDateFormat(returnStr, locale);			
		}
		
		return returnStr;
	}
	
	
	public static final String getCurrentTime(){
		
		String currentTime=""; 
		String fmat = "HH:mm:ss";     //"HH:mm:ss.sssZ";   //"yyyy-MM-dd'T'HH:mm:ss.SSSz";
		Locale malaysia = new Locale("ms","MY","MY");
		Locale cambodia = new Locale("km","KH","KH"); 
		
		SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(fmat,cambodia);

		currentTime = refDateTImeFormat.format(new Date());
		//currentTime =  displayDate.substring(0,displayDate.length()-2)+":00";

		return currentTime; 
	}
	
	
	
	
	public final String formatTransactionTimeStamp(XMLGregorianCalendar cal , String locale )
	{
		//define for date		
		String returnStr = "";
		String dateOnly = ""; 
		String format = MiBConstant.DDMMMYYYY_FORMAT; 
				
		//define for time
		String currentTime=""; 
		String fmat = MiBConstant.HHMMSS_FORMAT;     
		Locale malaysia = new Locale("ms","MY","MY");
		Locale cambodia = new Locale("km","KH","KH"); 

		if(cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(fmat,cambodia);

			currentTime = refDateTImeFormat.format(new Date());    //set system time 
			dateOnly = DATE_FORMAT.format(cal.toGregorianCalendar().getTime()); 
			
			dateOnly = cambodiaDateFormat(dateOnly, locale);

			
	
			returnStr = dateOnly+" "+currentTime; 
		}
		
		return returnStr;
	}
	
	
	public final String formatTransactionDateOnly(XMLGregorianCalendar cal, String locale)
	{
		//define for date		
		String returnStr = "";
		String dateOnly = ""; 
		String format = MiBConstant.DDMMMYYYY_FORMAT; 

		if(cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			returnStr = DATE_FORMAT.format(cal.toGregorianCalendar().getTime());  //set fuzion return date 			
			returnStr = cambodiaDateFormat(returnStr, locale);
		}
		
		return returnStr;
	}
	
	
	public final String formatTransactionDateTime(XMLGregorianCalendar cal, String locale)
	{
		//define for date		
		String returnStr = "";
		String dateOnly = ""; 
		String format = MiBConstant.DDMMMYYYY_FORMAT_TIME; 

		if(cal != null)
		{
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
			returnStr = DATE_FORMAT.format(cal.toGregorianCalendar().getTime());  //set fuzion return date 
			
						
			returnStr = cambodiaDateFormat(returnStr, locale); 
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
    	//log.debug("xml today date ----->"+geoCalendar); 
		return geoCalendar; 
	}
	
	
	
	public  static final XMLGregorianCalendar getFromDate(int transPeriod) throws ParseException,  DatatypeConfigurationException{
		
		
		
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
//		log.debug("correct today date format---->"+strPassToday);
		
		String strBefore = strBefore7+"T"+MiBConstant.TIME_TWELVE_MIGNIGHT+MiBConstant.TIME_MY_TIMEZONE; 
//		log.debug("correct before date format---->"+strBefore);

//		// ******* convert string to XMLGregorianCalendar
		XMLGregorianCalendar geoCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(strBefore);
//		log.debug("xml from date ----->"+geoCalendar); 
		
		return geoCalendar; 
	}
	
	
	public static final String formatTransactionDateOnly(String format, Date date)
	{			
		String dateOnly = "";
		Locale cambodia = new Locale("km","KH","KH");
		
		SimpleDateFormat sd = new SimpleDateFormat(format);
		SimpleDateFormat vd = new SimpleDateFormat(format, cambodia);
		dateOnly = sd.format(date);

		return dateOnly;
	}
	
	public static final String formatTransactionDateOnly(String format, Date date, String locale)
	{
			
		String returnStr = "";
		String dateOnly = "";
		Locale cambodia = new Locale("km","KH","KH");
		
		SimpleDateFormat sd = new SimpleDateFormat(format);
		SimpleDateFormat vd = new SimpleDateFormat(format, cambodia);
		
		if(locale.equalsIgnoreCase("km_KH")){
			System.out.println(""); 
			dateOnly = vd.format(date);
		}else{
			dateOnly = sd.format(date);
		}
		
		
		return dateOnly;
	}
	
	public static String getTransactionDateByLocale(Date transactionDate, String localeCode )
	{
		
		Locale cambodia = new Locale("km","KH","KH");
		
	
		if(localeCode.equalsIgnoreCase("km_KH"))
		{
			//km_KH
			SimpleDateFormat df;
			df = new SimpleDateFormat("dd-MMM-yyyy", cambodia);
			return df.format(transactionDate);
		}
		else
		{
			//en
			SimpleDateFormat df;
			df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			return df.format(transactionDate);
		}
		
		
		 
	
	}

	
	public String cambodiaDateFormat(String strDate, String locale){
	
		String result = ""; 
	
		if(locale.equalsIgnoreCase("km_KH")){

		}

		result = strDate; 
		
		return result; 
	}
	
	
	
	

	
//	  public static void main(String args[]) {
//		    int style = DateFormat.MEDIUM;
//		    //Also try with style = DateFormat.FULL and DateFormat.SHORT
//		    Date date = new Date();
//		    SimpleDateFormat df;
//	
//		    Locale cambodia = new Locale("km","KH","km_KH");
//		    df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", cambodia);
//
//		    
//		    //returns array of all locales
//	        Locale locales[] = SimpleDateFormat.getAvailableLocales();
//	         
//	        //iterate through each locale and print
//	        // locale code, display name and country
//	       for (int i = 0; i < locales.length; i++) {
//	        	log.debug(i);
//	           log.debug("%10s - %s, %s \n" , locales[i].toString(),
//	                locales[i].getDisplayName(), locales[i].getDisplayCountry());
//	           
//	         
//	        }
//		  }
}
