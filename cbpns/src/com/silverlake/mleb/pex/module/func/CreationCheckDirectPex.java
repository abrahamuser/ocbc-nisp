package com.silverlake.mleb.pex.module.func;



import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fuzion.ws.common.endpoint.CutOffTimeResponse;
import com.fuzion.ws.transaction.endpoint.TransactionLimitRequest;
import com.fuzion.ws.transaction.endpoint.TransactionLimitResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObAccountInquiryBean;
import com.silverlake.mleb.pex.bean.ObCIFDetailsBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.eai.EAIService;
import com.silverlake.mleb.pex.entity.PexAccount;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.TransactionServices.TransactionServices;
import com.silverlake.mleb.pex.module.ib.commonServices.CommoService;
import com.silverlake.mleb.pex.module.rbs.services.AccountInquiryServices;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.module.services.RBSServices;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPDAResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPECResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPERRResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.StringDataUtil;






@Service
public class CreationCheckDirectPex extends FuncServices  
{

	private static Logger log = LogManager.getLogger(CreationCheckDirectPex.class);

	
	@Autowired
	MLEBPExDAO dao;
	@Autowired ApplicationContext appContext;

	 //@Autowired @Qualifier("requestMessageQueue")
	 private JmsTemplate requestMessageQueue;
		 
	//@Autowired @Qualifier("responseMessageQueue")
	private JmsTemplate responseMessageQueue;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
	
		DecimalFormat tranxAmountFormat = new DecimalFormat(PExConstant.PEX_TRANSACTION_AMOUNT_FORMAT);
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		
		
		try {
			Date tranxDate = new Date();
			PExServices pexServices = new PExServices(dao);
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			StringDataUtil stringDataUtil = new StringDataUtil();
			
			EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
			//SIT TESTING USING FIX DATE  ----START
			
			
			 
			
			tranxDate = pexServices.checkRBSDate(tranxDate);
			
			//SIT TESTING USING FIX DATE  ----END
			
			pexResponse.setPexTransactionDetails(pexRequest.getPexTransactionDetails());
			
			
			
			String payeeMobileNumber = pexRequest.getPexTransactionDetails().getPayeeMsisdn();
			
			String payeeName = pexRequest.getPexTransactionDetails().getPayeeName();
			String senderName = pexRequest.getPexTransactionDetails().getSenderName();
			String fromAcc = pexRequest.getPexTransactionDetails().getFromAccount().getAccountNumber();
			
			
			CommoService commonService = new CommoService(appContext);
			CutOffTimeResponse cutResp = commonService.getCutOffTime(pexRequest.getUserContext(),  arg0.getTranxID());
			if(cutResp.getResponse().getStatusCode() == 0)
			{
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+cutResp.getResponse().getErrorCode());
			}
			else if(null==payeeMobileNumber || null==payeeName || senderName==null || null==fromAcc || payeeMobileNumber.trim().length()==0 || payeeName.trim().length()==0 || senderName.trim().length()==0 || fromAcc.trim().length()==0)
			{
				pexResponse = new ObPexResponse();
				pexResponse.setObHeader(new ObHeaderResponse());
				pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_MISSING_FIELD);
			}
			/*			else if(!payeeMobileNumber.matches(PExConstant.contactNumberPattern) && pexRequest.getUserContext().getCountryCode().equalsIgnoreCase(PExConstant.PEX_COUNTRY_CODE_MY))
			{
				pexResponse = new ObPexResponse();
				pexResponse.setObHeader(new ObHeaderResponse());
				pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_TO_NUMBER);
			}*/
			/*else if(!pexServices.checkVNMobileNumber(payeeMobileNumber) && pexRequest.getUserContext().getCountryCode().equalsIgnoreCase(PExConstant.PEX_COUNTRY_CODE_KH))*/
			else if(!payeeMobileNumber.matches(PExConstant.contactNumberPatternKH) && pexRequest.getUserContext().getCountryCode().equalsIgnoreCase(PExConstant.PEX_COUNTRY_CODE_KH))
			{
				pexResponse = new ObPexResponse();
				pexResponse.setObHeader(new ObHeaderResponse());
				pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_TO_NUMBER);
			}
			
			else
			{	
				if(payeeMobileNumber.startsWith("0", 3)){
					payeeMobileNumber = payeeMobileNumber.replaceFirst("0", "");
				}
				
				List<PexAccount> pexMobileAccs = pexServices.getDirectPexMobileActiveAcc(payeeMobileNumber,pexRequest.getUserContext().getCountryCode());
				/*String creditCard = pexRequest.getPexTransactionDetails().getFromAccount().getRefNo();
				if(null==creditCard || creditCard.length()==0)
				{
					creditCard = null;
				}*/
				
				Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
				DataBeanUtil dataBeanUtil = new DataBeanUtil();
				if(pexMobileAccs.size()>0)
				{
					log.info("PEX DIRECT ACCOUNT FOUND : "+payeeMobileNumber);
					//check RBS verify latest mobile number , Account Name, Type, by cif 
					RBSServices rbsServices = new RBSServices(dao);
					String cif = pexMobileAccs.get(0).getCif();
					String currency = pexMobileAccs.get(0).getAccountCurrency();
					String dailyRunningNumber = pexServices.updateSequenceNum(tranxDate)+"";
					//<<<<<<<<<<<<<<<<<<<EAI SERVICES
					
					ObCIFDetailsBean cifDetails = service.performCIFInquiry(arg0.getTranxID(), dailyRunningNumber, cif, currency, "", "", "", tranxDate);
					//<<<<<<<<<<<<<<<<<<EAI SERVICES
					//String rbs = rbsServices.inquiryTACNumberKH(cif, currency, tranxDate, dailyRunningNumber);
					
					
					String mobileNumber1 = "";
					String mobileNumber = "";
					boolean accountstatus = true;
					String accountName = "";
					
					if(cifDetails.getErrorResponseCode() == null)
					{
						//timeout
						pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_EAI_ERROR_PREFIX+cifDetails.getReasonCode());
						
					}
					else if(cifDetails.getErrorResponseCode().trim().length()!=0)
					{
						//return reason code
						
						pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_EAI_ERROR_PREFIX+cifDetails.getReasonCode());
						
						
					}
					else
					{
						
						
						//mobileNumber = inquiryProfileResponse.getResponseEC().getCustContactNumTAC_30_b_$();
						mobileNumber = cifDetails.getContactNo();
						//mobileNumber1 = inquiryProfileResponse.getResponseEC().getCustContactNumTAC1_30_b_$();
						mobileNumber1 = cifDetails.getContactNo1();
						String pexReqMobileNum = pexMobileAccs.get(0).getMobileNo();
						log.info("DB PEX MOBILE NUMBER :: " + pexReqMobileNum);
						/*
						if(mobileNumber.matches(PExConstant.contactNumber2Pattern) )
						{
							mobileNumber = "6"+mobileNumber;
						}
						*/
						//System.out.println("HOST MOBILE NUMBER : "+mobileNumber);
						/*if(pexRequest.getUserContext().getCountryCode().equalsIgnoreCase(PExConstant.PEX_COUNTRY_CODE_MY))
						{
						
							if(pexRequest.getUserContext().getCountryCode().equalsIgnoreCase(MiBConstant.COUNTRY_MY) && !mobileNumber.trim().matches(PExConstant.contactNumberPattern))
							{
								mobileNumber = "6"+mobileNumber.trim();
							}
						}*/
						
						log.info("HOST MOBILE NUMBER LENGTH : "+mobileNumber.length());
						
						log.info("HOST MOBILE NUMBER : ["+mobileNumber.trim()+"]");
						log.info("CHECK MOBILE NUMBER : ["+pexReqMobileNum.trim()+"]");
						if(pexReqMobileNum.trim().equalsIgnoreCase(mobileNumber.trim()) || pexReqMobileNum.trim().equalsIgnoreCase(mobileNumber1.trim()))
						{
						
							log.info("CHECK MOBILE NUMBER : ["+payeeMobileNumber.trim()+"]");
							dailyRunningNumber = pexServices.updateSequenceNum(tranxDate)+"";
							//<<<<<<<<<<<<<<<<<<<<< EAI SERVICE
							//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
							ObAccountInquiryBean accDetails = service.performAccountInquiry(arg0.getTranxID(), dailyRunningNumber, pexMobileAccs.get(0).getAccountNumber(), tranxDate, pexMobileAccs.get(0).getAccountProductType(), pexMobileAccs.get(0).getAccountCurrency());
							//<<<<<<<<<<<<<<<<<<<<< EAI SERVICE
						
							//String accRS = rbsServices.inquiryACCDetailsKH(pexMobileAccs.get(0).getAccountNumber(),pexMobileAccs.get(0).getAccountProductType(), tranxDate, dailyRunningNumber);
							
							
							
							
							if(accDetails.getErrorResponseCode() == null)
							{
								pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_EAI_ERROR_PREFIX + accDetails.getReasonCode());
							}
							else if(accDetails.getErrorResponseCode().trim().length()!=0)
							{
								//return reasonCode
								pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_EAI_ERROR_PREFIX + accDetails.getReasonCode());
								
								/*RBS_DSPERRResponse earMarkERRResponse = new RBS_DSPERRResponse();
								earMarkERRResponse = (RBS_DSPERRResponse) dataBeanUtil.setFieldNamesAndByte(earMarkERRResponse, rbsServices.getFullResponseByte(), "CP037");
							
							
								if(earMarkERRResponse.getResponse42().getErrorCode_2_p_0().trim().length()==0)
								{
									pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_RBS_ERROR_PREFIX+PExConstant.PEX_ERROR_CODE_RBS_UNAVAILABLE);
								}
								else
								{
									pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_RBS_ERROR_PREFIX+earMarkERRResponse.getResponse42().getErrorCode_2_p_0());
								}*/
							}
							else
							{
								//RBS_DSPDAResponse inquiryAccResponse = new RBS_DSPDAResponse();
							
								//inquiryAccResponse = (RBS_DSPDAResponse) dataBeanUtil.setFieldNamesAndByte(inquiryAccResponse, rbsServices.getFullResponseByte(),"CP037");
								
								//log.info(gsonLog.toJson(inquiryAccResponse));
								
								//accountName = inquiryAccResponse.getResponseDA().getAccountName_40_b_$();
								accountName = accDetails.getAccName();
								accountstatus = true;
								
								if(accountstatus)
								{
								
									pexResponse.getPexTransactionDetails().setCollectionAccount(new ObAccountBean());
									pexResponse.getPexTransactionDetails().setCollectionType(PExConstant.PEX_COLLECTION_TYPE_DIRECT);
									pexResponse.getPexTransactionDetails().setCollectionName(PExConstant.PEX_COLLECTION_NAME_DIRECT);
									pexResponse.getPexTransactionDetails().setPExDirect(true);
									
									pexResponse.getPexTransactionDetails().getCollectionAccount().setAccountTypeCode(pexMobileAccs.get(0).getAccountProductType());
									pexResponse.getPexTransactionDetails().getCollectionAccount().setAccountNumber(pexMobileAccs.get(0).getAccountNumber());
									
									if(pexMobileAccs.get(0).getAccountDesc()!=null && pexMobileAccs.get(0).getAccountProductType()!=null){
										pexResponse.getPexTransactionDetails().getCollectionAccount().setAccountDescription(stringDataUtil.getAccMaping(pexMobileAccs.get(0).getAccountDesc(), pexMobileAccs.get(0).getAccountProductType(), arg0.getLocaleCode()));
										
									}else{
										pexResponse.getPexTransactionDetails().getCollectionAccount().setAccountDescription(pexMobileAccs.get(0).getAccountDesc());
									}

									pexResponse.getPexTransactionDetails().getCollectionAccount().setAccountName(accountName);
									pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
								
								}
								else
								{
									//account dormant, continue pex internet/atm
									
									pexResponse.getPexTransactionDetails().setInternetCollection(true);
									pexResponse.getPexTransactionDetails().setAtmCollection(pexServices.showATM());
									pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
									
									
								}
							}
							
	
						}
						else
						{
							//mobile number not match, continue pex internet/atm
							
							pexResponse.getPexTransactionDetails().setInternetCollection(true);
							pexResponse.getPexTransactionDetails().setAtmCollection(pexServices.showATM());
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
						}
		
					}
					
					
				}
				else
				{
					
					pexResponse.getPexTransactionDetails().setInternetCollection(true);
					
					pexResponse.getPexTransactionDetails().setAtmCollection(pexServices.showATM());
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
				}
				
				
				
				//check card exist
				
				//vn no need check
				/*if(null==creditCard && pexResponse.getPexTransactionDetails().isInternetCollection() && !pexResponse.getPexTransactionDetails().isAtmCollection())
				{
					AccountService accService = new AccountService(muleContext);
					DebitCardListResponse debirCardList = accService.fuzionGetDebitCardList(pexRequest.getUserContext(), arg0.getTranxID());
					
					
					if(debirCardList.getResponse().getStatusCode()==1)
					{
					
						List<DebitCardVO> debitList = debirCardList.getDebitCardList();
						creditCard = debitList.size()>0?debitList.get(0).getDebitCardNumber():null;

						if(null==creditCard && pexResponse.getPexTransactionDetails().isInternetCollection() && !pexResponse.getPexTransactionDetails().isAtmCollection())
						{
							pexResponse.getPexTransactionDetails().setInternetCollection(false);
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INTERNET_NOT_ALLOWED);
						}
						else
						{
							pexResponse.getPexTransactionDetails().getFromAccount().setRefNo(creditCard);
						}
					}
					else
					{
						if(debirCardList.getResponse().getErrorCode().equalsIgnoreCase("ERR_INT_OCM_999978"))
						{
							pexResponse.getPexTransactionDetails().setInternetCollection(false);
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INTERNET_NOT_ALLOWED);
						}
						else
						{
							pexResponse.getPexTransactionDetails().setInternetCollection(false);
							pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+debirCardList.getResponse().getErrorCode());
						}
					}
				}*/
				
				

				
				if(pexResponse.getPexTransactionDetails().isAtmCollection())
				{
					TransactionServices transactionServices = new TransactionServices(appContext);
					TransactionLimitRequest limitRq = new TransactionLimitRequest();
					limitRq.setTypeCode("PEX");

					TransactionLimitResponse limitRs = transactionServices.getAvailableTransactionLimit(pexRequest.getUserContext(), limitRq, arg0.getTranxID());
					
					if(limitRs.getResponse().getStatusCode()!=1 )
					{
						pexResponse.setPexTransactionDetails(null);
						pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+limitRs.getResponse().getErrorCode());
					}
					else
					{
						pexResponse.setAtmDenomination(pexServices.getATMDenomination(limitRs.getAvailableLimit()));
					}
				}
					
					

			
				
			}
			
			if(!pexResponse.getObHeader().getStatusCode().equalsIgnoreCase(PExConstant.PEX_ERR_COMMON_SUCCESS))
			{
				String error_status_code = pexResponse.getObHeader().getStatusCode();
				pexResponse = new ObPexResponse();
				pexResponse.setObHeader(new ObHeaderResponse());
				pexResponse.getObHeader().setStatusCode(error_status_code);
				
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


	 /*
		MuleContext muleContext;
		


		@Override
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