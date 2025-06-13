package com.silverlake.mleb.pex.task;





import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.mleb.pex.bean.ObEAIHeader;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.eai.EAIService;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.rbs.services.ReleaseEarmarkServices;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.module.services.RBSServices;



@Service
public class PExCheckExpirationTask
{
	private static Logger log = LogManager.getLogger(PExCheckExpirationTask.class);

	private static final String KEYLOCK = "EXPIRYLOCK_EXPIRATION_TASK";
	@Autowired TaskRenewSession renewSession;
	@Autowired MLEBPExDAO dao;
	
	 //@Autowired @Qualifier("requestMessageQueue")
	 private JmsTemplate requestMessageQueue;
	 
	 //@Autowired @Qualifier("responseMessageQueue")
	 private JmsTemplate responseMessageQueue;
	
	@Scheduled(fixedDelay=10000)
	public void process()
	{
		try
		{
		/*	EhcacheSession echache = new EhcacheSession();
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
		log.info("PEx Expiration Check Started");
	}
	
	
	public void taskProcess()
	{
		
	
		try
		{
			
			PExServices pexServices = new PExServices(dao);
			String findActiveTranxSQL = "FROM HlbPexProcessTranx WHERE status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"'";
			List<PexProcessTranx> hlbPexTranxList = dao.selectQuery(findActiveTranxSQL);
			
			for(PexProcessTranx pexTranx : hlbPexTranxList)
			{
				int rs = pexServices.updatePexExpiration(pexTranx);
				if(rs>0)
				{
					Date currentDate = new Date();
					
					//SIT TESTING USING FIX DATE  ----START
					
					
					
					
					currentDate = pexServices.checkRBSDate(currentDate);
					
					//SIT TESTING USING FIX DATE  ----END
					
					
					RBSServices rbsServices = new RBSServices(dao);
					ObAccountBean fromAccBean = new ObAccountBean();
					fromAccBean.setAccountNumber(pexTranx.getAccountNo());
					String tranxAccType = pexTranx.getAccountProductType();
					String dailyRunningNumber = pexServices.updateSequenceNum(currentDate)+"";
					
					//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
					EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
					ObEAIHeader resultRelease = service.performReleaseEarmarkAcc(pexTranx.getRbsholdRef(), fromAccBean, pexTranx.getAmountEarmark(),  pexTranx.getCurrency(), tranxAccType, currentDate, pexTranx.getRbsSeqNo(), dailyRunningNumber);
					//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
					
					
					//String resultRelease = rbsServices.releaseEarmarkKH(false,fromAccBean,tranxAccType, pexTranx.getAmountEarmark(), pexTranx.getCurrency(), pexTranx.getRbsholdRef(), currentDate, dailyRunningNumber,30000);
				
					
					
					//ReleaseEarmarkServices releaseEmk = new ReleaseEarmarkServices();
					//releaseEmk.performReleaseEarmarkAcc("9082", dailyRunningNumber, fromAccBean.getAccountNumber(), pexTranx.getAmountEarmark(), pexTranx.getCurrency(), payeeRef, new Date(), pexTranx.getRbsholdRef());
				}
			}
			
			
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	

	



}