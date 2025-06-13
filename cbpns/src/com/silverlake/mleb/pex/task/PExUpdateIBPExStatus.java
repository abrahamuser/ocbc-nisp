package com.silverlake.mleb.pex.task;





import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fuzion.ws.transaction.endpoint.PexRequest;
import com.fuzion.ws.transaction.endpoint.PexUpdateRequest;
import com.fuzion.ws.transaction.endpoint.TransactionResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.entity.HlbCustomerProfile;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.ib.TransactionServices.TransactionServices;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.util.PropertiesManager;


@Service
public class PExUpdateIBPExStatus
{
	private static Logger log = LogManager.getLogger(PExUpdateIBPExStatus.class);

	private static final String KEYLOCK = "UPDATE_IB_PEx_TASK";
	private PropertiesManager property = new PropertiesManager();
	@Autowired TaskRenewSession renewSession;
	@Autowired MLEBPExDAO dao;


	

	@Scheduled(fixedDelay=10000)
	public void process()
	{
	

		try
		{

			boolean data = renewSession.checkTask(KEYLOCK);
			
			//log.info("Scheduler RUN");
			
			if(data)
			{
				//log.info("Scheduler Execute");
				
				
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

	}
	


	
	@PostConstruct
	public void initIt() throws Exception {
		log.info("PEx Update IB Status Started");
	}
	
	
	public void taskProcess()
	{
		
	
		try
		{
			//log.info("Initializing PEx Expiration Check...");

			PExServices pexServices = new PExServices(dao);
			//normal pex creation success
			String findActiveTranxSQL1 = "FROM HlbPexProcessTranx WHERE update_ib_flag='"+PExConstant.PEX_IB_FLAG_PENDING_UPDATED+"'";
			
			List<PexProcessTranx> processTranxList = dao.selectQuery(findActiveTranxSQL1);
			
			for(PexProcessTranx process: processTranxList)
			{
				String ibRefID = pexServices.getSystemSequenceNum(new Date());
				if(process.getUpdateIBFlag().equalsIgnoreCase(PExConstant.PEX_IB_FLAG_PENDING_ADD))
				{
					try
					{
						TransactionServices tranService = new TransactionServices(null);
						PexRequest PexRequest = new PexRequest();
						PexRequest.setDescription(process.getRemarkMessage());
						PexRequest.setFromAccountNumber(process.getAccountNo());
						PexRequest.setFromCurrencyCode(process.getCurrency());
						PexRequest.setFromProductTypeCode(process.getAccountProductType());
						PexRequest.setMobileNumber(process.getMobileNo());
						PexRequest.setReferenceNumber(process.getRefNo());
						PexRequest.setSenderName(process.getSenderName());
						PexRequest.setServiceChargeAmount(process.getAmountCharges());
						PexRequest.setTransactionAmount(process.getAmountPex());
						
						GregorianCalendar gc = new GregorianCalendar();
			            gc.setTimeInMillis(process.getCreationDate().getTime());
			            XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
			                    .newXMLGregorianCalendar(
			                        gc);
			         
						PexRequest.setTransactionDate(calendar);
						PexRequest.setTransactionStatusCode(process.getStatus());
						
						ObUserContext userContext = new ObUserContext();
						userContext.setLoginId(((HlbCustomerProfile)dao.findByID(HlbCustomerProfile.class, process.getCif())).getLoginId());
						userContext.setCountryCode(property.getProperty("pexCollectCountry"));
						userContext.setLocaleCode(property.getProperty("pexCollectLocale"));
						TransactionResponse updatePexIB = tranService.insertIBPExTransaction(userContext, PexRequest, ibRefID);
					
						if(updatePexIB.getResponse().getStatusCode()==1)
						{
							updateIBInsertFlag(true,process.getRefNo());
						}
						else
						{
							updateIBInsertFlag(false,process.getRefNo());
						}
						dao.insertEntity(tranService.getIbwsLog());
					}catch(Exception ex)
					{
						updateIBInsertFlag(false,process.getRefNo());
					}
				}
				else
				{
					try
					{
						TransactionServices tranService = new TransactionServices(null);
						
						PexUpdateRequest PexRequest = new PexUpdateRequest();
						PexRequest.setServiceChargeAmount(process.getAmountCharges());
						PexRequest.setTransactionStatusCode(process.getStatus());
						PexRequest.setTransactionId(process.getUpdateIBId());
						
						/*PexRequest PexRequest = new PexRequest();
						PexRequest.setDescription(process.getRemarkMessage());
						PexRequest.setFromAccountNumber(process.getAccountNo());
						PexRequest.setFromProductTypeCode(process.getAccountProductType());
						PexRequest.setMobileNumber(process.getMobileNo());
						PexRequest.setReferenceNumber(process.getRefNo());
						PexRequest.setSenderName(process.getSenderName());
						PexRequest.setServiceChargeAmount(process.getAmountCharges());
						PexRequest.setTransactionAmount(process.getAmountPex());
						
						GregorianCalendar gc = new GregorianCalendar();
			            gc.setTimeInMillis(process.getCreationDate().getTime());
			            XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
			                    .newXMLGregorianCalendar(
			                        gc);
			         
						PexRequest.setTransactionDate(calendar);
						PexRequest.setTransactionStatusCode(process.getStatus());*/
						
						ObUserContext userContext = new ObUserContext();
						userContext.setLoginId(((HlbCustomerProfile)dao.findByID(HlbCustomerProfile.class, process.getCif())).getLoginId());
						userContext.setCountryCode(property.getProperty("pexCollectCountry"));
						userContext.setLocaleCode(property.getProperty("pexCollectLocale"));
						
						userContext.setLoginId(((HlbCustomerProfile)dao.findByID(HlbCustomerProfile.class, process.getCif())).getLoginId());
						TransactionResponse updatePexIB = tranService.updateIBPExTransaction(userContext, PexRequest, ibRefID);
						
						if(updatePexIB.getResponse().getStatusCode()==1)
						{
							updateIBModifyFlag(true,process.getRefNo(),process.getUpdateIBId());
						}
						else
						{
							updateIBModifyFlag(false,process.getRefNo(),process.getUpdateIBId());
						}
						dao.insertEntity(tranService.getIbwsLog());
					}catch(Exception ex)
					{
						log.info("Exception ::: ",ex);
						updateIBModifyFlag(false,process.getRefNo(),process.getUpdateIBId());
					}
				}
				
				
			}
			
			
			
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	public void updateIBInsertFlag(boolean success, String refID)
	{
		
		String updateIBTransSQL = "UPDATE HlbPexProcessTranx SET update_ib_flag = '<ibFlagStatus>' WHERE ref_no='"+refID+"' AND  update_ib_flag = '"+PExConstant.PEX_IB_FLAG_PENDING_ADD+"'"; 
		
		if(success)
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<ibFlagStatus>", PExConstant.PEX_IB_FLAG_ADDED);
		}
		else
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<ibFlagStatus>", PExConstant.PEX_IB_FLAG_PENDING_ADD);
		}
		
		dao.updateSQL(updateIBTransSQL);
		
	}
	
	
	
	public void updateIBModifyFlag(boolean success, String refID, String tranxId)
	{
		
		String updateIBTransSQL = "UPDATE HlbPexProcessTranx SET update_ib_flag = '<ibFlagStatus>' , update_ib_id='"+tranxId+"' WHERE ref_no='"+refID+"' AND  update_ib_flag = '"+PExConstant.PEX_IB_FLAG_PENDING_UPDATED+"'"; 
		
		if(success)
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<ibFlagStatus>", PExConstant.PEX_IB_FLAG_UPDATED);
		}
		else
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<ibFlagStatus>", PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
		}
		
		dao.updateSQL(updateIBTransSQL);
		
	}
	
	
	
	
	
	

	



}