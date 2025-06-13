package com.silverlake.mleb.mcb.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.entity.dao.HouseKeepingDAO;
import com.silverlake.mleb.mcb.util.PropertiesManager;

/**
 * This scheduler do house keeping on the log tables of 
 * MLEB_CORE.MLEB_REQUEST_LOG
 * MLEB_CORE.MLEB_RESPONSE_LOG (This is cascade delete which referencing to MLEB_REQUEST_LOG)
 * MLEB_MCB.IBWS_TRANX
 * MLEB_MCB.CUSTOMER_EVENT
 * 
 * @author Alex Loo
 *
 */
@Service
public class LogHouseKeeping{
	private static Logger log = LogManager.getLogger(LogHouseKeeping.class);
	
	private PropertiesManager pro = new PropertiesManager();
	
	@Autowired GeneralCodeDAO generalDao;
	
	@Autowired HouseKeepingDAO houseKeepingDao;

	@Autowired ApplicationContext appContext;
	
	private static long PURGE_TIME_DAYS = 180;//around 6 months
	
	@Scheduled(cron="0 0 3 * * *")//Run at 03:00 daily.
//	@Scheduled(fixedDelay=(60000))//Run every minute (Testing)
	public void process(){
		String isSchedulerNode = pro.getProperty(MiBConstant.IS_SCHEDULER_MODE);
		if(isSchedulerNode == null || !isSchedulerNode.equalsIgnoreCase("true")) {
			return;
		}
		log.info("Scheduler job for db log housekeeping is STARTED");
		try{
			GeneralCode purgeDurationDay = generalDao.findByGnCodeAndGnCodeType(MiBConstant.GENERAL_CODE_HOUSEKEEPING_SETTING_PURGE_TIME_DAYS, MiBConstant.GENERAL_CODE_HOUSEKEEPING_SETTING);
			if(purgeDurationDay != null) {
				PURGE_TIME_DAYS = Integer.parseInt(purgeDurationDay.getGnCodeDescription());
			}
		}catch(Exception e){
			log.catching(e);
		}
		Calendar cal = Calendar.getInstance();
		long expiredMiliUnits = cal.getTimeInMillis() - (PURGE_TIME_DAYS  * 24 * 60 *  60 * 1000);
		cal.setTimeInMillis(expiredMiliUnits);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("Purging records before "+sdf.format(cal.getTime()));
		try{
			int result = houseKeepingDao.purgingVcwsTranx(cal.getTime());
			log.info("Purged "+result+" no. of records from MLEB_OCBC.IBWS_TRANX");
		}catch(Exception e){
			log.catching(e);
		}
		try{
			int result = houseKeepingDao.purgingCustomerEvent(cal.getTime());
			log.info("Purged "+result+" no. of records from MLEB_OCBC.CUSTOMER_EVENT");
		}catch(Exception e){
			log.catching(e);
		}
		try{
			int result = houseKeepingDao.purgingMlebRequestLog(cal.getTime());
			log.info("Purged "+result+" no. of records from MLEB_CORE.MLEB_REQUEST_LOG/MLEB_RESPONSE_LOG");
		}catch(Exception e){
			log.catching(e);
		}
		log.info("Scheduler job for db log housekeeping is ENDED");
	}
}

