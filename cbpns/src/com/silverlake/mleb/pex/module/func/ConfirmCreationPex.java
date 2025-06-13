package com.silverlake.mleb.pex.module.func;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fuzion.ws.security.endpoint.RequestAuthorizationCodeRequest;
import com.fuzion.ws.security.endpoint.RequestAuthorizationCodeResponse;
import com.fuzion.ws.transaction.endpoint.TransactionLimitRequest;
import com.fuzion.ws.transaction.endpoint.TransactionLimitResponse;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexConf;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.TransactionServices.TransactionServices;
import com.silverlake.mleb.pex.module.ib.securityServices.SecuirtyService;
import com.silverlake.mleb.pex.module.services.PExServices;






@Service
public class ConfirmCreationPex extends FuncServices  
{

	private static Logger log = LogManager.getLogger(ConfirmCreationPex.class);
	
	
	@Autowired
	MLEBPExDAO dao;
	
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
	
		DecimalFormat tranxAmountFormat = new DecimalFormat(PExConstant.PEX_TRANSACTION_AMOUNT_FORMAT);
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		
		
		try {
			
			PExServices pexServices = new PExServices(dao);
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			
			if(null == pexRequest.getPexTransactionDetails().getRemark() || pexRequest.getPexTransactionDetails().getRemark().trim().length()==0)
			{
				pexResponse = new ObPexResponse();
				pexResponse.setObHeader(new ObHeaderResponse());
				pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_MISSING_FIELD);
				
			}
			else
			{
			
				String cif = pexRequest.getObUser().getCifNumber();
				Date confirmationDate = new Date();
				pexResponse.setPexTransactionDetails(pexRequest.getPexTransactionDetails());
				
				boolean checkATM = false;
				if(pexServices.showATM() && pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_ATM))
				{
					checkATM = true;
				}
				
				
				//allow ITN & DRT only
				if(!pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_INTERNET) && !pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_DIRECT) && !checkATM)
				{
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_COLLECTION_TYPE);
				}
				else
				{
				
				
					BigDecimal pexAmount = new BigDecimal(pexResponse.getPexTransactionDetails().getAmount());
					PexConf pexConf = pexServices.getPExConf();
					
					//get charges
					BigDecimal charges = new BigDecimal("0.00");
					if(pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_DIRECT))
					{
						charges = pexServices.getCharges(pexConf.getTransactionDRTCharges(),pexAmount);
					}
					else if(pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_ATM))
					{
						charges = pexServices.getCharges(pexConf.getTransactionATMCharges(),pexAmount);
					}
					else
					{
						charges = pexServices.getCharges(pexConf.getTransactionITNCharges(),pexAmount);
					}
					
					
					
					
					
					
					//check available balance
					BigDecimal accAvailable = pexRequest.getPexTransactionDetails().getFromAccount().getAvailableBalance();
					if((pexAmount.add(charges)).compareTo(accAvailable)>0)
					{
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INSUFFICIENT_FUNDS);
					}
					else
					{
						//check limit
						TransactionServices transactionServices = new TransactionServices(appContext);
						TransactionLimitRequest limitRq = new TransactionLimitRequest();
						limitRq.setTypeCode("PEX");
	
						TransactionLimitResponse limitRs = transactionServices.getAvailableTransactionLimit(pexRequest.getUserContext(), limitRq, arg0.getTranxID());
						
						if(limitRs.getResponse().getStatusCode()!=1 )
						{
							pexResponse.setPexTransactionDetails(null);
							pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+limitRs.getResponse().getErrorCode());
						}
						/*else if(pexAmount.compareTo(limitRs.getAvailableLimit())>=0)
						{
							pexResponse.setPexTransactionDetails(null);
							pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_TRANSACTION_LIMIT_EXISTED);
						}*/
						else
						{
							//check dailyLimit
							if(!pexServices.validateCIFDailyPExLimit(cif, limitRs.getAvailableLimit(), confirmationDate,new BigDecimal(pexResponse.getPexTransactionDetails().getAmount())))
							{
								//daily limit exception
								if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
									pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
								}else{
									pexResponse.getPexTransactionDetails().setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
								}
								
								pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_DAILY_LIMIT_EXISTED);
							}
							else
							{
								/*//quick pex limit
								BigDecimal quickPexLimit = null;
								
								if(pexServices.loginTypeContainBiometric(pexRequest.getObUser().getLoginType(), MiBConstant.QUIKC_LOGIN_BIOMETRIC))
								{   
									if(pexRequest.getObUser().getLoginType()!=null)
									{
										quickPexLimit = pexServices.getQuickPexLimit(MiBConstant.QUICK_LOGIN_PEX, arg0.getDeviceBean().getType());
									}
								}
								//check quick biometric limit
								if(null != quickPexLimit && pexAmount.compareTo(quickPexLimit) > 0){
									pexResponse.getObHeader().setStatusCode(PExConstant.PEX_QUICK_PEX_LIMIT_EXCEEDED);
								}
								else{*/
									
									if(pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_ATM))
									{
										pexResponse.getPexTransactionDetails().setAtmCollection(true);
										pexResponse.getPexTransactionDetails().setInternetCollection(false);
									}
									else if((pexRequest.getPexTransactionDetails().getCollectionType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_INTERNET)))
									{
										pexResponse.getPexTransactionDetails().setAtmCollection(false);
										pexResponse.getPexTransactionDetails().setInternetCollection(true);
									}
									
									pexResponse.getPexTransactionDetails().setAmount(tranxAmountFormat.format(new BigDecimal(pexResponse.getPexTransactionDetails().getAmount())));
									
									
									
									
									pexResponse.getPexTransactionDetails().setServiceCharge(tranxAmountFormat.format(charges));
									pexResponse.getPexTransactionDetails().setCurrency(pexConf.getCurrency());
									pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
									
								/*}*/
								
								
								
								
								//vn auto send tac
								/*SecuirtyService securityService = new SecuirtyService(appContext);
								RequestAuthorizationCodeRequest authorizationCode = new RequestAuthorizationCodeRequest();
								authorizationCode.setActionCode(PExConstant.PEX_SMS_TAC_ACTION_CODE);
								authorizationCode.setFunctionCode(PExConstant.PEX_SMS_TAC_FUNC_CODE);
								log.debug("PEX sms TAC");
								
								authorizationCode.setCustomDecimal01(new BigDecimal(pexResponse.getPexTransactionDetails().getAmount().replaceAll(",", "")));
								String beneName = pexResponse.getPexTransactionDetails().getPayeeName();
								beneName = beneName.length()>18?beneName.substring(0,18):beneName;
								authorizationCode.setCustomString01(beneName);
								authorizationCode.setCustomString02(pexResponse.getPexTransactionDetails().getCurrency());
								
								
								RequestAuthorizationCodeResponse respTAC = securityService.requestAuthorizationCode(pexRequest.getUserContext(), authorizationCode, arg0.getTranxID());
								pexResponse.setObUser(pexRequest.getObUser());
								pexResponse.getObUser().setAccessId(respTAC.getAuthorizationUUID());*/
							}
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