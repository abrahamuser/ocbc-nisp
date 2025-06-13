package com.silverlake.mleb.pex.task;





import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.jms.Message;

import net.sf.ehcache.Element;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ibm.jms.JMSBytesMessage;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.mleb.pex.bean.ObEAIHeader;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.eai.EAIService;
import com.silverlake.mleb.pex.entity.BankRoute;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBDAO;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.module.services.RBSServices;
import com.silverlake.mleb.pex.util.EhcacheSession;
import com.silverlake.mleb.pex.util.PropertiesManager;


@Service
public class PExCheckInProgressTask
{
	private static Logger log = LogManager.getLogger(PExCheckInProgressTask.class);
	private PropertiesManager property = new PropertiesManager();
	private static final String KEYLOCK = "CHECK_INPROGRESS_TASK";
	private static final int limitCheck = 20;
	
	@Autowired TaskRenewSession renewSession;
	@Autowired MLEBPExDAO dao;
	
	
	 //@Autowired @Qualifier("responseMessageQueue")
	 private JmsTemplate responseMessageQueue;
	 //@Autowired @Qualifier("requestMessageQueue")
	 private JmsTemplate requestMessageQueue;
		
	
	@Scheduled(fixedDelay=10000)
	public void process()
	{
		try
		{
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
		log.info("PEx Check Collection In Progress Started");
	}
	
	
	public void taskProcess()
	{
		
	
		try
		{
			
			
			PExServices pexServices = new PExServices(dao);
			RBSServices rbsServices = new RBSServices(dao);
		
			BankRoute bankRouteData =  pexServices.getPExBeneficiaryBankCode(PExConstant.FT_BENEFICIARY_BANK_CODE);
			
			
			String findActiveTranxSQL = "FROM HlbPexProcessTranx WHERE status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED+"' AND collection_type = '"+PExConstant.PEX_COLLECTION_TYPE_INTERNET+"'";
			List<PexProcessTranx> hlbPexTranxList = dao.selectQueryLimited(findActiveTranxSQL,limitCheck);
			
			
			for(PexProcessTranx pexTranx : hlbPexTranxList)
			{
				if(pexTranx.getCollectionBankRouteAbbr().equalsIgnoreCase(bankRouteData.getRouteAbbr()))
				{
					//hlb
					log.debug("COLLECTION IN PROGRESS CHECK - HLB");
					ObAccountBean fromAccBean = new ObAccountBean();
					fromAccBean.setAccountNumber(pexTranx.getAccountNo());
					Date transDate = new Date();
					
					String tranxAccType = pexTranx.getAccountProductType();
					String dailyRunningNumber = pexServices.updateSequenceNum(transDate)+"";
					
					//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
					EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
					ObEAIHeader resultRelease = service.performReleaseEarmarkAcc(pexTranx.getRbsholdRef(), fromAccBean, pexTranx.getAmountEarmark(),  pexTranx.getCurrency(), tranxAccType, transDate, pexTranx.getRbsSeqNo(), dailyRunningNumber);
					//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
					
					//String resultRelease = rbsServices.releaseEarmarkKH(false,fromAccBean,tranxAccType, pexTranx.getAmountEarmark(), pexTranx.getCurrency(), pexTranx.getRbsholdRef(), transDate, dailyRunningNumber,30000);
					
					if(resultRelease.getErrorResponseCode()==null)
					{
						log.debug("RBS CHECK RELEASE EARMARK FOR COLLECTION IN PROGRESS ["+resultRelease+"]");
					
					}
					else if(resultRelease.getErrorResponseCode().trim().length()!=0)
					{
						//release earmark failed, amount deducted 
						pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_PAID);
						pexTranx.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
						dao.updateEntity(pexTranx);
						
					}	
					else
					{
						//release earmark success, amount being release, transaction failed
						pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
						pexTranx.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
						dao.updateEntity(pexTranx);
					}
					
					
					
				}
				else
				{
					
					log.debug("COLLECTION IN PROGRESS CHECK - OTHER BANK");
					//pay+
					String responseQueueName = property.getProperty("response.mq").toString();
					String selector = "JMSCorrelationID='"+pexTranx.getEaiCorrelationId()+"'";
					Message msgRs = responseMessageQueue.receiveSelected(responseQueueName, selector);
					
					
					if(msgRs == null)
					{
						
					}
					else
					{
						JMSBytesMessage ibmMsg = (JMSBytesMessage) msgRs;
						//TextMessage msg = (TextMessage) msgRs;
						byte[] msgByte = new byte[(int)ibmMsg.getBodyLength()];
						ibmMsg.readBytes(msgByte);
						String msg = new String(msgByte);
						log.info("EAI RESPONSE \n : "+msg);
						Document doc =  pexServices.stringToDom(msg);
						NodeList rsList = doc.getElementsByTagName("Header");
						org.w3c.dom.Element el_header = (org.w3c.dom.Element) rsList.item(0);
	
						NodeList rsResponseCodeList = el_header.getElementsByTagName("ResponseCode");
						NodeList errorResponseCodeList = el_header.getElementsByTagName("ErrorCode");
						NodeList errorMsgList = el_header.getElementsByTagName("ErrorMsg");
						NodeList reasonCodeList = el_header.getElementsByTagName("ReasonCode");
						NodeList OCMRefNoList = el_header.getElementsByTagName("OCMRefNo");
						
						String responseCode = "";
						String errorResponseCode = "";
						String errorMsg="";
						String ocmRefNo = "";
						String reasonCode = "";
						if(rsResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_responseCode=  (org.w3c.dom.Element) rsResponseCodeList.item(0);
							responseCode = el_responseCode.getTextContent();
						}
						if(errorMsgList.getLength()>0)
						{
							org.w3c.dom.Element el_errorMsgCode=  (org.w3c.dom.Element) errorMsgList.item(0);
							errorMsg = el_errorMsgCode.getTextContent();
						}
						if(reasonCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_reasonCode=  (org.w3c.dom.Element) reasonCodeList.item(0);
							reasonCode = el_reasonCode.getTextContent();
						}
						if(errorResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_AResponse=  (org.w3c.dom.Element) errorResponseCodeList.item(0);
							errorResponseCode = el_AResponse.getTextContent();
						}
						
						if(OCMRefNoList.getLength()>0)
						{
							org.w3c.dom.Element el_ocmRefNo=  (org.w3c.dom.Element) OCMRefNoList.item(0);
							ocmRefNo = el_ocmRefNo.getTextContent();
						}
						
						
						if(responseCode.equalsIgnoreCase("F1"))
						{
							//success
							pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_PAID);
						}
						else
						{
							//failed
							
							NodeList errorCode = el_header.getElementsByTagName("ErrorCode");
							org.w3c.dom.Element el_rerrorCode =  (org.w3c.dom.Element)  errorCode.item(0);
	
							//<ErrorCode>E007</ErrorCode><ErrorMsg>Transaction is rejected</ErrorMsg><ReasonCode>480005</ReasonCode><ARespCode>480005</ARespCode>
							
							pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
							pexTranx.setErrorCode(errorResponseCode);
							pexTranx.setErrorMessage(errorMsg+"["+reasonCode+"]");
							
						}
					
						
						//update status && ib status
						pexTranx.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
						dao.updateEntity(pexTranx);
						
					}
					
					
				}
				
			}
			
			
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	

	



}