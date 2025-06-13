package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.entity.AOImageDocCacheData;
//import com.silverlake.mleb.mib.entity.dao.AOCacheInputDao;
//import com.silverlake.mleb.mib.entity.dao.MLEBMIBDAO;
//import com.silverlake.mleb.mib.module.common.MiBServices;
//@Service
//public class CheckAccountOnBoardingImageDocCache
//{
//	private static Logger log = LogManager.getLogger(CheckAccountOnBoardingImageDocCache.class);
//
//	@Autowired
//	MLEBMIBDAO dao;
//
//	@Autowired
//	AOCacheInputDao cacheDao;
//
//	@Autowired TaskRenewSession renewSession;
//
//	@Autowired 
//	ApplicationContext appContext;
//
//	@Scheduled(fixedDelay=(60000*25)) //Run every 25 min
//	public void process()
//	{
//		MiBServices mibService = new MiBServices(dao);
//		try{
//			List<AOImageDocCacheData> aoImageDocCacheData = cacheDao.getALLAOImageData();
//			log.info("Account On Boarding Image Cache Checking Start");
//			if(aoImageDocCacheData!=null && !aoImageDocCacheData.isEmpty()){
//				for(AOImageDocCacheData aoImageDocCacheDataInfo : aoImageDocCacheData){
//					Date currentTime = Calendar.getInstance().getTime();
//					long differenceInMillis = currentTime.getTime() - aoImageDocCacheDataInfo.getCreatedTime().getTime();
//					long differenceInHours = (differenceInMillis) / 1000L / 60L / 60L; // Divide by millis/sec, secs/min, mins/hr
//					log.info("difference In Hours (IMAGE CACHE):: " + differenceInHours);
//					
//					if(aoImageDocCacheDataInfo.getStatus().equalsIgnoreCase(MiBConstant.ITH_STATUS_PENDING) && differenceInHours >= mibService.getFinalCacheExpiredHour()){
//						cacheDao.deleteAOImageData(aoImageDocCacheDataInfo.getRowId(), aoImageDocCacheDataInfo.getUuid());
//						
//						log.info("REMOVED IMAGE CACHE :: " + aoImageDocCacheDataInfo.getRowId());
//					}else if(aoImageDocCacheDataInfo.getStatus().equalsIgnoreCase(MiBConstant.ITH_STATUS_SUCCESS) && differenceInHours >= mibService.getNonFinalCacheExpiredHour()){
//						cacheDao.deleteAOImageData(aoImageDocCacheDataInfo.getRowId(), aoImageDocCacheDataInfo.getUuid());
//						
//						log.info("REMOVED IMAGE CACHE :: " + aoImageDocCacheDataInfo.getRowId());
//					}
//				}
//			}
////			SimpleDateFormat myFormat = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
////			Calendar calendarTime = Calendar.getInstance();  
////			calendarTime.add(Calendar.HOUR, -mibService.getFinalCacheExpiredHour());
////			Date currentTime = calendarTime.getTime();
////			cacheDao.deleteAOImageData(myFormat.format(currentTime));
//			log.info("Account On Boarding IMAGE Cache Checking End");
//		}
//		catch(Exception e)
//		{
//			log.info("EXCEPTION PROCESSING CACHE LISTS :: "+e);
//			e.printStackTrace();
//		}
//
//
//		//log.info("Cache Checking every 1 minute");
//
//	}
//}
//
