package com.silverlake.mleb.pex.module.func;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.PersistenceException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fuzion.ws.common.endpoint.CutOffTimeResponse;
import com.fuzion.ws.security.endpoint.EndpointResponse;
import com.fuzion.ws.security.endpoint.SendNotificationForPreLoginRequest;
import com.fuzion.ws.security.endpoint.ValidateAuthorizationCodeRequest;
import com.fuzion.ws.security.endpoint.ValidateAuthorizationCodeResponse;
import com.fuzion.ws.transaction.endpoint.PexRequest;
import com.fuzion.ws.transaction.endpoint.TransactionResponse;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.hlb.mib.entity.HlbCustomerProfile;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObEAIHeader;
import com.silverlake.mleb.pex.bean.ObEarmarkBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.eai.EAIService;
import com.silverlake.mleb.pex.entity.BankRoute;
import com.silverlake.mleb.pex.entity.PexCollectionCode;
import com.silverlake.mleb.pex.entity.PexConf;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.TransactionServices.TransactionServices;
import com.silverlake.mleb.pex.module.ib.commonServices.CommoService;
import com.silverlake.mleb.pex.module.ib.securityServices.SecuirtyService;
import com.silverlake.mleb.pex.module.rbs.services.EarmarkServices;
import com.silverlake.mleb.pex.module.rbs.services.ReleaseEarmarkServices;
import com.silverlake.mleb.pex.module.rbs.services.TransferServices;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.module.services.RBSServices;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPERRResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPFBResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.HLBDateUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.StringDataUtil;






@Service
public class PerformCreationPex extends FuncServices  
{

	private static Logger log = LogManager.getLogger(PerformCreationPex.class);
	private PropertiesManager property = new PropertiesManager();
	
	
	@Autowired
	MLEBPExDAO dao;
	
	public static final String ref_date_format = "yyMMdd";
	public static final String ref_dateTime_format = "yyMMdd HH:mm";

	
	public static final String collectionEncryptionKey = "HLB@PEx@";
	
	 //@Autowired @Qualifier("requestMessageQueue")
	 private JmsTemplate requestMessageQueue;
		 
	//@Autowired @Qualifier("responseMessageQueue")
	private JmsTemplate responseMessageQueue;
	
	
	
	 
	@Autowired ApplicationContext appContext;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		PropertiesManager pmgr = new PropertiesManager();
		StringDataUtil stringUtil = new StringDataUtil();
		EncryptionServices encryptService = new EncryptionServices();
		//SimpleDateFormat tranxDTFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST);
		SimpleDateFormat refFormat = new SimpleDateFormat(ref_date_format);
		SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(ref_dateTime_format);
		MICBResponseBean response = new MICBResponseBean();
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		
		String atmPin = null;
		
		try {
			
			PExServices pexServices = new PExServices(dao);
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			
			pexResponse.setPexTransactionDetails(pexRequest.getPexTransactionDetails());
			PexConf pexConf = pexServices.getPExConf();
			
			String cif = pexRequest.getObUser().getCifNumber();
			
			EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
			Date transactionDate = new Date();
			
			//SIT TESTING USING FIX DATE  ----START
			
			
			
			transactionDate = pexServices.checkRBSDate(transactionDate);
			
			//SIT TESTING USING FIX DATE  ----END
			
			
			String pexDBStatus = "";
			
			
			//validatePex - Transaction Limit - confirmCreationPex already valid
		
			CommoService commonService = new CommoService(appContext);
			CutOffTimeResponse cutResp = commonService.getCutOffTime(pexRequest.getUserContext(),  arg0.getTranxID());
			if(cutResp.getResponse().getStatusCode() == 0)
			{
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+cutResp.getResponse().getErrorCode());
			}
			else
			{
				/*SecuirtyService securityService = new SecuirtyService(appContext);
				ValidateAuthorizationCodeRequest authorizationCode = new ValidateAuthorizationCodeRequest();
				authorizationCode.setAuthorizationCode(pexRequest.getAuthorizationCode());
				authorizationCode.setAuthorizationUUID(pexRequest.getObUser().getAccessId());
				ValidateAuthorizationCodeResponse tacRsp = securityService.validateAuthorizationCode(pexRequest.getUserContext(), authorizationCode, arg0.getTranxID());
				
				if(tacRsp.getResponse().getStatusCode()!=1)
				{
					pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+tacRsp.getResponse().getErrorCode());
				}
				else
				{*/
					//validate daily Limit
					
					//BigDecimal globalDailyLimit = pexConf.getGlobalDailyLimit();
					//confirmCreationPex already valid
					boolean validateDailyLimit = true;
					
					
					if(!validateDailyLimit)
					{
						
						pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_DAILY_LIMIT_EXISTED);
						
					}
					else
					{
						
						//earmark amount and charges				
						String dailyRunningNumber = pexServices.updateSequenceNum(transactionDate)+"";
						String rbsHoldRef = arg0.getTranxID();
						//RBSServices rbsServices = new RBSServices(dao);
						//Object objExpPeriods = pmgr.getProperty("PEx.expired.type");
						//String expirationType = String.valueOf(objExpPeriods);
						Date expirationDate = pexServices.getExpiryDate(Integer.parseInt(pexConf.getExpiry()), pexConf.getExpiryTime(), transactionDate);
						//log.info("[[[[[[[[ EXPIRATION RBS ]]]]]]]]]] :::::::::::::::::::::::::::::: "+expirationDate);
						String earMakType = pexResponse.getPexTransactionDetails().getFromAccount().getProductTypeCode();
						BigDecimal totalAmt = new BigDecimal(pexResponse.getPexTransactionDetails().getAmount().replaceAll(",", "")).add(new BigDecimal(pexResponse.getPexTransactionDetails().getServiceCharge().replaceAll(",", "")));
						
						
						
						//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
						
						ObEarmarkBean resultEarMark = service.performEarmarkAccount(arg0.getTranxID(), pexResponse.getPexTransactionDetails().getFromAccount(), totalAmt, earMakType, transactionDate, dailyRunningNumber,pexResponse.getPexTransactionDetails().getCurrency());
						//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
				
						//String resultEarMark = rbsServices.earMarkPexAccountKH(pexResponse.getPexTransactionDetails().isPExDirect(),pexResponse.getPexTransactionDetails().getFromAccount(),earMakType, totalAmt,totalAmt,new BigDecimal(pexResponse.getPexTransactionDetails().getServiceCharge().replaceAll(",", "")), pexResponse.getPexTransactionDetails().getCurrency(), rbsHoldRef, transactionDate, expirationDate ,dailyRunningNumber);
						char[] hexString = Hex.encodeHex(collectionEncryptionKey.getBytes());
						String collectionCode = pexServices.genCollectionCode(pexConf.getCollectionCodeLength());
						
						String collectionEncryted = encryptService.encrypt(String.valueOf(hexString).getBytes(), collectionCode.getBytes());
						String ref_no = pexServices.genPExRefNO(transactionDate,dailyRunningNumber);
						
						
						
						if(null == resultEarMark.getErrorResponseCode())
						{
							//TIMEOUT ///////////////////////////RELEASE EAR ONCE ONLY
							dailyRunningNumber = pexServices.updateSequenceNum(transactionDate)+"";
							
							//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
							//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
							ObEAIHeader resultRelease = service.performReleaseEarmarkAcc(arg0.getTranxID(), pexResponse.getPexTransactionDetails().getFromAccount(), new BigDecimal(pexResponse.getPexTransactionDetails().getAmount()), pexResponse.getPexTransactionDetails().getCurrency(), earMakType, transactionDate, resultEarMark.getSequenceNo(), dailyRunningNumber);
							//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
							
							//String resultRelease = rbsServices.releaseEarmarkKH(pexResponse.getPexTransactionDetails().isPExDirect(), pexResponse.getPexTransactionDetails().getFromAccount(), earMakType, new BigDecimal(pexResponse.getPexTransactionDetails().getAmount().replaceAll(",", "")), pexResponse.getPexTransactionDetails().getCurrency(), rbsHoldRef, transactionDate, dailyRunningNumber,10000);
							//log.info("EARMARK FAILED : RELEASE ONCE : "+resultRelease);
							////////////////////////////////////////////////////////////////
							
							
						
							//---------
							PexProcessTranx pexTrax = new PexProcessTranx();
							pexTrax.setAmountCharges(new BigDecimal(pexResponse.getPexTransactionDetails().getServiceCharge().replaceAll(",", "")));
							pexTrax.setAmountPex(new BigDecimal(pexResponse.getPexTransactionDetails().getAmount().replaceAll(",", "")));
							//history.setCollectionNo(collectionEncryted);
							pexTrax.setCreationDate(transactionDate);
							pexTrax.setRefNo(ref_no);
							pexTrax.setCif(cif);
							pexTrax.setCollectionFlag(PExConstant.PEX_COLLECTION_FLAG_OPEN);
							pexTrax.setRbsSeqNo(dailyRunningNumber);
							pexTrax.setSenderName(pexResponse.getPexTransactionDetails().getSenderName());
							pexTrax.setRbsholdRef(rbsHoldRef);
							pexTrax.setPayeeName(pexResponse.getPexTransactionDetails().getPayeeName());
							pexTrax.setRemarkMessage(pexResponse.getPexTransactionDetails().getRemark());
							pexTrax.setAccountNo(pexResponse.getPexTransactionDetails().getFromAccount().getAccountNumber());
							pexTrax.setAccountType(pexResponse.getPexTransactionDetails().getFromAccount().getAccountDescription());
							pexTrax.setAccountProductType(pexResponse.getPexTransactionDetails().getFromAccount().getProductTypeCode());
							pexTrax.setCollectionType(pexResponse.getPexTransactionDetails().getCollectionType());
							//history.setCollectionFlag(PExConstant.PEX_COLLECTION_FLAG_OPEN);
							pexTrax.setMobileNo(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
							pexTrax.setAmountEarmark(totalAmt);	
							pexTrax.setCurrency(pexResponse.getPexTransactionDetails().getCurrency());
							pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
							pexTrax.setErrorCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultEarMark.getReasonCode());
							pexTrax.setErrorMessage(pexServices.loadCCMsg(pexTrax.getErrorCode(),pexRequest.getUserContext().getLocaleCode()));
							pexTrax.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_UPDATING);
							pexTrax.setLocale(pexRequest.getUserContext().getLocaleCode());
							pexDBStatus = pexTrax.getStatus();
							
							//insert login method to determine if biometric login 
							if(pexServices.loginTypeContainBiometric(pexRequest.getObUser().getLoginType(),MiBConstant.QUIKC_LOGIN_BIOMETRIC)){
								pexTrax.setLoginMethod(MiBConstant.QUIKC_LOGIN_BIOMETRIC);
							}
							else {
								pexTrax.setLoginMethod(PExConstant.LOGIN_METHOD_NORMAL);
							}
							pexTrax.setDeviceId(arg0.getDeviceBean().getDeviceId());
							
							dao.insertEntity(pexTrax);
							
							
							if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
								pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
							}else{
								pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
							}
							
							pexResponse.getPexTransactionDetails().setErrorMessage(pexTrax.getErrorMessage());
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);

							pexResponse.getPexTransactionDetails().setReferenceNumber(ref_no);
							pexResponse.getPexTransactionDetails().setDatetime(HLBDateUtil.getTransactionDateByLocale(transactionDate, pexRequest.getUserContext().getLocaleCode()));
							
							
							
						}
						else if(resultEarMark.getErrorResponseCode().trim().length()!=0)
						{
							log.info("EAI ERROR "+ resultEarMark);
							
							
							//log.info("ACCOUNT DETAILS :  "+pexResponse.getPexTransactionDetails().getFromAccount().getProductTypeCode());
							
							//failed earmark to RBS
							if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
								pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
							}else{
								pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
							}
							
	
							PexProcessTranx pexTrax = new PexProcessTranx();
							
								
							pexTrax.setErrorCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultEarMark.getErrorResponseCode());
								
							
							
							
							pexResponse.getPexTransactionDetails().setErrorMessage(pexServices.loadCCMsg(pexTrax.getErrorCode(),pexRequest.getUserContext().getLocaleCode()));
							
							
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
							pexResponse.getPexTransactionDetails().setReferenceNumber(ref_no);
							pexResponse.getPexTransactionDetails().setDatetime(HLBDateUtil.getTransactionDateByLocale(transactionDate, pexRequest.getUserContext().getLocaleCode()));
							//-----------
							
							pexTrax.setAmountCharges(new BigDecimal(pexResponse.getPexTransactionDetails().getServiceCharge().replaceAll(",", "")));
							pexTrax.setAmountPex(new BigDecimal(pexResponse.getPexTransactionDetails().getAmount().replaceAll(",", "")));
							//history.setCollectionNo(collectionEncryted);
							pexTrax.setAmountEarmark(totalAmt);	
							pexTrax.setCurrency(pexResponse.getPexTransactionDetails().getCurrency());
							pexTrax.setCreationDate(transactionDate);
							pexTrax.setRefNo(ref_no);
							pexTrax.setCif(cif);
							pexTrax.setRbsSeqNo(dailyRunningNumber);
							pexTrax.setSenderName(pexResponse.getPexTransactionDetails().getSenderName());
							pexTrax.setRbsholdRef(rbsHoldRef);
							pexTrax.setAccountNo(pexResponse.getPexTransactionDetails().getFromAccount().getAccountNumber());
							pexTrax.setAccountType(pexResponse.getPexTransactionDetails().getFromAccount().getAccountDescription());
							pexTrax.setAccountProductType(pexResponse.getPexTransactionDetails().getFromAccount().getProductTypeCode());
							pexTrax.setCollectionType(pexResponse.getPexTransactionDetails().getCollectionType());
							pexTrax.setMobileNo(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
							pexTrax.setCollectionFlag(PExConstant.PEX_COLLECTION_FLAG_OPEN);
							pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
							pexTrax.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_UPDATING);
							pexTrax.setLocale(pexRequest.getUserContext().getLocaleCode());
							
							//pexTrax.setErrorMessage(resultEarMark.getErrorMsg());
							pexTrax.setErrorMessage(pexServices.loadCCMsg(pexTrax.getErrorCode(),pexRequest.getUserContext().getLocaleCode()));
							pexTrax.setRemarkMessage(pexResponse.getPexTransactionDetails().getRemark());
							pexTrax.setPayeeName(pexResponse.getPexTransactionDetails().getPayeeName());
							//Gson gson = new Gson();
							//log.info(gson.toJson(pexTrax));
							pexDBStatus = pexTrax.getStatus();
							
							//log.info("INSERT PEX TYPE : "+pexTrax.getAccountProductType());
							//insert login method to determine if biometric login 
							if(pexServices.loginTypeContainBiometric(pexRequest.getObUser().getLoginType(),MiBConstant.QUIKC_LOGIN_BIOMETRIC)){
								pexTrax.setLoginMethod(MiBConstant.QUIKC_LOGIN_BIOMETRIC);
							}
							else {
								pexTrax.setLoginMethod(PExConstant.LOGIN_METHOD_NORMAL);
							}
							pexTrax.setDeviceId(arg0.getDeviceBean().getDeviceId());
							dao.insertEntity(pexTrax);
							
						}
						else
						{
							//generate collection number & reference number PExYYMMDDXXX

				
							//atm generate pin
							String encryptedPin = "";
							if(pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_ATM)){
								atmPin = pexServices.genPin(pexConf.getAtmPinLength());
								//log.debug("!!! ATM Pin : "+atmPin);
								encryptedPin  = encryptService.encrypt(String.valueOf(hexString).getBytes(), atmPin.getBytes());
							}
							
							DataBeanUtil dataBeanUtil = new DataBeanUtil();
							//RBS_DSPFBResponse earMarkFBResponse = new RBS_DSPFBResponse();
							//earMarkFBResponse = (RBS_DSPFBResponse) dataBeanUtil.setFieldNamesAndByte(earMarkFBResponse, rbsServices.getFullResponseByte(), "CP037");
							rbsHoldRef = arg0.getTranxID();
				
							
								//insert record to database
							PexProcessTranx pexTrax = new PexProcessTranx();
							pexTrax.setAmountCharges(new BigDecimal(pexResponse.getPexTransactionDetails().getServiceCharge().replaceAll(",", "")));
							pexTrax.setAmountPex(new BigDecimal(pexResponse.getPexTransactionDetails().getAmount().replaceAll(",", "")));
							
							pexTrax.setCreationDate(transactionDate);
							pexTrax.setRefNo(ref_no);
							pexTrax.setCif(cif);
							pexTrax.setRbsSeqNo(dailyRunningNumber);
							pexTrax.setSenderName(pexResponse.getPexTransactionDetails().getSenderName());
							pexTrax.setRbsholdRef(rbsHoldRef);
							pexTrax.setAccountNo(pexResponse.getPexTransactionDetails().getFromAccount().getAccountNumber());
							pexTrax.setAccountType(pexResponse.getPexTransactionDetails().getFromAccount().getAccountDescription());
							pexTrax.setAccountProductType(pexResponse.getPexTransactionDetails().getFromAccount().getProductTypeCode());
							pexTrax.setCardNo(pexResponse.getPexTransactionDetails().getFromAccount().getRefNo());
							//pexTrax.setExpiration_period(getExpirationPeriod(arg0,100));
							
				
							pexTrax.setRemarkMessage(pexResponse.getPexTransactionDetails().getRemark());
							pexTrax.setPayeeName(pexResponse.getPexTransactionDetails().getPayeeName());
							pexTrax.setExpiration_period(Integer.parseInt(pexConf.getExpiry()));
							pexTrax.setExpiredDate(expirationDate);
							pexTrax.setCollectionType(pexResponse.getPexTransactionDetails().getCollectionType());	
							pexTrax.setCollectionFlag(PExConstant.PEX_COLLECTION_FLAG_OPEN);
							pexTrax.setMobileNo(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
							pexTrax.setLocale(pexRequest.getUserContext().getLocaleCode());
							
							if(pexResponse.getPexTransactionDetails().isPExDirect())
							{
								pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING);
							}
							else
							{
								pexTrax.setCollectionNo(collectionEncryted);
								pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_ACTIVE);
								
							}
							
							pexTrax.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_UPDATING);
							pexTrax.setSmsCreationFlag(PExConstant.PEX_SMS_FLAG_PROCCESSING);
							pexTrax.setAmountEarmark(totalAmt);	
							pexTrax.setPin(encryptedPin);
							pexTrax.setCurrency(pexResponse.getPexTransactionDetails().getCurrency());
							
							
							//bank code = branch code
							//branch code temporary set to EAICorrelationId
							pexTrax.setEaiCorrelationId(pexResponse.getPexTransactionDetails().getFromAccount().getBankCode());
							
							
							
							
							PexCollectionCode activeCollectionCode = new PexCollectionCode();
							activeCollectionCode.setCollectionNo(collectionEncryted);
							activeCollectionCode.setRefNo(pexTrax.getRefNo());
							
							int insertResult = 0;
							while( -100 <= insertResult &&  insertResult <= 0 && !pexResponse.getPexTransactionDetails().isPExDirect())
							{
								try
								{
									//insertResult = dao.insertEntity(pexTrax);
									insertResult = dao.insertEntity(activeCollectionCode);
								
								}catch(PersistenceException  ex)
								{
				
									if(insertResult <= -100)
									{
										throw(ex);
									}
									else if(ex.getCause()!= null && ex.getCause().getClass().equals(ConstraintViolationException.class))
									{
										log.info("SQL ERROR : "+ex.getCause().getMessage());
										collectionCode = pexServices.genCollectionCode(pexConf.getCollectionCodeLength());
										
										collectionEncryted = encryptService.encrypt(String.valueOf(hexString).getBytes(),  collectionCode.getBytes());
										pexTrax.setCollectionNo(collectionEncryted);
										activeCollectionCode.setCollectionNo(collectionEncryted);
										insertResult --;
									}
									else
									{
										throw(ex);
									}
									
									
								}
							}
							
							pexDBStatus = pexTrax.getStatus();
							
							//insert login method to determine if biometric login 
							if(pexServices.loginTypeContainBiometric(pexRequest.getObUser().getLoginType(),MiBConstant.QUIKC_LOGIN_BIOMETRIC)){
								pexTrax.setLoginMethod(MiBConstant.QUIKC_LOGIN_BIOMETRIC);
							}
							else {
								pexTrax.setLoginMethod(PExConstant.LOGIN_METHOD_NORMAL);
							}
							pexTrax.setDeviceId(arg0.getDeviceBean().getDeviceId());
							
							dao.insertEntity(pexTrax);
							
							pexResponse.getPexTransactionDetails().setDatetime(HLBDateUtil.getTransactionDateByLocale(transactionDate, pexRequest.getUserContext().getLocaleCode()));
							pexResponse.getPexTransactionDetails().setReferenceNumber(ref_no);
							
							
							if(pexResponse.getPexTransactionDetails().isPExDirect())
							{
								
								
								
								//call RBS ThirdParty Transfer
								String dailyRunningNumber_thirdParty = pexServices.updateSequenceNum(transactionDate)+"";
								String pexDailyRunningNumber = pexServices.formatOCMSeqNo(dailyRunningNumber_thirdParty);
								String dailyRunningNumber_thirdParty2 = pexServices.updateSequenceNum(transactionDate)+"";
								
								ObAccountBean collectionAccBean = new ObAccountBean();
								collectionAccBean.setAccountNumber(pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountNumber());
								collectionAccBean.setAccountName(pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountName());
								collectionAccBean.setAccountTypeCode(pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountTypeCode());
								collectionAccBean.setCurrencyCode(pexRequest.getPexTransactionDetails().getCurrency());
								String toAccType = pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountTypeCode();
								
								pexTrax.setCollectionAccName(pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountName());
								pexTrax.setCollectionAccNo(pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountNumber());
								pexTrax.setCollectionAccProductType(pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountTypeCode());
								pexTrax.setCollectionAccType(pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountDescription());
								
								ObAccountBean fromAccBean = new ObAccountBean();
								fromAccBean.setAccountNumber(pexTrax.getAccountNo());
								fromAccBean.setAccountTypeCode(pexTrax.getAccountProductType());
								fromAccBean.setAccountDescription(pexTrax.getAccountType());
								fromAccBean.setBankCode(pexRequest.getPexTransactionDetails().getFromAccount().getBankCode());
								fromAccBean.setCurrencyCode(pexTrax.getCurrency());
								String fromAccType = fromAccBean.getAccountTypeCode();
								
								
								
								HlbCustomerProfile custProfile = (HlbCustomerProfile)dao.findByID(HlbCustomerProfile.class, pexTrax.getCif());
								
								String payeeName = pexRequest.getPexTransactionDetails().getCollectionAccount().getAccountName();
								String senderName = custProfile.getFullName();
								String remark = pexTrax.getRefNo()+" "+pexRequest.getPexTransactionDetails().getRemark();
								
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
								
								ObEAIHeader resultFT = service.performFundTransfer(arg0.getTranxID(), fromAccBean.getAccountNumber(), senderName, pexTrax.getAmountEarmark(), 
										collectionAccBean.getAccountNumber(), payeeName, transactionDate,
										dailyRunningNumber_thirdParty, fromAccType, toAccType, 
										fromAccBean.getCurrencyCode(), collectionAccBean.getCurrencyCode(), resultEarMark.getSequenceNo());
								//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI SERVICE
								
							//	String resultFT = rbsServices.thirdPartyTransferKH(pexResponse.getPexTransactionDetails().isPExDirect(),fromAccBean,collectionAccBean,fromAccType, toAccType, pexTrax.getAmountPex(), pexTrax.getAmountEarmark(),pexTrax.getAmountCharges(), pexTrax.getCurrency(), pexTrax.getRbsholdRef(), transactionDate, dailyRunningNumber_thirdParty,dailyRunningNumber_thirdParty2,pexTrax.getRefNo(), payeeName, (remark+senderName));
								
								
								
								
								
								
								String responseLogMsg = ("PEX Direct thirdPartyTransfer : ["+fromAccBean.getAccountNumber()+"] - ["+collectionAccBean.getAccountNumber()+"] - ["+pexTrax.getAmountPex()+"] : RBS RESULT - ["+resultFT+"]");
								
								if(null==resultFT.getErrorResponseCode())
								{
									
									
									//TIMEOUT ///////////////////////////RELEASE EAR ONCE ONLY
									dailyRunningNumber = pexServices.updateSequenceNum(transactionDate)+"";
									
									
									//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
									//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
									ObEAIHeader resultRelease = service.performReleaseEarmarkAcc(arg0.getTranxID(), pexResponse.getPexTransactionDetails().getFromAccount(), new BigDecimal(pexResponse.getPexTransactionDetails().getAmount()), pexResponse.getPexTransactionDetails().getCurrency(), earMakType, transactionDate, pexTrax.getRbsSeqNo() , dailyRunningNumber);
									//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
									
									
									
									//String resultRelease = rbsServices.releaseEarmarkKH(pexResponse.getPexTransactionDetails().isPExDirect(), pexResponse.getPexTransactionDetails().getFromAccount(), earMakType, new BigDecimal(pexResponse.getPexTransactionDetails().getAmount()), pexResponse.getPexTransactionDetails().getCurrency(), rbsHoldRef, transactionDate, dailyRunningNumber,10000);
									log.info("EARMARK FAILED : RELEASE ONCE : "+resultRelease);
									////////////////////////////////////////////////////////////////
									
									
									
									//if(resultRelease==null || resultRelease.equalsIgnoreCase(PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE))
									if(resultRelease.getErrorResponseCode()==null)
									{
										log.info("RBS CHECK RELEASE EARMARK FOR PEX DIRECT TIMEOUT ["+resultRelease+"]");
										pexTrax.setErrorCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultRelease.getReasonCode());
										pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
										pexTrax.setSmsCreationFlag(null);
										
										if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH"))
										{
											pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
										}
										else
										{
											pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
										}
										
										
										pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
									
									}
									else if(resultRelease.getErrorResponseCode().trim().length()!=0)
									{
										//release earmark failed, amount deducted 
										pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_PAID);
										//pexTrax.setErrorCode(MiBConstant.MIB_RBS_ERROR_PREFIX+PExConstant.PEX_ERROR_CODE_RBS_TIMEOUT);
										pexTrax.setErrorMessage(pexServices.loadCCMsg(pexTrax.getErrorCode(),pexRequest.getUserContext().getLocaleCode()));
										
										if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
											pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS_KH);
										}else{
											pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS);
										}
										
										pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
									
									}	
									else
									{
										//release earmark success, amount being release, transaction failed
										
										pexTrax.setErrorCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultRelease.getReasonCode());
										pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
										pexTrax.setSmsCreationFlag(null);
										if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
											pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
										}else{
											pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
										}
										pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
									}
									
									
									
									
								}
								//EAI response error
								else if(resultFT.getErrorResponseCode().trim().length()!=0)
								{

									
									pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
									pexTrax.setErrorCode(MiBConstant.MIB_EAI_ERROR_PREFIX+resultFT.getReasonCode());
								
									pexTrax.setErrorMessage(pexServices.loadCCMsg(pexTrax.getErrorCode(),pexRequest.getUserContext().getLocaleCode()));
									pexResponse.getPexTransactionDetails().setErrorMessage(pexServices.loadCCMsg(pexTrax.getErrorCode(),pexRequest.getUserContext().getLocaleCode()));
									if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
										pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
									}else{
										pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
									}
									pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
									pexTrax.setErrorMessage(pexServices.loadCCMsg(pexTrax.getErrorCode(),pexRequest.getUserContext().getLocaleCode()));
									pexTrax.setSmsCreationFlag(null);
									String dailyRunningNumber_release = pexServices.updateSequenceNum(transactionDate)+"";
									
									
									
									//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
									//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
									ObEAIHeader releaseRsp = service.performReleaseEarmarkAcc(arg0.getTranxID(), pexResponse.getPexTransactionDetails().getFromAccount(), new BigDecimal(pexResponse.getPexTransactionDetails().getAmount()), pexResponse.getPexTransactionDetails().getCurrency(), earMakType, transactionDate, resultEarMark.getSequenceNo(), dailyRunningNumber_release);
									//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<EAI service
									
									
									//String releaseRsp = rbsServices.releaseEarmarkKH(pexResponse.getPexTransactionDetails().isPExDirect(),fromAccBean,fromAccType, pexTrax.getAmountEarmark(), pexTrax.getCurrency(), pexTrax.getRbsholdRef(), transactionDate, dailyRunningNumber_release,20000);
							
									log.info("DIRECT PEX FAILED : RELEASE EARMARK : "+releaseRsp);
								}
								else
								{
									
									pexTrax.setStatus(PExConstant.PEX_TRANSACTION_STATUS_PAID);
									
									if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
										pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS_KH);
									}else{
										pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS);
									}
									
									pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
								}
								
								
								BankRoute bankRoute =  pexServices.getPExBeneficiaryBankCode(PExConstant.FT_BENEFICIARY_BANK_CODE);
								pexTrax.setCollectionBankRouteAbbr(bankRoute.getRouteAbbr());
								
								
								log.info(responseLogMsg);
								pexDBStatus = pexTrax.getStatus();
								dao.updateEntity(pexTrax);
								
							}
							else
							{
								
								String notice = pexServices.loadMsgx("notice.pex", pexRequest.getUserContext().getLocaleCode());
								notice = notice.replaceAll("\\{0\\}", pexConf.getExpiry());
								pexResponse.getPexTransactionDetails().setNotice(notice);
								
								pexResponse.getPexTransactionDetails().setCollectionCode(collectionCode);
								if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
									pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_PENDING_COLLECTION_KH);
								}else{
									pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_PENDING_COLLECTION);
								}
								
								
								pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
							}
						}
						
					}
					
				
				//} <<<tac
			}
			
			

			
			
			
			if(pexResponse.getObHeader().getStatusCode().equalsIgnoreCase(PExConstant.PEX_ERR_COMMON_SUCCESS))
			{
				
				
				
				
				//insert fuzion ws and update flag
				//pexTrax.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_ADDED);- user refid
				try
				{
					TransactionServices tranService = new TransactionServices(appContext);
					PexRequest PexRequest = new PexRequest();
					PexRequest.setDescription(pexResponse.getPexTransactionDetails().getRemark());
					PexRequest.setFromAccountNumber(pexResponse.getPexTransactionDetails().getFromAccount().getAccountNumber());
					PexRequest.setFromCurrencyCode(pexResponse.getPexTransactionDetails().getFromAccount().getCurrencyCode());
					PexRequest.setFromProductTypeCode(pexResponse.getPexTransactionDetails().getFromAccount().getAccountTypeCode());
					PexRequest.setMobileNumber(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
					PexRequest.setReferenceNumber(pexResponse.getPexTransactionDetails().getReferenceNumber());
					PexRequest.setSenderName(pexResponse.getPexTransactionDetails().getPayeeName());
					PexRequest.setServiceChargeAmount(new BigDecimal(pexResponse.getPexTransactionDetails().getServiceCharge().replaceAll(",", "")));
					PexRequest.setTransactionAmount(new BigDecimal(pexResponse.getPexTransactionDetails().getAmount().replaceAll(",", "")));
					
					GregorianCalendar gc = new GregorianCalendar();
		            gc.setTimeInMillis(transactionDate.getTime());
		            XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
		                    .newXMLGregorianCalendar(
		                        gc);
		         
					PexRequest.setTransactionDate(calendar);
					PexRequest.setTransactionStatusCode(pexDBStatus);
					TransactionResponse updatePexIB = tranService.insertIBPExTransaction(pexRequest.getUserContext(), PexRequest,  arg0.getTranxID());
				
					if(updatePexIB.getResponse().getStatusCode()==1)
					{
						updateIBFlag(true,pexResponse.getPexTransactionDetails().getReferenceNumber(),updatePexIB.getTransactionId());
					}
					else
					{
						updateIBFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber(),"");
					}
					
				}catch(Exception ex)
				{
					updateIBFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber(),"");
				}
				
				
				
				
				
				
				
				
				
				if(pexResponse.getPexTransactionDetails().getStatus().equalsIgnoreCase(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS) 
						|| pexResponse.getPexTransactionDetails().getStatus().equalsIgnoreCase(PExConstant.PEX_STATUS_TRANSACTION_PENDING_COLLECTION)
						|| pexResponse.getPexTransactionDetails().getStatus().equalsIgnoreCase(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS_KH) 
						|| pexResponse.getPexTransactionDetails().getStatus().equalsIgnoreCase(PExConstant.PEX_STATUS_TRANSACTION_PENDING_COLLECTION_KH))
				{
					
					
					/*sms001=<Name> has PEx you RM<Amount>.<Ref No>. Call sender for code & claim at www.HongLeongConnect.my within 3 days. <Messsage>
					sms002=<Name> has PEx you RM<Amount>.<Ref No>. Amount has been transferred to your PEx Direct Account : <Acct No>. <Message>*/
					
					if(pexResponse.getPexTransactionDetails().isInternetCollection())
					{
						String firstName =  stringUtil.getLimitedChac(15, 0, pexResponse.getPexTransactionDetails().getSenderName());
						String sms001 = pexServices.loadMsgx("sms001",pexRequest.getUserContext().getLocaleCode());
						sms001 = sms001.replaceAll("<Name>", firstName);
						sms001 = sms001.replaceAll("<Amount>", pexResponse.getPexTransactionDetails().getAmount().replaceAll("\\.00", ""));
						sms001 = sms001.replaceAll("<Ref No>", pexResponse.getPexTransactionDetails().getReferenceNumber());
						sms001 = sms001.replaceAll("<xdays>",  pexConf.getExpiry());
						sms001 = sms001.replaceAll("<Message>",  pexResponse.getPexTransactionDetails().getRemark());
						
						try
						{
						
							SecuirtyService secService = new SecuirtyService(appContext);
							SendNotificationForPreLoginRequest notificationRequest = new SendNotificationForPreLoginRequest();
							notificationRequest.setMessageTemplateCode("PTA_MAKE_PEX_INTERNET");
							notificationRequest.setMessageBody(sms001);
							notificationRequest.setReferenceId(arg0.getTranxID());
							notificationRequest.setCifId(pexRequest.getObUser().getCifNumber());
							notificationRequest.setMobileNumber(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
							EndpointResponse respEndPoint = secService.sendPreLoginNotification(pexRequest.getUserContext(), notificationRequest, arg0.getTranxID());
							if(respEndPoint.getResponse().getStatusCode()==1)
							{
								updateSMSFlag(true,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
							else
							{
								updateSMSFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
						
						}catch(Exception e)
						{
							e.printStackTrace();
							updateSMSFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber());
						}
						
						//send sms and update flag
						//pexTrax.setSmsCreationFlag(PExConstant.PEX_SMS_FLAG_SENT); - user refid
						
					}
					else if(pexResponse.getPexTransactionDetails().isAtmCollection())
					{
						String firstName =  stringUtil.getLimitedChac(15, 0, pexResponse.getPexTransactionDetails().getSenderName()); 
						String sms001 = pexServices.loadMsgx("sms005",pexRequest.getUserContext().getLocaleCode());
						sms001 = sms001.replaceAll("<Name>", firstName);
						sms001 = sms001.replaceAll("<Amount>", pexResponse.getPexTransactionDetails().getAmount().replaceAll("\\.00", ""));
						sms001 = sms001.replaceAll("<Ref No>", pexResponse.getPexTransactionDetails().getReferenceNumber());
						sms001 = sms001.replaceAll("<xdays>",  pexConf.getExpiry());
						sms001 = sms001.replaceAll("<ATMPIN>",  atmPin);
						sms001 = sms001.replaceAll("<Message>",  pexResponse.getPexTransactionDetails().getRemark());
						
						try
						{
						
							SecuirtyService secService = new SecuirtyService(appContext);
							SendNotificationForPreLoginRequest notificationRequest = new SendNotificationForPreLoginRequest();
							notificationRequest.setMessageTemplateCode("PTA_MAKE_PEX_INTERNET");
							notificationRequest.setMessageBody(sms001);
							notificationRequest.setReferenceId(arg0.getTranxID());
							notificationRequest.setCifId(pexRequest.getObUser().getCifNumber());
							notificationRequest.setMobileNumber(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
							EndpointResponse respEndPoint = secService.sendPreLoginNotification(pexRequest.getUserContext(), notificationRequest, arg0.getTranxID());
							if(respEndPoint.getResponse().getStatusCode()==1)
							{
								updateSMSFlag(true,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
							else
							{
								updateSMSFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
						
						}catch(Exception e)
						{
							e.printStackTrace();
							updateSMSFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber());
						}
					}
					else
					{
						String firstName =  stringUtil.getLimitedChac(15, 0, pexResponse.getPexTransactionDetails().getSenderName()); 
						String sms002 = pexServices.loadMsgx("sms002",pexRequest.getUserContext().getLocaleCode());
						sms002 = sms002.replaceAll("<Name>", firstName);
						sms002 = sms002.replaceAll("<Amount>", pexResponse.getPexTransactionDetails().getAmount().replaceAll("\\.00", ""));
						sms002 = sms002.replaceAll("<Ref No>", pexResponse.getPexTransactionDetails().getReferenceNumber());
						sms002 = sms002.replaceAll("<Acct No>", pexResponse.getPexTransactionDetails().getCollectionAccount().getAccountNumber());
						sms002 = sms002.replaceAll("<Message>",  pexResponse.getPexTransactionDetails().getRemark());
						
						try
						{
						
							SecuirtyService secService = new SecuirtyService(appContext);
							SendNotificationForPreLoginRequest notificationRequest = new SendNotificationForPreLoginRequest();
						
							notificationRequest.setMessageTemplateCode("PTA_MAKE_PEX_DIRECT");
							notificationRequest.setMessageBody(sms002);
							notificationRequest.setReferenceId(arg0.getTranxID());
							notificationRequest.setCifId(pexRequest.getObUser().getCifNumber());
							notificationRequest.setMobileNumber(pexResponse.getPexTransactionDetails().getPayeeMsisdn());
							EndpointResponse respEndPoint = secService.sendPreLoginNotification(pexRequest.getUserContext(), notificationRequest, arg0.getTranxID());
							if(respEndPoint.getResponse().getStatusCode()==1)
							{
								updateSMSFlag(true,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
							else
							{
								updateSMSFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber());
							}
						
						}catch(Exception e)
						{
							e.printStackTrace();
							updateSMSFlag(false,pexResponse.getPexTransactionDetails().getReferenceNumber());
						}
						
						//pex direct sms
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



	
	
	
	public void updateIBFlag(boolean success, String refID, String tranxid)
	{
		
		String updateIBTransSQL = "UPDATE HlbPexProcessTranx SET update_ib_flag = '<ibFlagStatus>' , update_ib_id='"+tranxid+"' WHERE ref_no='"+refID+"' AND  update_ib_flag = '"+PExConstant.PEX_IB_FLAG_UPDATING+"'"; 
		
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
	
	
	
	
	
	
	public void updateSMSFlag(boolean success, String refID)
	{
		
		String updateIBTransSQL = "UPDATE HlbPexProcessTranx SET sms_creation_flag = '<smsFlagStatus>' WHERE ref_no='"+refID+"' AND sms_creation_flag = '"+PExConstant.PEX_SMS_FLAG_PROCCESSING+"'"; 
		
		if(success)
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<smsFlagStatus>", PExConstant.PEX_SMS_FLAG_SENT);
		}
		else
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<smsFlagStatus>", PExConstant.PEX_SMS_FLAG_PENDING);
		}
		
		dao.updateSQL(updateIBTransSQL);
		
	}
	
	
	
	
	
	/*@Override
	public MuleContext getMuleContext() {
		// TODO Auto-generated method stub
		return muleContext;
	}


	@Override
	public void setMuleContext(MuleContext arg0) {
		// TODO Auto-generated method stub
		
		
		
		
		muleContext = arg0;
	}
	*/
	
	
	
}