package com.silverlake.mleb.pex.module.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.hlb.mib.entity.HlbMaintenanceNotification;
import com.silverlake.mleb.pex.bean.ObPexTransactionDetails;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.util.PropertiesManager;


public class MiBServices 
{
	private static Logger log = LogManager.getLogger(MiBServices.class);	
	private PropertiesManager pro = new PropertiesManager();
	
	MLEBPExDAO dao;
	
	public MiBServices(MLEBPExDAO dao)
	{
		this.dao = dao;
	}
	
	public boolean checkDay(Date checkDay, String dbDay)
	{
		if(null==dbDay || !dbDay.matches("^[0-9]{1}"))
		{
			return true;
		}
		
		String checkDayString = "0";
		String[] week = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
		SimpleDateFormat sft = new SimpleDateFormat("E");
		String dayWeek = sft.format(checkDay);
		for(int i=0;i<7;i++)
		{
			if(dayWeek.equalsIgnoreCase(week[i]))
			{
				checkDayString = String.valueOf(i+1);
			}
		}
		
		
		if(checkDayString.equalsIgnoreCase(dbDay))
		{
			return true;
		}
		
		
		
		return false;
	}
	
	public String[] checkMaintenanceNotification(String deviceID)
	{
		//[0]true/false , [1]Start Datetime,  [2] End Datetime
		String[] result = new String[3];
		

		// allow device access under maintenance
		String allowServerMaintenanceService = pro.getProperty("deviceAccessUnderMaintenance");
		String allowDeviceList[] = null==allowServerMaintenanceService?new String[0]:allowServerMaintenanceService.split(",");
		for(String allowDevice:allowDeviceList)
		{
			if(null!=deviceID && deviceID.equalsIgnoreCase(allowDevice)){
				result[0] = "false";
				return result;
			}
		}
		// end 
		
		

		Date currentDate = new Date();
		SimpleDateFormat sft = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
		
		String currentDT = sft.format(currentDate);
		String currentTime = currentDT.substring(11,16);
		String dateMaintenance = currentDT.substring(0,11);
		String sqlx = "FROM HlbMaintenanceNotification WHERE effective_start_dt <= '"+currentDT+"' AND effective_end_dt >= '"+currentDT+"' AND status='"+MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE+"'";
		
		List<HlbMaintenanceNotification>  notifications = dao.selectQuery(sqlx);
		
		for(HlbMaintenanceNotification notif : notifications)
		{
			
			String maintenanceStartTime = notif.getStartTime();
			String maintenanceEndTime = notif.getEndTime();
			if(currentTime.compareTo(maintenanceStartTime) >= 0 && currentTime.compareTo(maintenanceEndTime)<= 0 && checkDay(currentDate,notif.getDayInWeek()))
			{
				//is under maintenance
				
				if(maintenanceEndTime.equalsIgnoreCase("23:59"))
				{
					 Calendar c = Calendar.getInstance();
					 c.setTime(currentDate);
					 c.add(Calendar.DATE, 1);
					 Date checkNextDay = new Date(c.getTimeInMillis());
					
					 String nextCurrentDay = sft.format(checkNextDay);
					 String nextMainTenance = nextCurrentDay.substring(0,11);
					 String sqlxx = "FROM HlbMaintenanceNotification WHERE effective_start_dt <= '"+nextCurrentDay+"' AND effective_end_dt >= '"+nextCurrentDay+"' AND status='"+MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE+"'";
					
					 
					 List<HlbMaintenanceNotification>  nextNotifications = dao.selectQuery(sqlxx);
					 for(HlbMaintenanceNotification nextNotify:nextNotifications)
					 {
						 if(nextNotify.getStartTime().equalsIgnoreCase("00:00") && checkDay(checkNextDay,nextNotify.getDayInWeek()))
						 {
							 String nextMaintenanceEndTime = nextNotify.getEndTime();
							 	result[1] = dateMaintenance+maintenanceStartTime;
								result[2] = nextMainTenance+nextMaintenanceEndTime;
								return result;
						 }
						 else
						 {
								result[1] = dateMaintenance+maintenanceStartTime;
								result[2] = dateMaintenance+maintenanceEndTime;
						 }
					 }
				}
				else
				{
					result[1] = dateMaintenance+maintenanceStartTime;
					result[2] = dateMaintenance+maintenanceEndTime;
				}
				
				result[0] = "true";
				return result;
			}
			
			
		
		}
		
		result[0] = "false";
		return result;
		
	}
	

	
	

	 public Comparator<ObPexTransactionDetails> COMPARATOR_PExTrans = new Comparator<ObPexTransactionDetails>()
    {
		 SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST);
		 
		public int compare(ObPexTransactionDetails o1, ObPexTransactionDetails o2) {
			Date date2 = null ;
			Date date1 = null;
			
			try {
				date2 = refFormat.parse(o2.getDatetime());
				date1 = refFormat.parse(o1.getDatetime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return date2.compareTo(date1);
		}

    };
    
    
    
    public Comparator<ObPexTransactionDetails> COMPARATOR_PExTrans_vn = new Comparator<ObPexTransactionDetails>()
    	    {
    			Locale cambodia = new Locale("km","KH","KH");
    			 SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST,cambodia);
    			 
    			public int compare(ObPexTransactionDetails o1, ObPexTransactionDetails o2) {
    				Date date2 = null ;
    				Date date1 = null;
    				
    				try {
    					date2 = refFormat.parse(o2.getDatetime());
    					date1 = refFormat.parse(o1.getDatetime());
    				} catch (ParseException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
    				
    				return date2.compareTo(date1);
    			}

    	    };

	
}
