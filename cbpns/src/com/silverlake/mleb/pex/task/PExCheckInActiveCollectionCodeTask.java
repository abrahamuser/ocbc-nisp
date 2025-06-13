package com.silverlake.mleb.pex.task;





import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexCollectionCode;
import com.silverlake.mleb.pex.entity.PexConf;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.services.PExServices;


@Service
public class PExCheckInActiveCollectionCodeTask
{
	private static Logger log = LogManager.getLogger(PExCheckInActiveCollectionCodeTask.class);

	private static final String KEYLOCK = "EXPIRYLOCK_COLLECTIONCODE_TASK";
	private static final int limitDel = 50;
	@Autowired TaskRenewSession renewSession;
	@Autowired MLEBPExDAO dao;

	//@Scheduled(cron = "0 */3 16,0-5 * * ?")
	//@Scheduled(cron = "0 */5 * * * ?")
	@Scheduled(fixedDelay=300000)
	public void process()
	{
		try
		{
			log.info("TASK TRIGGER ----- ");
			/*EhcacheSession echache = new EhcacheSession();
			Element cacheData = echache.getLockTask().putIfAbsent(new Element(KEYLOCK,"1"));*/
			boolean data = renewSession.checkTask(KEYLOCK);
			if(data)
			{
				Future<String>future  = renewSession.processTask(KEYLOCK);
				try
				{
					taskProcess();
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}

				
				future.cancel(true);

				//boolean removeKey = echache.getLockTask().remove(KEYLOCK);
				
			}
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//taskProcess();
	}
	


	
	@PostConstruct
	public void initIt() throws Exception {
		log.info("PEx Check Collection Code Started");
	}
	
	
	public void taskProcess()
	{
		
	
		try
		{
			//log.info("Initializing PEx Expiration Check...");
		

			PExServices pexServices = new PExServices(dao);
			String findActiveTranxSQL = "FROM HlbPexCollectionCode";
			
			List<PexCollectionCode> hlbPexCollectionCode = dao.selectQueryLimited(findActiveTranxSQL,limitDel);
			
			String pexRef = "";
			
			for(PexCollectionCode pexTranx : hlbPexCollectionCode)
			{
				
				
				pexRef = pexRef+" OR ref_no = '"+pexTranx.getRefNo()+"'";
				
			}
			
			pexRef = pexRef.length()>2?pexRef.substring(3):null;
			
			
			
			if(null!=pexRef)
			{
				SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.PEX_REF_DATE_FORMAT);
				Integer collectionCodeReservePeriod = null;
				//Retrieve Collection Code Reserve Period (day)
				PexConf pexConf = pexServices.getPExConf();
				if(null!=pexConf)
					collectionCodeReservePeriod = pexConf.getCollectionReservePeriod();
				
				String sqlPexTranx = "From HlbPexProcessTranx WHERE ("+pexRef+")";
				List<PexProcessTranx> pexList = dao.selectQuery(sqlPexTranx);
				
				String deletePex = "";
				for(PexProcessTranx pexTranx:pexList)
				{
					if(!pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_ACTIVE) && !pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING)  )
					{
						boolean proceedDelete = false;
						//log.debug("collectionCodeReservePeriod: " + collectionCodeReservePeriod);
						if(null!=collectionCodeReservePeriod)
						{
							//Delete if over reserve day
							String pexDayString = pexTranx.getRefNo().substring(5,13);
							Date pexDate = refFormat.parse(pexDayString);
							Date currentDate = refFormat.parse(refFormat.format(new Date()));
							
							Calendar cal = Calendar.getInstance();  
							cal.setTime(pexDate);  
							cal.add(Calendar.DAY_OF_MONTH, collectionCodeReservePeriod);
							Date endDate = cal.getTime();
							
							if(currentDate.compareTo(endDate) > 0)
							{
								proceedDelete = true;
								log.debug("proceedDelete: " + proceedDelete);
							}
						}
						else
							proceedDelete = true;
						
						if(proceedDelete)
							deletePex = deletePex + " OR ref_no = '"+pexTranx.getRefNo()+"' ";
					}
				}
				
				
				for(PexCollectionCode pexTranx : hlbPexCollectionCode)
				{
					if(!isExistPExRef(pexList,pexTranx.getRefNo()))
					{
						String pexDay = pexTranx.getRefNo().substring(5,13);
						Date currentDate = new Date();
						String currentDay = refFormat.format(currentDate);
					
						if(!pexDay.equalsIgnoreCase(currentDay))
						{
							deletePex = deletePex + " OR ref_no = '"+pexTranx.getRefNo()+"' ";
						}
						
					}
				}
				
				
				
				deletePex = deletePex.length()>2?deletePex.substring(3):null;
				
				if(null!=deletePex)
				{
				
					String sqlDelCollectionCode = "DELETE From HlbPexCollectionCode WHERE ("+deletePex+")";
					dao.updateSQL(sqlDelCollectionCode);
				}
				
			}
			
			
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	
	public boolean isExistPExRef(List<PexProcessTranx> pexList, String pexRef)
	{
		
		for(PexProcessTranx pexTranx:pexList)
		{
			if(pexRef.equalsIgnoreCase(pexTranx.getRefNo()))
			{
				return true;
			}
		}
		
		
		return false;
	}
	
	



}