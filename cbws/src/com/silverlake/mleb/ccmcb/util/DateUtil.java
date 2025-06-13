package com.silverlake.mleb.ccmcb.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
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
	 
	 public static boolean checkDatePattern(String datePattern, String data) {
		    try {
		        SimpleDateFormat format = new SimpleDateFormat(datePattern);
		        format.parse(data);
		        return true;
		    } catch (ParseException e) {
		        return false;
		    }
		}

}
