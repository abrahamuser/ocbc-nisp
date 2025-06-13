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
import com.silverlake.mleb.mcb.entity.dao.RegCacheDao;
import com.silverlake.mleb.mcb.util.PropertiesManager;

/**
 * This scheduler to clear Registration cache data

 *
 */
@Service
public class ClearRegCacheData{
	private static Logger log = LogManager.getLogger(ClearRegCacheData.class);
	@Autowired
	RegCacheDao regCacheDao;
	
	private PropertiesManager pro = new PropertiesManager();
	
	@Scheduled(cron="0 0 4 * * *")//Run at 04:00 daily.

	public void process(){
		String isSchedulerNode = pro.getProperty(MiBConstant.IS_SCHEDULER_MODE);
		if(isSchedulerNode == null || !isSchedulerNode.equalsIgnoreCase("true")) {
			return;
		}
		log.info("Scheduler job for db Reg cache data is STARTED");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    	Calendar cal = Calendar.getInstance();
			cal = Calendar.getInstance();
			cal.add(Calendar.HOUR, -2);
			System.out.println(sdf.format(cal.getTime()));
		try{
			int nResult = regCacheDao.removeRegCache(cal.getTime());
			log.info("nResult="+nResult);
		}catch(Exception e){
			log.catching(e);
		}
		log.info("Scheduler job for db Reg cache data is ENDED");
	}
}

