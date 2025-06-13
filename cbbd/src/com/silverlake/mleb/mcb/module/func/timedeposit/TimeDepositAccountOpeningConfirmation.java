package com.silverlake.mleb.mcb.module.func.timedeposit;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListPurposeCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListRolloverCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListSourceOfFundCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListTimeDepositProduct;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositSubmitRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefResponseData;

@Service
public class TimeDepositAccountOpeningConfirmation extends CacheSessionFuncServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache>{
	private static Logger log = LogManager.getLogger(TimeDepositAccountOpeningConfirmation.class);
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ObTimeDepositSessionCache cacheBean, VCGenericService vcService) throws Exception {
		TimeDepositSubmitRequestData requestData = new TimeDepositSubmitRequestData();
		TimeDepositResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean, vcService);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<TimeDepositResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TIME_DEPOSIT_OPENING_INQUIRY_RATE, 
				requestData, 
				TimeDepositResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, responseData, requestBean, responseBean);
		requestBean.setSessionCache(null);
		cacheBean.setInquiryRateRequestData(requestData);
		cacheBean.setInquiryRateResponseData(responseData);
		cacheBean.setSourceOfFundDesc(responseBean.getSourceOfFundDesc());
		cacheBean.setPurposeDesc(responseBean.getPurposeDesc());
		cacheBean.setRolloverTypeDesc(responseBean.getRolloverTypeDesc());
		cacheBean.setInterestRate(responseBean.getInterestRate());
		cacheBean.setTransactionId(responseBean.getTransactionId());
		cacheBean.setDebitAccountAliasName(responseBean.getDebitAccountAliasName());
		cacheBean.setDebitAccountType(responseBean.getDebitAccountType());
	}
	
	private void processRequest(String locale, TimeDepositSubmitRequestData requestData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, VCGenericService vcService) throws Exception{
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(MiBConstant.PRODUCT_CODE_TDAO);
		if(sofResponseDataCache == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund sub cache is not found");
			return;
		}
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
				responseBean.setDebitAccountNo(acc.getAcct_no());
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//return to FE later
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());//return to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		
		ObTimeDepositSessionCache step1SessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1.getId());
		if(step1SessionCache.getParameterResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Step 1 cache not found");
			return;
		}
		
		ObTimeDepositSessionCache productListSessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_PROD_LIST.getId());
		if(productListSessionCache.getProductListResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Product list cache not found");
			return;
		}
		
		boolean isProductFound = false;
		for(ListTimeDepositProduct listTimeDepositProduct:productListSessionCache.getProductListResponseData().getProd_detail_list()) {
			if(listTimeDepositProduct.getCcy_code().equals(requestBean.getAmountCcy()) && 
					listTimeDepositProduct.getTenor_type().equals(requestBean.getTenorType()) &&
					listTimeDepositProduct.getTenor_value().compareTo(requestBean.getTenor()) == 0) {
				requestData.setProd_cd(listTimeDepositProduct.getProd_cd());
				isProductFound = true;
				break;
			}
		}
		if(!isProductFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Product not match with product list");
			return;
		}
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setPurpose_cd(requestBean.getPurposeCode());
		requestData.setSource_fund_cd(requestBean.getSourceOfFundCode());
		requestData.setRollover_type_cd(requestBean.getRolloverTypeCode());
		
		//Generate bank reference number
		TransactionBankRefRequestData requestBankRefData = new TransactionBankRefRequestData();
		requestBankRefData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestBankRefData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestBankRefData.setProd_cd(MiBConstant.PRODUCT_CODE_TDAO);
		VCResponse<TransactionBankRefResponseData> vcBankRefResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_GET_BANK_REFERENCE, 
				requestBankRefData, 
				TransactionBankRefResponseData.class, 
				true);
		if(processVCResponseError(vcBankRefResponse, responseBean)){
			return;
		}
		TransactionBankRefResponseData responseBankRefData = vcBankRefResponse.getData();
		requestData.setBank_ref(responseBankRefData.getBank_ref());
		requestData.setAmount(requestBean.getAmount());
		requestData.setAmount_ccy(requestBean.getAmountCcy());
		requestData.setTenor(requestBean.getTenor());
		requestData.setTenor_type(requestBean.getTenorType());
		requestData.setInterest_term(requestBean.getInterestTerm());
		requestData.setInterest_term_code(requestBean.getInterestTermCode());
		requestData.setSpecial_cd(requestBean.getPromoCode());
		
		//Set value from request data into responseBean
		responseBean.setAmount(requestData.getAmount());
		responseBean.setAmountCcy(requestData.getAmount_ccy());
		responseBean.setSourceOfFundCode(requestData.getSource_fund_cd());
		responseBean.setPurposeCode(requestData.getPurpose_cd());
		responseBean.setTenor(requestData.getTenor());
		responseBean.setTenorType(requestData.getTenor_type());
		responseBean.setInterestTerm(requestData.getInterest_term());
		responseBean.setInterestTermCode(requestData.getInterest_term_code());
		responseBean.setRolloverTypeCode(requestData.getRollover_type_cd());
		
		//Get the description from common parameter
		for(ListSourceOfFundCode temp:step1SessionCache.getParameterResponseData().getSource_fund_list()) {
			if(temp.getSource_fund_code().equals(requestData.getSource_fund_cd())){
				if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
					responseBean.setSourceOfFundDesc(temp.getSource_fund_desc_en());
				}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					responseBean.setSourceOfFundDesc(temp.getSource_fund_desc_cn());
				}else {
					responseBean.setSourceOfFundDesc(temp.getSource_fund_desc_id());
				}
				break;
			}
		}
		for(ListPurposeCode temp:step1SessionCache.getParameterResponseData().getPurpose_code_list()) {
			if(temp.getPurpose_code().equals(requestData.getPurpose_cd())){
				if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
					responseBean.setPurposeDesc(temp.getPurpose_desc_en());
				}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					responseBean.setPurposeDesc(temp.getPurpose_desc_cn());
				}else {
					responseBean.setPurposeDesc(temp.getPurpose_desc_id());
				}
				break;
			}
		}
		for(ListRolloverCode temp:step1SessionCache.getParameterResponseData().getRollover_type_list()) {
			if(temp.getRollover_code().equals(requestData.getRollover_type_cd())){
				if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
					responseBean.setRolloverTypeDesc(temp.getRollover_desc_en());
				}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					responseBean.setRolloverTypeDesc(temp.getRollover_desc_cn());
				}else {
					responseBean.setRolloverTypeDesc(temp.getRollover_desc_id());
				}
				break;
			}
		}
	}
	
	private void processResponse(String locale, TimeDepositResponseData responseData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean) throws Exception{
		responseBean.setInterestRate(responseData.getCustomer_rate());
		responseBean.setTransactionId(UUID.randomUUID().toString());
	}
}
