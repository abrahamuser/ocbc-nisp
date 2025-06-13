package com.silverlake.mleb.pex.task;





import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.listerner.SMSNotification;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.util.PropertiesManager;


@Service
public class PExCheckSMSNotification
{
	private static Logger log = LogManager.getLogger(PExCheckSMSNotification.class);

	private static final String KEYLOCK = "SMS_NOTIFICATION_TASK";
	private static final int limitDel = 5;
	private PropertiesManager property = new PropertiesManager();
	
	
	@Autowired SMSNotification smsNotifiy;
	
	@Autowired MLEBPExDAO dao;
	@Autowired TaskRenewSession renewSession;
	@Scheduled(fixedDelay=1000*60*2)
	public void process()
	{
		try
		{
			//log.info("TASK TRIGGER ----- ");
			
			
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
		log.info("PEx SMS Notification Check Started");
	}
	
	
	public void taskProcess()
	{
		
	
		try
		{
			//log.info("Initializing PEx Expiration Check...");

			PExServices pexServices = new PExServices(dao);
			//normal pex creation success
			String findActiveTranxSQL1 = "FROM HlbPexProcessTranx WHERE STATUS = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"' AND sms_creation_flag='"+PExConstant.PEX_SMS_FLAG_PENDING+"' AND collection_type='"+PExConstant.PEX_COLLECTION_TYPE_INTERNET+"'";
			
			//direct pex paid success
			String findActiveTranxSQL2 = "FROM HlbPexProcessTranx WHERE STATUS = '"+PExConstant.PEX_TRANSACTION_STATUS_PAID+"' AND sms_creation_flag='"+PExConstant.PEX_SMS_FLAG_PENDING+"' AND collection_type='"+PExConstant.PEX_COLLECTION_TYPE_DIRECT+"'";
			
			
			//pex cancelled alert
			String findActiveTranxSQL3 = "FROM HlbPexProcessTranx WHERE STATUS = '"+PExConstant.PEX_TRANSACTION_STATUS_CANCELLED+"' AND sms_cancel_flag='"+PExConstant.PEX_SMS_FLAG_PENDING+"'";
			
			
			//pex failed attempt 3 times alert
			String findActiveTranxSQL4 = "FROM HlbPexProcessTranx WHERE STATUS = '"+PExConstant.PEX_TRANSACTION_STATUS_SUSPENDED+"' AND sms_suspend_flag='"+PExConstant.PEX_SMS_FLAG_PENDING+"' ";
			
			
			
			//atm pex paid success
			String findActiveTranxSQL5 = "FROM HlbPexProcessTranx WHERE STATUS = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"' AND sms_creation_flag='"+PExConstant.PEX_SMS_FLAG_PENDING+"' AND collection_type='"+PExConstant.PEX_COLLECTION_TYPE_ATM+"'";
			
		
			
			
			
		
			log.info("SMS RESEND TASK");
			
		/*	PTA_MAKE_PEX_INTERNET sms001=<Name> has PEx you RM<Amount>.<Ref No>. Call sender for code & claim at www.HongLeongConnect.my within <xdays> days. <Messsage>
			PTA_MAKE_PEX_DIRECT sms002=<Name> has PEx you RM<Amount>.<Ref No>. Amount has been transferred to your PEx Direct Account : <Acct No>. <Message>
			PTA_PEX_CANCEL sms003=PEx <Ref No> from <Name> has been cancelled by Sender
			PTA_PEX_COLLECT_FAILED sms004=PEx Collection.<Ref No> has failed as mobile number or/and amount was entered incorrectly
		*/
			
			
			
			//PExServices pex = new PExServices(dao);
			//MuleClient mClient = new MuleClient(MuleDispatcher.getMuleContext());
			
			List<PexProcessTranx> tranx1 = dao.selectQuery(findActiveTranxSQL1) ;
			//log.debug("SMS001 RESEND COUNT : "+tranx1.size());
			for(PexProcessTranx tranx : tranx1)
			{
				List<Object> dataList = new ArrayList();
				dataList.add("1");
				dataList.add(tranx);
				
				smsNotifiy.onCall(dataList);
				
				//mClient.sendNoReceive("vm://smsNotification", dataList, null);
				Thread.sleep(100);
			}
			
			
			
			List<PexProcessTranx> tranx2 = dao.selectQuery(findActiveTranxSQL2) ;
			//log.debug("SMS002 RESEND COUNT : "+tranx2.size());
			for(PexProcessTranx tranx : tranx2)
			{
				List<Object> dataList = new ArrayList();
				dataList.add("2");
				dataList.add(tranx);
				smsNotifiy.onCall(dataList);
				//mClient.sendNoReceive("vm://smsNotification", dataList, null);
				Thread.sleep(100);
			}
			
			
			
			List<PexProcessTranx> tranx3 = dao.selectQuery(findActiveTranxSQL3) ;
			//log.debug("SMS003 RESEND COUNT : "+tranx3.size());
			for(PexProcessTranx tranx : tranx3)
			{
				List<Object> dataList = new ArrayList();
				dataList.add("3");
				dataList.add(tranx);
				smsNotifiy.onCall(dataList);
				//mClient.sendNoReceive("vm://smsNotification", dataList, null);
				Thread.sleep(100);
			}
			
		
			
			
			List<PexProcessTranx> tranx4 = dao.selectQuery(findActiveTranxSQL4) ;
			//log.debug("SMS004 RESEND COUNT : "+tranx4.size());
			for(PexProcessTranx tranx : tranx4)
			{
				List<Object> dataList = new ArrayList();
				dataList.add("4");
				dataList.add(tranx);
				smsNotifiy.onCall(dataList);
				//mClient.sendNoReceive("vm://smsNotification", dataList, null);
				Thread.sleep(100);
			}
			
			
			
			
			List<PexProcessTranx> tranx5 = dao.selectQuery(findActiveTranxSQL5) ;
			//log.debug("SMS005 RESEND COUNT : "+tranx5.size());
			for(PexProcessTranx tranx : tranx5)
			{
				List<Object> dataList = new ArrayList();
				dataList.add("5");
				dataList.add(tranx);
				smsNotifiy.onCall(dataList);
				//mClient.sendNoReceive("vm://smsNotification", dataList, null);
				Thread.sleep(100);
			}
			
		
			
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	
	
	

	



}