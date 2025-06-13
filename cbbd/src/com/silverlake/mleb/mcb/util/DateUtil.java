package com.silverlake.mleb.mcb.util;

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

import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import org.apache.logging.log4j.LogManager;

public class DateUtil
{
	public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

	 public static boolean isSameDay(Calendar cal1, Calendar cal2) {
	        if (cal1 == null || cal2 == null) {
	            throw new IllegalArgumentException("The dates must not be null");
	        }
	        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
	                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	    }
	 
	 public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }
	 
	public static final String formatXMLGregorianCalendarToString(String format, XMLGregorianCalendar cal, String locale)
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
	
	
	
	
	public static final String formatTransactionTimeStamp(XMLGregorianCalendar cal , String locale )
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
	
	
	public static final String formatTransactionDateOnly(XMLGregorianCalendar cal, String locale)
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
	
	
	public static final String formatTransactionDateTime(XMLGregorianCalendar cal, String locale)
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
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy @ hh:mma");
		
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

	
	public static String cambodiaDateFormat(String strDate, String locale){
	
		String result = ""; 
	
		if(locale.equalsIgnoreCase("km_KH")){


		}

		result = strDate; 
		
		return result; 
	}
	
	public static String convertDateTimeFormat(String sourceFormat, String targetFormat, String sourceDate)
	{
		String targetDate = sourceDate;
		
		try {
			SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);
		    Date date = sdfSource.parse(sourceDate);
		    SimpleDateFormat sdfTarget = new SimpleDateFormat(targetFormat);
		    
		    targetDate = sdfTarget.format(date);
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return targetDate;
	}
	
	//OCBC2BUIXU-679
	public static String convertTransferDate(String transferDate, String lang){
		SimpleDateFormat formatter = new SimpleDateFormat(MiBConstant.OMNI_DATE_FORMAT);
		String outputDate = "";
		try {
			if(transferDate != null){
				Date date = formatter.parse(transferDate);
				Locale inLocale = new Locale("id", "ID");
				// Locale cnLocale = new Locale("cn", "CN");
				if(lang!=null && lang.equalsIgnoreCase(MiBConstant.LANG_EN)){
					DateFormat formatDateToStr = new SimpleDateFormat(MiBConstant.FUND_TRANSFER_ACK_DATE_FORMAT);
					outputDate = formatDateToStr.format(date);
				}
				else if(lang!=null && lang.equalsIgnoreCase(MiBConstant.LANG_CN)){
					DateFormat formatDateToStr = new SimpleDateFormat(MiBConstant.FUND_TRANSFER_ACK_DATE_FORMAT);
					outputDate = formatDateToStr.format(date);
				}
				else{
					DateFormat formatDateToStr = new SimpleDateFormat(MiBConstant.FUND_TRANSFER_ACK_DATE_FORMAT, inLocale);
					outputDate = formatDateToStr.format(date);
				}
	
			}
		}catch(ParseException e){
			e.printStackTrace();
		}
		return outputDate;
	}
	
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static Date trimTime(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return cal.getTime();
    }
	
	public static Date getDate(int date, int month, int year){
		Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, 0, 0, 0);
        return cal.getTime();
	}

}
