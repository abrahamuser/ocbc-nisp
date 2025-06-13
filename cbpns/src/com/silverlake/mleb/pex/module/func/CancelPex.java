package com.silverlake.mleb.pex.module.func;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fuzion.ws.common.endpoint.CutOffTimeResponse;
import com.fuzion.ws.security.endpoint.EndpointResponse;
import com.fuzion.ws.security.endpoint.SendNotificationForPreLoginRequest;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObEAIHeader;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.eai.EAIService;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.commonServices.CommoService;
import com.silverlake.mleb.pex.module.ib.securityServices.SecuirtyService;
import com.silverlake.mleb.pex.module.rbs.services.ReleaseEarmarkServices;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.module.services.RBSServices;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPERRResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.HLBDateUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.StringDataUtil;






@Service
public class CancelPex extends FuncServices 
{

	private static Logger log = LogManager.getLogger(CancelPex.class);
	private PropertiesManager property = new PropertiesManager();
	
	@Autowired
	MLEBPExDAO dao;
	
	
	@Autowired ApplicationContext appContext;
	
	 //@Autowired @Qualifier("requestMessageQueue")
	 private JmsTemplate requestMessageQueue;
		 
	//@Autowired @Qualifier("responseMessageQueue")
	private JmsTemplate responseMessageQueue;
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		//StringDataUtil stringUtil = new StringDataUtil();
		EncryptionServices encryptService = new EncryptionServices();
		
		MICBResponseBean response = new MICBResponseBean();
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		StringDataUtil stringUtil = new StringDataUtil();
		
		try {

			SimpleDateFormat dbFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			String cif = pexRequest.getObUser().getCifNumber();
			String collectionCode = pexRequest.getPexTransactionDetails().getCollectionCode();
			String pexRefNo = pexRequest.getPexTransactionDetails().getReferenceNumber();
			char[] hexString = Hex.encodeHex(PerformCreationPex.collectionEncryptionKey.getBytes());
			String encryptedCollectionCode = encryptService.encrypt(String.valueOf(hexString).getBytes(), collectionCode.getBytes());
			PExServices pexServices = new PExServices(dao);
			Date cancelDate = new Date();
			//SIT TESTING USING FIX DATE  ----START
			
			cancelDate = pexServices.checkRBSDate(cancelDate);
			//SIT TESTING USING FIX DATE  ----END
			
			
			CommoService commonService = new CommoService(appContext);
			CutOffTimeResponse cutResp = commonService.getCutOffTime(pexRequest.getUserContext(),  arg0.getTranxID());
			if(cutResp.getResponse().getStatusCode() == 0)
			{
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+cutResp.getResponse().getErrorCode());
			}
			else
			{
				String cancelCollectionSQL = "UPDATE HlbPexProcessTranx SET status = '"+PExConstant.PEX_TRANSACTION_STATUS_CANCELLED+"' , cancelled_date = '"+dbFormat.format(cancelDate)+"'  WHERE collection_no = '"+encryptedCollectionCode+"' AND cif='"+cif+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"' AND ref_no = '"+pexRefNo+"'";
				
				
				int updateRs = dao.updateSQL(cancelCollectionSQL);
				
				
				if(updateRs == 1)
				{
					pexResponse.setPexTransactionDetails(pexRequest.getPexTransactionDetails());
					
					RBSServices rbsServices = new RBSServices(dao);
					
					
					
					ObAccountBean fromAccBean = pexRequest.getPexTransactionDetails().getFromAccount();

					String tranxAccType = fromAccBean.getAccountTypeCode();
					String dailyRunningNumber = pexServices.updateSequenceNum(cancelDate)+"";
					PexProcessTranx pexTransactionData = pexServices.getPExTransactionByRef(pexRequest.getPexTransactionDetails().getReferenceNumber());
					//String resultRelease = rbsServices.releaseEarmarkKH(false,fromAccBean,tranxAccType, pexTransactionData.getAmountEarmark(), pexRequest.getPexTransactionDetails().getCurrency(), pexTransactionData.getRbsholdRef(), cancelDate, dailyRunningNumber,30000);
					
					//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
					EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
					ObEAIHeader resultRelease = service.performReleaseEarmarkAcc(arg0.getTranxID(), pexResponse.getPexTransactionDetails().getFromAccount(), new BigDecimal(pexResponse.getPexTransactionDetails().getAmount()), pexResponse.getPexTransactionDetails().getCurrency(), tranxAccType, cancelDate, pexTransactionData.getRbsSeqNo(), dailyRunningNumber);
					//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
							
					if(resultRelease.getErrorResponseCode()==null)
					{
						//time out
						//updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_FAILED,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean);
						//pexResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_IN_PROGRESS);
						
						if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
							pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
						}else{
							pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
						}
					
						
						
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
						pexResponse.getPexTransactionDetails().setErrorMessage(MiBConstant.MIB_EAI_ERROR_PREFIX+resultRelease.getReasonCode());
						
					}
					else if(resultRelease.getErrorResponseCode().trim().length()!=0)
					{
						
				
						//failed earmark to RBS
						if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
							pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
						}else{
							pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
						}
						
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
						
						pexResponse.getPexTransactionDetails().setErrorMessage(MiBConstant.MIB_EAI_ERROR_PREFIX+resultRelease.getReasonCode());
	
					}
					else
					{
						//SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST);
						pexResponse.getPexTransactionDetails().setStatus(pexTransactionData.getStatus());
						pexResponse.getPexTransactionDetails().setCancelDate(HLBDateUtil.getTransactionDateByLocale(pexTransactionData.getCancelledDate(), pexRequest.getUserContext().getLocaleCode()));
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
						//successfull
						String sms003 = property.getProperty("sms003");
						String firstName =  stringUtil.getLimitedChac(15, 0, pexResponse.getPexTransactionDetails().getSenderName());
						sms003 = sms003.replaceAll("<Name>", firstName);
						sms003 = sms003.replaceAll("<Amount>", pexResponse.getPexTransactionDetails().getAmount());
						sms003 = sms003.replaceAll("<Ref No>", pexResponse.getPexTransactionDetails().getReferenceNumber());
						sms003 = sms003.replaceAll("<Acct No>", pexResponse.getPexTransactionDetails().getCollectionAccount().getAccountNumber());
						sms003 = sms003.replaceAll("<Message>",  pexResponse.getPexTransactionDetails().getRemark());
						sms003 = sms003.replaceAll("<datetime>",  pexResponse.getPexTransactionDetails().getDatetime());  
						try
						{
						
							SecuirtyService secService = new SecuirtyService(appContext);
							SendNotificationForPreLoginRequest notificationRequest = new SendNotificationForPreLoginRequest();
						
							notificationRequest.setMessageTemplateCode("PTA_PEX_CANCEL");
							notificationRequest.setMessageBody(sms003);
							notificationRequest.setReferenceId(arg0.getTranxID());
							notificationRequest.setCifId(pexRequest.getObUser().getCifNumber());
							notificationRequest.setMobileNumber(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
							EndpointResponse respEndPoint = secService.sendPreLoginNotification(pexRequest.getUserContext(), notificationRequest, arg0.getTranxID());
							if(respEndPoint.getResponse().getStatusCode()==1)
							{
								String cancelCollectionSMSSQL = "UPDATE HlbPexProcessTranx SET sms_cancel_flag = '"+PExConstant.PEX_SMS_FLAG_SENT+"' WHERE ref_no = '"+pexResponse.getPexTransactionDetails().getReferenceNumber()+"' AND STATUS='"+PExConstant.PEX_TRANSACTION_STATUS_CANCELLED+"'";
								dao.updateSQL(cancelCollectionSMSSQL);
								//updateSMSFlag(true,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
							else
							{
								String cancelCollectionSMSSQL = "UPDATE HlbPexProcessTranx SET sms_cancel_flag = '"+PExConstant.PEX_SMS_FLAG_PENDING+"' WHERE ref_no = '"+pexResponse.getPexTransactionDetails().getReferenceNumber()+"' AND STATUS='"+PExConstant.PEX_TRANSACTION_STATUS_CANCELLED+"'";
								dao.updateSQL(cancelCollectionSMSSQL);
								//updateSMSFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
						
						}catch(Exception e)
						{
							String cancelCollectionSMSSQL = "UPDATE HlbPexProcessTranx SET sms_cancel_flag = '"+PExConstant.PEX_SMS_FLAG_PENDING+"' WHERE ref_no = '"+pexResponse.getPexTransactionDetails().getReferenceNumber()+"' AND STATUS='"+PExConstant.PEX_TRANSACTION_STATUS_CANCELLED+"'";
							dao.updateSQL(cancelCollectionSMSSQL);
							
						}
					}
					
					
					
					
					
					
				}
				else
				{
					String findRejectedCollection = "FROM HlbPexProcessTranx WHERE collection_no = '"+encryptedCollectionCode+"' AND cif='"+cif+"' AND ref_no = '"+pexRefNo+"'";
					
					List<PexProcessTranx> pexTranxs =  dao.selectQuery(findRejectedCollection);
					
					if(pexTranxs.size()==0)
					{
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTION);
						
						
						
						
					}
					else
					{
						PexProcessTranx pexTranx = pexTranxs.get(0);
						
						if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_EXPIRED))
						{
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_EXPIRED);
						}
						else if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING))
						{
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_PROCESSING);
						}
						else if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PAID))
						{
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_PAID);
						}
						else if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_CANCELLED))
						{
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COLLECTION_CANCELLED);
						}
						else
						{
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTION);
						}
						
					
					}
					

				}
			}
			
			
			
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			log.error(this.getClass().toString(), e);
			pexResponse = new ObPexResponse();
			pexResponse.setObHeader(new ObHeaderResponse());
			pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
			pexResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(pexResponse);
		
		return response;
	}


	 
	/*MuleContext muleContext;
	


	@Override
	public MuleContext getMuleContext() {
		// TODO Auto-generated method stub
		return muleContext;
	}


	@Override
	public void setMuleContext(MuleContext arg0) {
		// TODO Auto-generated method stub
		
		
		
		
		muleContext = arg0;
	}*/
	
	
	
	
}