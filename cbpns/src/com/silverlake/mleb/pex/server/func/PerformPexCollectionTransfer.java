package com.silverlake.mleb.pex.server.func;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fuzion.ws.transaction.endpoint.PexUpdateRequest;
import com.fuzion.ws.transaction.endpoint.TransactionResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.ibm.jms.JMSBytesMessage;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.hlb.mib.entity.HlbCustomerProfile;
import com.silverlake.mleb.pex.bean.ObAccountInquiryBean;
import com.silverlake.mleb.pex.bean.ObEAIHeader;
import com.silverlake.mleb.pex.bean.ObEarmarkBean;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.eai.EAIService;
import com.silverlake.mleb.pex.entity.BankRoute;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.func.PerformCreationPex;
import com.silverlake.mleb.pex.module.ib.TransactionServices.TransactionServices;
import com.silverlake.mleb.pex.module.rbs.services.AccountInquiryServices;
import com.silverlake.mleb.pex.module.rbs.services.EarmarkServices;
import com.silverlake.mleb.pex.module.rbs.services.ReleaseEarmarkServices;
import com.silverlake.mleb.pex.module.rbs.services.TransferServices;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.module.services.RBSServices;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPDAResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPERRResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPFBResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.PropertiesManager;

/*import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;*/
import org.apache.commons.codec.binary.Hex;


@Service
public class PerformPexCollectionTransfer extends PExFunc{

	
	 //@Autowired @Qualifier("requestMessageQueue")
	 private JmsTemplate requestMessageQueue;
	 
	 //@Autowired @Qualifier("responseMessageQueue")
	 private JmsTemplate responseMessageQueue;
	
	
	
	private static Logger log = LogManager.getLogger(PerformPexCollectionTransfer.class);
	@Autowired MLEBPExDAO dao;
	
	@Autowired ValidatePexCollection validatePexCollection;
	@Autowired RetrieveBeneficiaryDetails retrieveBeneficiary;
	
	private PropertiesManager property = new PropertiesManager();
	
	@Override
	public WSResponse processService(WSRequest wsRequest) {
		// TODO Auto-generated method stub
	
		EncryptionServices encryptService = new EncryptionServices();
		
		WSPExResponse wsResponse = new WSPExResponse();
		ObAccountBean collectionAccBean = new ObAccountBean();
		try
		{
			WSPExRequest pexRequest = (WSPExRequest) wsRequest;
			PExServices pexServices = new PExServices(dao);
			BankRoute bankRouteData = pexServices.getPExBeneficiaryBankCode(pexRequest.getBeneficiaryDetails().getBankCode());
			EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
			if(null==bankRouteData)
			{
				
				wsResponse.setObHeader(new WSHeaderResponse());
				wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_BENEFICIARY_INPUT);
				
				return wsResponse;
			}
			
			
			wsResponse = (WSPExResponse) validatePexCollection.processService(wsRequest);
			//wsResponse = (WSPExResponse) retrieveBeneficiary.processService(wsRequest);
			log.info("RESULT VALIDATE : "+wsResponse.getObHeader().getStatusCode());
			
			if(wsResponse.getObHeader().getStatusCode().equalsIgnoreCase(PExConstant.PEX_ERR_COMMON_SUCCESS))
			{
				Date collectionDate = new Date();
				

				//SIT TESTING USING FIX DATE  ----START
				
				
				collectionDate = pexServices.checkRBSDate(collectionDate);
				
				//SIT TESTING USING FIX DATE  ----END
				
				
				wsResponse.setObHeader(new WSHeaderResponse());
				char[] hexString = Hex.encodeHex(PerformCreationPex.collectionEncryptionKey.getBytes());
				String encrypted_collection_code = encryptService.encrypt(String.valueOf(hexString).getBytes(), pexRequest.getCollectionCode().getBytes());
				
				
				
				//update pex status
				String updateSQL = "UPDATE HlbPexProcessTranx SET status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"' WHERE collection_no = :collection_no AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"'";
				Hashtable params = new Hashtable();
				params.put("collection_no", encrypted_collection_code);
				int updateResult = dao.updateSQLParam(updateSQL,params);
				log.info("perform pex collection update result : "+updateResult);
				
				
				if(updateResult == 1)
				{
					String pexFinalStatus = "";
					PexProcessTranx collectionTrnx = null;
					try
					{
						
						String getCollectionSQL = "FROM HlbPexProcessTranx WHERE collection_no = :collection_no AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"'";
						Hashtable params01 = new Hashtable();
						params01.put("collection_no", encrypted_collection_code);
						
						List<PexProcessTranx> collectionTrnxs =  dao.selectQueryParam(getCollectionSQL,params01);
						collectionTrnx = collectionTrnxs.get(0);
						HlbCustomerProfile custProfile = (HlbCustomerProfile)dao.findByID(HlbCustomerProfile.class, collectionTrnx.getCif());
						/*//release earmark
						RBS_DSPResponse earMarkResponse = releaseEarmark();
						if(null ==  earMarkResponse)
						{
							//timeout or no response
							log.info("ERROR ["+collectionTrnx.getMobileNo()+"]["+pexRequest.getObHeader().getId()+"] RELEASE EARMARK TIMEOUT FROM HOST");
							updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_ERROR,collectionDate);
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_COMMON_ERROR);
							return wsResponse;
						}
						*/
						
						//RBSServices rbsServices = new RBSServices(dao);
						
						if(null!=bankRouteData && pexRequest.getBeneficiaryDetails().getBankCode().equalsIgnoreCase(PExConstant.FT_BENEFICIARY_BANK_CODE))
						{
							
							//3rd ft
							//ObAccountBean collectionAccBean = new ObAccountBean();
							collectionAccBean.setAccountNumber(pexRequest.getBeneficiaryDetails().getAccountNumber());
							collectionAccBean.setAccountName(pexRequest.getBeneficiaryDetails().getAccountName());
							collectionAccBean.setAccountTypeCode(pexRequest.getBeneficiaryDetails().getAccountType());
							
							collectionAccBean.setAccountDescription("");
							
							if(null==pexRequest.getBeneficiaryDetails().getAccountType() || pexRequest.getBeneficiaryDetails().getAccountType().trim().length()==0 )
							{
								String dailyRunningNumberx = pexServices.updateSequenceNum(collectionDate)+"";
								ObAccountInquiryBean toAccDetails = getEAIAccountType(wsRequest.getObHeader().getId(),collectionAccBean.getAccountNumber(),collectionAccBean.getProductTypeCode(),collectionDate,dailyRunningNumberx,"");
								pexRequest.getBeneficiaryDetails().setAccountType(toAccDetails.getAccType());
								pexRequest.getBeneficiaryDetails().setAccountCurrency(toAccDetails.getCurrencyType());
							}
							
							String toAccType = pexRequest.getBeneficiaryDetails().getAccountType();
							String toAccCurrency = pexRequest.getBeneficiaryDetails().getAccountCurrency();
							
							ObAccountBean fromAccBean = new ObAccountBean();
							fromAccBean.setAccountNumber(collectionTrnx.getAccountNo());
							fromAccBean.setAccountTypeCode(collectionTrnx.getAccountProductType());
							fromAccBean.setAccountDescription(collectionTrnx.getAccountType());
							fromAccBean.setBankCode(collectionTrnx.getEaiCorrelationId());//************Branch Code = EaiCorrelationID *********
							fromAccBean.setCurrencyCode(collectionTrnx.getCurrency());
							String fromAccType = fromAccBean.getAccountTypeCode();
							
							//String resultFT = rbsServices.releaseEarmark(fromAccBean, fromAccType, collectionTrnx.getAmountEarmark(), collectionTrnx.getCurrency(), collectionTrnx.getRbsholdRef(), collectionDate, collectionTrnx.getRbsSeqNo());
							String dailyRunningNumber = pexServices.updateSequenceNum(collectionDate)+"";
							String dailyRunningNumber2 = pexServices.updateSequenceNum(collectionDate)+"";
							
							//String payeeName = custProfile.getFullName();
							
							String payeeName = pexRequest.getBeneficiaryDetails().getAccountName();
							String senderName = custProfile.getFullName();
							String remark = collectionTrnx.getRefNo() + " "+ collectionTrnx.getRemarkMessage();
							
							/*if(payeeName.length() > rbsServices.sender_bene_name_length)
								payeeName = payeeName.substring(0, rbsServices.sender_bene_name_length);
							
							if(payeeName.length() < rbsServices.sender_bene_name_length)
							{
								int bLenght = rbsServices.sender_bene_name_length-payeeName.length();
								for(int i=0; i<bLenght; i++)
									payeeName = payeeName+" ";
							}
							
							if(senderName.length() > rbsServices.sender_bene_name_length)
								senderName = senderName.substring(0, rbsServices.sender_bene_name_length);
							
							if(senderName.length() < rbsServices.sender_bene_name_length)
							{
								int bLenght = rbsServices.sender_bene_name_length-senderName.length();
								for(int i=0; i<bLenght; i++)
									senderName = senderName+" ";
							}
							
							if(remark.length() > rbsServices.remark_length)
								remark = remark.substring(0, rbsServices.remark_length);
							
							if(remark.length() < rbsServices.remark_length)
							{
								int bLenght = rbsServices.remark_length-remark.length();
								for(int i=0; i<bLenght; i++)
									remark = remark+" ";
							}*/
							
							//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI SERVICE
							//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
							
							ObEAIHeader resultFT = service.performFundTransfer(wsRequest.getObHeader().getId(),fromAccBean.getAccountNumber(), senderName,collectionTrnx.getAmountPex(), 
									collectionAccBean.getAccountNumber(), payeeName, collectionDate, 
									dailyRunningNumber, fromAccType, toAccType, 
									fromAccBean.getCurrencyCode(), toAccCurrency , collectionTrnx.getRbsSeqNo());
							
							//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI SERVICE
							
							//String resultFT = rbsServices.thirdPartyTransferKH(false,fromAccBean,collectionAccBean, fromAccType, toAccType, collectionTrnx.getAmountPex(),collectionTrnx.getAmountEarmark(),collectionTrnx.getAmountCharges(), collectionTrnx.getCurrency(), collectionTrnx.getRbsholdRef(), collectionDate, dailyRunningNumber,dailyRunningNumber2,collectionTrnx.getRefNo(), payeeName, (remark+senderName));
							
							String responseLogMsg = ("PEX thirdPartyTransfer : ["+fromAccBean.getAccountNumber()+"] - ["+collectionAccBean.getAccountNumber()+"] - ["+collectionTrnx.getAmountPex()+"] : RBS RESULT - ["+resultFT+"]");
							if(resultFT.getErrorResponseCode()==null)
							{
								//time out
								
								pexFinalStatus = updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED,collectionDate,collectionTrnx.getRefNo(),MiBConstant.MIB_EAI_ERROR_PREFIX+resultFT.getReasonCode(),pexServices.loadCCMsg(MiBConstant.MIB_EAI_ERROR_PREFIX+resultFT.getReasonCode(),null),collectionAccBean,bankRouteData.getRouteAbbr(),"",null);
								wsResponse.setTransactionStatus(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED);
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
							}
							else if(resultFT.getErrorResponseCode().trim().length()!=0)
							{
								//error - but can retry again
								DataBeanUtil dataBeanUtil = new DataBeanUtil();
							/*	RBS_DSPERRResponse earMarkERRResponse = new RBS_DSPERRResponse();
								
								earMarkERRResponse = (RBS_DSPERRResponse) dataBeanUtil.setFieldNamesAndByte(earMarkERRResponse, rbsServices.getFullResponseByte(), "CP037");
								Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
								
								
								responseLogMsg = responseLogMsg + "@["+earMarkERRResponse.getResponse42().errorCode_2_p_0+"]";*/
								//System.out.println(new String(rbsServices.getFullResponseByte()));
								
								
								String rbsHoldSeq = null;
								
								String dailyRunningNumber3 = pexServices.updateSequenceNum(collectionDate)+"";
									
									
									//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
									//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
									ObEarmarkBean rsEarmark = service.performEarmarkAccount(wsRequest.getObHeader().getId(), fromAccBean, collectionTrnx.getAmountPex(), fromAccType, collectionDate, dailyRunningNumber3,fromAccBean.getCurrencyCode());
									//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
									
									
									//String rsEarmarkReverse = rbsServices.earMarkPexAccountKH(false, fromAccBean, fromAccType, collectionTrnx.getAmountPex(),collectionTrnx.getAmountEarmark(),collectionTrnx.getAmountCharges(), collectionTrnx.getCurrency(), "", collectionDate, collectionDate, dailyRunningNumber3);
									rbsHoldSeq = rsEarmark.getSequenceNo();
									if(rsEarmark.getErrorResponseCode()==null){
										pexFinalStatus = updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),"",rbsHoldSeq);
									}
									else if(rsEarmark.getErrorResponseCode().trim().length()!=0){
										pexFinalStatus = updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_FAILED,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),"",rbsHoldSeq);
									}
									else{
										pexFinalStatus = updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_ACTIVE,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),"",rbsHoldSeq);
									}
									
									
									
								
								
								
								wsResponse.setReasonFailure(pexServices.loadCCMsg(MiBConstant.MIB_EAI_ERROR_PREFIX+resultFT.getReasonCode(),null));
								//wsResponse.setReasonFailureCode(MiBConstant.MIB_RBS_ERROR_PREFIX+earMarkERRResponse.getResponse42().getErrorCode_2_p_0());
								wsResponse.setReasonFailureCode(PExConstant.FZ_EAI_ERROR_FORMAT+resultFT.getReasonCode());
								
								pexFinalStatus =updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_FAILED,collectionDate,collectionTrnx.getRefNo(),wsResponse.getReasonFailureCode(),wsResponse.getReasonFailure(),collectionAccBean,bankRouteData.getRouteAbbr(),"",rbsHoldSeq);
								
								
								wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
							}
							else
							{
								//successful
								
								//String ref_no = pexServices.genPExRefNO(collectionDate);
								
								pexFinalStatus =updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_PAID,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),"",null);
								wsResponse.setPexReferenceNo(collectionTrnx.getRefNo());
								
						
								wsResponse.setTransactionDate(getTransactionDateTime(collectionDate));
								wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS);
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
							
							}
							
							log.info(responseLogMsg);
						
						}
						else if(null!=bankRouteData)
						{
							
							
							
							//interbank
							//pay+
							
							collectionAccBean.setAccountNumber(collectionTrnx.getAccountNo());
							collectionAccBean.setAccountTypeCode("CASA");
							collectionAccBean.setAccountDescription("");
							collectionAccBean.setAccountName("");
							
							ObAccountBean fromAccBean = new ObAccountBean();
							fromAccBean.setAccountNumber(collectionTrnx.getAccountNo());
							fromAccBean.setAccountTypeCode(collectionTrnx.getAccountProductType());
							fromAccBean.setAccountDescription(collectionTrnx.getAccountType());
							String tranxAccType = fromAccBean.getAccountTypeCode();
							
							String dailyRunningNumber = pexServices.updateSequenceNum(collectionDate)+"";
							
							
							//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
							//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
							ObEAIHeader resultRelease = service.performReleaseEarmarkAcc(wsRequest.getObHeader().getId(), fromAccBean, collectionTrnx.getAmountEarmark(), collectionTrnx.getCurrency(), tranxAccType, collectionDate, collectionTrnx.getRbsSeqNo(), dailyRunningNumber);
							//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
							
							
							//String resultRelease = rbsServices.releaseEarmarkKH(false,fromAccBean,tranxAccType, collectionTrnx.getAmountEarmark(), collectionTrnx.getCurrency(), collectionTrnx.getRbsholdRef(), collectionDate, dailyRunningNumber,10000);
							//String resultRelease = "F1";
							wsResponse.setTransactionDate(getTransactionDateTime(collectionDate));
							wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
							
							if(resultRelease.getErrorResponseCode()==null)
							{
								//time out
								
								pexFinalStatus = updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_FAILED,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),"",null);
								wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
				
							}
							else if(resultRelease.getErrorResponseCode().trim().length()!=0)
							{
								//error
								log.info("RELEASE EARMARK FAILED -- ");
								DataBeanUtil dataBeanUtil = new DataBeanUtil();
								//RBS_DSPERRResponse earMarkERRResponse = new RBS_DSPERRResponse();
								
								//earMarkERRResponse = (RBS_DSPERRResponse) dataBeanUtil.setFieldNamesAndByte(earMarkERRResponse, rbsServices.getFullResponseByte(), "CP037");
								
								pexFinalStatus = updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_FAILED,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),"",null);
								
								wsResponse.setReasonFailure(pexServices.loadCCMsg(MiBConstant.MIB_EAI_ERROR_PREFIX+resultRelease.getReasonCode(),null));
								//wsResponse.setReasonFailureCode(MiBConstant.MIB_RBS_ERROR_PREFIX+earMarkERRResponse.getResponse42().getErrorCode_2_p_0());
								wsResponse.setReasonFailureCode(PExConstant.FZ_EAI_ERROR_FORMAT+resultRelease.getReasonCode());
								wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
								wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
							
							}
							else
							{
								//successful
								//call OCM
								log.info("RELEASE EARMARK SUCCESSFUL -- ");
								
								
							
								
								//String reuqestQueueName = property.getProperty("request.mq").toString();
								//String responseQueueName = property.getProperty("response.mq").toString();
								
								
							//	String text = new Scanner( getClass().getClassLoader().getResourceAsStream("eai_message/IBG_FT.xml") ).useDelimiter("\\A").next();
								
								String fromAccType = collectionTrnx.getAccountProductType();
								
								
								Date OCMTRanDate = collectionDate;
								SimpleDateFormat refDateFormat = new SimpleDateFormat("ddMMyy");
								SimpleDateFormat refTimeFormat = new SimpleDateFormat("HHMMss");
								
							
								/*if(fromAccType.equalsIgnoreCase("CA"))
								{
									text = text.replaceAll("%APPTRANSID%", "9785");
								}
								else
								{
									text = text.replaceAll("%APPTRANSID%", "9786");
								}
								
				
								text = text.replaceAll("%TRANSDATE%", refDateFormat.format(OCMTRanDate ));
								text = text.replaceAll("%TRANSTIME%", refTimeFormat.format(OCMTRanDate));
								text = text.replaceAll("%APPUSERID%", "");
								text = text.replaceAll("%TELLERID%", "MOBI001");
								text = text.replaceAll("%BRANCHCODE%", "11");
								text = text.replaceAll("%CTRLUNIT%", "1");
								text = text.replaceAll("%OCMUSER%", "");
								
								
								String cardNo = pexRequest.getCardNoIBFT() ;
								if(null==pexRequest.getCardNoIBFT() || pexRequest.getCardNoIBFT().length()==0)
								{
									cardNo = collectionTrnx.getCardNo();
								}
								*/
								
								String pexdailyRunningNumber = pexServices.updateSequenceNum(collectionDate)+"";
								pexdailyRunningNumber = pexServices.formatOCMSeqNo(pexdailyRunningNumber);

								/*text = text.replaceAll("%JOURNALSEQUENCE_6%", pexdailyRunningNumber);
								text = text.replaceAll("%CCNO_25%", cardNo);
								text = text.replaceAll("%FROMACCTYPE_2%", fromAccType);
								text = text.replaceAll("%DEBACCNO_25%", collectionTrnx.getAccountNo());
								text = text.replaceAll("%TRANNAME_26%", collectionTrnx.getSenderName());
								text = text.replaceAll("%TRANAMT_13_2%", collectionTrnx.getAmountPex().toString());
								text = text.replaceAll("%FEEAMT_5_2%", collectionTrnx.getAmountCharges().toString());
								text = text.replaceAll("%TREFNO_40%", collectionTrnx.getRefNo());
								text = text.replaceAll("%BENEBKROUTE_10%", bankRouteData.getRouteNo());
								text = text.replaceAll("%ABVBENEBK_10%", bankRouteData.getRouteAbbr());
								text = text.replaceAll("%BENEACCNO_25%", pexRequest.getBeneficiaryDetails().getAccountNumber());
								text = text.replaceAll("%TOTAMT_13_2%", collectionTrnx.getAmountEarmark().toString());
								text = text.replaceAll("%PREFNO_40%", "");*/
								
								
								//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI SERVICE
								//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
								
								
								//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI SERVICE
								
						
								/*log.info("EAI REQUEST \n : "+text);
								RequestSendMessageCreator messageCreator = new RequestSendMessageCreator();
								messageCreator.setMessage(text);
								requestMessageQueue.send(reuqestQueueName, messageCreator);
								
								
								
								String selector = "JMSCorrelationID='"+messageCreator.getMsgID()+"'";

								Message msgRs = responseMessageQueue.receiveSelected(responseQueueName, selector);*/
							//	String msgRs=new String("");
								
								
							//	if(msgRs==null)
							//	{
									//timeOut accepted = in progress
									
									//pexFinalStatus =updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),messageCreator.getMsgID(),null);
							//		wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_IN_PROGRESS);
									
							//	}
							//	else
							//	{
									//log.info("EAI RESPONSE CORRELATION ID "+msgRs.getJMSCorrelationID());
									//log.info("EAI RESPONSE MESSAGE ID "+msgRs.getJMSMessageID());
								
									//JMSBytesMessage ibmMsg = (JMSBytesMessage) msgRs;
									
									//byte[] msgByte = new byte[(int)ibmMsg.getBodyLength()];
									//ibmMsg.readBytes(msgByte);
									//String msg = new String(msgByte);
									/*String msg = new String("");
									log.info("EAI RESPONSE \n : "+msg);
									Document doc =  pexServices.stringToDom(msg);
									NodeList rsList = doc.getElementsByTagName("Header");
									Element el_header = (Element) rsList.item(0);
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
									
									
									log.info("OCM Error Response Code : ["+errorResponseCode+"]");
									if(errorResponseCode.trim().length()==0)
									{
										//success
										
										//pexFinalStatus =updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_PAID,collectionDate,collectionTrnx.getRefNo(),null,null,collectionAccBean,bankRouteData.getRouteAbbr(),messageCreator.getMsgID(),null);
										wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS);
										
									}
									else
									{
										//failed
										
										//NodeList errorCode = el_header.getElementsByTagName("ErrorCode");
										//Element el_rerrorCode =  (Element) errorCode.item(0);
										HlbPexProcessTranx pexDataUpdate = reversePEx(rbsServices,pexServices,collectionTrnx,"","",collectionDate);
										
										//updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_FAILED,collectionDate,collectionTrnx.getRefNo(),errorResponseCode,errorMsg+"["+reasonCode+"]",collectionAccBean,bankRouteData.getRouteAbbr(),messageCreator.getMsgID());
										
										//pexFinalStatus = updatePExTransactionStatus(dao,encrypted_collection_code,pexDataUpdate.getStatus(),collectionDate,collectionTrnx.getRefNo(),pexDataUpdate.getErrorCode(),pexDataUpdate.getErrorMessage(),collectionAccBean,bankRouteData.getRouteAbbr(),messageCreator.getMsgID(),null);
										
										wsResponse.setReasonFailureCode(MiBConstant.MIB_OCM_PREFIX+errorResponseCode);
										wsResponse.setReasonFailure(pexServices.loadCCMsg(MiBConstant.MIB_OCM_PREFIX+errorResponseCode,null));
										wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
										
									}*/
									
									ObEAIHeader resultFT = service.performFundTransfer(wsRequest.getObHeader().getId(), collectionTrnx.getAccountNo(), collectionTrnx.getSenderName(), collectionTrnx.getAmountEarmark(), 
											collectionTrnx.getCollectionAccNo(), collectionTrnx.getPayeeName(), collectionDate, 
											pexdailyRunningNumber, collectionTrnx.getAccountType(), collectionTrnx.getCollectionAccType(), 
											collectionTrnx.getCurrency(), pexRequest.getBeneficiaryDetails().getAccountCurrency(), collectionTrnx.getRbsSeqNo());
									if (resultFT.getErrorResponseCode() == null){
										
										//wsResponse.setReasonFailureCode(MiBConstant.MIB_OCM_PREFIX+errorResponseCode);
										//wsResponse.setReasonFailure(pexServices.loadCCMsg(MiBConstant.MIB_OCM_PREFIX+errorResponseCode,null));
										wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_IN_PROGRESS);
									}
									else if(resultFT.getErrorResponseCode().trim().length()!=0)
									{
										PexProcessTranx pexDataUpdate = reversePEx(wsRequest.getObHeader().getId(),pexServices,collectionTrnx,"","",collectionDate);
										wsResponse.setReasonFailureCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultFT.getReasonCode());
										wsResponse.setReasonFailure(pexServices.loadCCMsg(MiBConstant.MIB_EAI_ERROR_PREFIX+resultFT.getReasonCode(),null));
										wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
									}
									else{
										wsResponse.setTransactionStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS);
									}
										
									
									
									
							//	}
								
								
								
							
								
								
							}
						
							//updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_ACTIVE,collectionDate,collectionTrnx.getRefNo(),null);
						
						}
						
						
						
		
						
						
					}catch (Exception tEx)
					{
						log.info("ERROR ["+collectionTrnx.getRefNo()+"] - ["+pexRequest.getObHeader().getId()+"] : ",tEx);
						String errorMsg = tEx.getMessage().length()>200?tEx.getMessage().substring(0, 200): tEx.getMessage();
						errorMsg = errorMsg.replaceAll("\\'", "");
						
						pexFinalStatus =updatePExTransactionStatus(dao,encrypted_collection_code,PExConstant.PEX_TRANSACTION_STATUS_FAILED,collectionDate,null,PExConstant.PEX_ERR_COMMON_ERROR,errorMsg,collectionAccBean,bankRouteData.getRouteAbbr(),"",null);
						wsResponse = new WSPExResponse();
						wsResponse.setObHeader(new WSHeaderResponse());
						wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
					}
					
					
					
					
					try
					{
						String ibRefID = pexServices.getSystemSequenceNum(new Date());
						TransactionServices tranService = new TransactionServices(null);
						PexUpdateRequest PexRequest = new PexUpdateRequest();
						PexRequest.setServiceChargeAmount(collectionTrnx.getAmountCharges());
						PexRequest.setTransactionStatusCode(pexFinalStatus);
						PexRequest.setTransactionId(collectionTrnx.getUpdateIBId());
						/*PexRequest.setDescription(collectionTrnx.getRemarkMessage());
						PexRequest.setFromAccountNumber(collectionTrnx.getAccountNo());
						PexRequest.setFromProductTypeCode(collectionTrnx.getAccountProductType());
						PexRequest.setMobileNumber(collectionTrnx.getMobileNo());
						PexRequest.setReferenceNumber(collectionTrnx.getRefNo());
						PexRequest.setSenderName(collectionTrnx.getSenderName());
						PexRequest.setServiceChargeAmount(collectionTrnx.getAmountCharges());
						PexRequest.setTransactionAmount(collectionTrnx.getAmountPex());
						
						GregorianCalendar gc = new GregorianCalendar();
			            gc.setTimeInMillis(collectionTrnx.getCreationDate().getTime());
			            XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
			                    .newXMLGregorianCalendar(
			                        gc);
			         
						PexRequest.setTransactionDate(calendar);
						PexRequest.setTransactionStatusCode(collectionTrnx.getStatus());*/
						
						
						ObUserContext userContext = new ObUserContext();
						userContext.setLoginId(((HlbCustomerProfile)dao.findByID(HlbCustomerProfile.class, collectionTrnx.getCif())).getLoginId());
						userContext.setCountryCode(property.getProperty("pexCollectCountry"));
						userContext.setLocaleCode(property.getProperty("pexCollectLocale"));
						TransactionResponse updatePexIB = tranService.updateIBPExTransaction(userContext, PexRequest, ibRefID);
			
						if(updatePexIB.getResponse().getStatusCode()==1)
						{
							updateIBModifyFlag(true,collectionTrnx.getRefNo(),collectionTrnx.getUpdateIBId());
						}
						else
						{
							updateIBModifyFlag(false,collectionTrnx.getRefNo(),collectionTrnx.getUpdateIBId());
						}
						dao.insertEntity(tranService.getIbwsLog());
					}catch(Exception ex)
					{
						ex.printStackTrace();
						updateIBModifyFlag(false,collectionTrnx.getRefNo(),collectionTrnx.getUpdateIBId());
					}
				
				
				}
				else
				{
					wsResponse.setObHeader(new WSHeaderResponse());
					wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTIONNO);
				}

				
			}
			
			
			
		}catch (Exception e)
		{
			log.info("PEx server Error - PEX Collection  ",e);
			wsResponse = new WSPExResponse();
			wsResponse.setObHeader(new WSHeaderResponse());
			wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
		}
		
		

		return wsResponse;
	}
	
	/*private class RequestSendMessageCreator implements MessageCreator{

		private String message;
		private TextMessage texMessage;
		
		@Override
		public Message createMessage(Session arg0) throws JMSException {
			setTexMessage(arg0.createTextMessage(message));
			return getTexMessage();
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public TextMessage getTexMessage() {
			return texMessage;
		}

		public void setTexMessage(TextMessage texMessage) {
			this.texMessage = texMessage;
		}
		
		public String getMsgID() throws JMSException
		{
			return texMessage.getJMSMessageID();
		}
		
		
	}*/
	
	
	
	public void updateIBModifyFlag(boolean success, String refID, String tranxId)
	{
		
		String updateIBTransSQL = "UPDATE HlbPexProcessTranx SET update_ib_flag = '<ibFlagStatus>' , update_ib_id = '"+tranxId+"' WHERE ref_no='"+refID+"' "; 
		
		if(success)
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<ibFlagStatus>", PExConstant.PEX_IB_FLAG_UPDATED);
		}
		else
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<ibFlagStatus>", PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
		}
		
		
		log.info("UPDATE PEX TRANX : "+updateIBTransSQL);
		
		dao.updateSQL(updateIBTransSQL);
		
	}

	
	public String updatePExTransactionStatus(MLEBPExDAO dao, String encryptedCollectionCode, String status, Date paidDate, String refNo,String errorCode, String errorMessage, ObAccountBean accountBean, String bankRouteAbbr,String eaiCorrelationID, String rbsHoldref)
	{
		String updateSQL = ""; 
		SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		/*if(null!=paidDate)
		{
			updateSQL = "UPDATE HlbPexProcessTranx SET " +
					"collection_acc_no='"+accountBean.getAccountNumber()+"' , " +
					"collection_acc_product_type='"+accountBean.getAccountTypeCode()+"' , " +
					"collection_acc_type='"+accountBean.getAccountDescription()+"' , " +
					"collection_acc_name='"+accountBean.getAccountName()+"' , " +
					"collection_bank_route_abbr='"+bankRouteAbbr+"' , " +
					"eai_correlation_id = '"+eaiCorrelationID+"' , "+
					"status = '"+status+"' , " +
					"collection_date='"+refFormat.format(paidDate)+"', " +
					"update_ib_flag='"+PExConstant.PEX_IB_FLAG_PENDING_UPDATED+"' " +
					"WHERE collection_no = '"+encryptedCollectionCode+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"'";
		}
		else if(null!=errorCode)
		{
			updateSQL = "UPDATE HlbPexProcessTranx SET " +
					"collection_acc_no='"+accountBean.getAccountNumber()+"' , " +
					"collection_acc_product_type='"+accountBean.getAccountTypeCode()+"' , " +
					"collection_acc_type='"+accountBean.getAccountDescription()+"' , " +
					"collection_acc_name='"+accountBean.getAccountName()+"' , " +
					"collection_bank_route_abbr='"+bankRouteAbbr+"' , " +
					"eai_correlation_id = '"+eaiCorrelationID+"' , "+
					"status = '"+status+"' , error_code='"+errorCode+"' , error_message='"+errorMessage+"' , collection_date='"+refFormat.format(paidDate)+"' " +
					"WHERE collection_no = '"+encryptedCollectionCode+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"'";
		}
		else
		{
			updateSQL = "UPDATE HlbPexProcessTranx SET status = '"+status+"' WHERE collection_no = '"+encryptedCollectionCode+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"'";	
		}*/
		
		updateSQL = "UPDATE HlbPexProcessTranx SET " +
				"collection_acc_no='"+accountBean.getAccountNumber()+"' , " +
				"collection_acc_product_type='"+accountBean.getAccountTypeCode()+"' , " +
				"collection_acc_type='"+accountBean.getAccountDescription()+"' , " +
				"collection_acc_name='"+accountBean.getAccountName()+"' , " +
				"collection_bank_route_abbr='"+bankRouteAbbr+"' , " +
				"eai_correlation_id = '"+eaiCorrelationID+"' , "+
				"<reverseHoldSeq>"+
				"status = '"+status+"' , error_code='"+errorCode+"' , error_message='"+errorMessage+"' , collection_date='"+refFormat.format(paidDate)+"' " +
				"WHERE collection_no = '"+encryptedCollectionCode+"' AND status = '"+PExConstant.PEX_TRANSACTION_STATUS_PROCESSING+"'";
		
		
		
		
		if(null!=rbsHoldref)
		{
			updateSQL = updateSQL.replaceAll("<reverseHoldSeq>", "rbs_hold_ref = '"+rbsHoldref+"' , ");
		}
		else
		{
			updateSQL = updateSQL.replaceAll("<reverseHoldSeq>", "");
		}
		
		
	
		dao.updateSQL(updateSQL);
		return status;
	}
	
	
	public String getTransactionDateTime(Date tdate)
	{
		String fmat = PExConstant.PEX_TRANX_DATE;
		Locale malaysia = new Locale("en","MY","MY");
		
		SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(fmat,malaysia);


		String displayDate = refDateTImeFormat.format(tdate);
		displayDate =  displayDate.substring(0,displayDate.length()-2)+":00";
		return displayDate;
	}

	
	
	

	public String toHexString(byte[] bytes) {
	    char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j*2] = hexArray[v/16];
	        hexChars[j*2 + 1] = hexArray[v%16];
	    }
	    return new String(hexChars);
	}
  
	
	
	
	public ObAccountInquiryBean getEAIAccountType(String tranxID, String accountNumber,String accountProductType,Date transDate, String runningNumber, String currencyCode) throws Exception
	{
		
		
		//<<<<<<<<<<<<<<<<<<<<< EAI SERVICE
		
		
		 EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
		ObAccountInquiryBean accDetails = service.performAccountInquiry(tranxID, runningNumber, accountNumber , transDate, accountProductType, currencyCode);
		
		return accDetails;
		
		
		//<<<<<<<<<<<<<<<<<<<<< EAI SERVICE
	
		
	}
	
	
	
	
	
	public PexProcessTranx reversePEx(String tranxID, PExServices pexServices , PexProcessTranx pexData, String errorCode, String errorMsg, Date currentDate ) throws Exception
	{
	
		
		
		ObAccountBean fromAccBean = new ObAccountBean();
		fromAccBean.setAccountNumber(pexData.getAccountNo());
		String earMakType = pexData.getAccountProductType();
		String dailyRunningNumber = pexServices.updateSequenceNum(currentDate)+"";
		EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
		ObEarmarkBean resultEarMark = service.performEarmarkAccount(tranxID, fromAccBean, pexData.getAmountPex(), earMakType, currentDate, dailyRunningNumber,pexData.getCurrency());
		//String resultEarMark = rbsServices.earMarkReverse(false,fromAccBean,earMakType,pexData.getAmountPex(),pexData.getAmountEarmark(),pexData.getAmountCharges(),pexData.getCurrency(), pexData.getRbsholdRef(), currentDate, pexData.getExpiredDate() ,dailyRunningNumber);
		
		if(null == resultEarMark.getErrorResponseCode())
		{
			
			//TIMEOUT ///////////////////////////RELEASE EAR ONCE ONLY
			//dailyRunningNumber = pexServices.updateSequenceNum(currentDate)+"";
			//String resultRelease = rbsServices.releaseEarmark(false, fromAccBean, earMakType,pexData.getAmountPex(), pexData.getCurrency(), pexData.getRbsholdRef(), currentDate, dailyRunningNumber,10000);
			//System.out.println("EARMARK FAILED : RELEASE ONCE : "+resultRelease);
			////////////////////////////////////////////////////////////////
			pexData.setStatus(PExConstant.PEX_STATUS_TRANSACTION_IN_PROGRESS);
			pexData.setErrorCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultEarMark.getReasonCode());
			pexData.setErrorMessage(pexServices.loadCCMsg(pexData.getErrorCode(),null));
			
		}
		else if(resultEarMark.getErrorResponseCode().trim().length()!=0)
		{
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			//RBS_DSPERRResponse earMarkERRResponse = new RBS_DSPERRResponse();
			//earMarkERRResponse = (RBS_DSPERRResponse) dataBeanUtil.setFieldNamesAndByte(earMarkERRResponse, rbsServices.getFullResponseByte(), "CP037");
			pexData.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
			

			
				pexData.setErrorCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultEarMark.getReasonCode());
				pexData.setErrorMessage(pexServices.loadCCMsg(pexData.getErrorCode(),null));

		}
		else
		{
			pexData.setStatus(PExConstant.PEX_TRANSACTION_STATUS_ACTIVE);
		}
	
		return pexData;
	
	}
	
	
	
	
	
	public static void main (String args[]) throws Exception
	{
		SimpleDateFormat refTimeFormat = new SimpleDateFormat("HHmmss");
		
		Date xa = new Date();
		
		System.out.println(refTimeFormat.format(xa));
	}
	
}

