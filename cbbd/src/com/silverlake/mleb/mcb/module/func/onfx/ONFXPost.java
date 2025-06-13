package com.silverlake.mleb.mcb.module.func.onfx;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObForexRequest;
import com.silverlake.mleb.mcb.bean.ObForexResponse;
import com.silverlake.mleb.mcb.bean.ObForexSessionCache;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTNCCache;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.onfx.RequestData;
import com.silverlake.mleb.mcb.module.vc.onfx.ResponseData;
import com.silverlake.mleb.mcb.module.vc.others.TNCRequestData;
import com.silverlake.mleb.mcb.module.vc.others.TNCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

@Service
public class ONFXPost extends CacheSessionFuncServices<ObForexRequest, ObForexResponse, ObForexSessionCache>{
	private static Logger log = LogManager.getLogger(ONFXPost.class);
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObForexRequest requestBean, ObForexResponse responseBean, ObForexSessionCache cacheBean, VCGenericService vcService) throws Exception {
		//Get the term and condition cache exist and match or not.
		ObTNCCache tncCache = (ObTNCCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_GET.getId()) ; 
		if(tncCache.getOmniResponse() == null || tncCache.getRequestData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Unable to get cache data");
			return;
		}
		if(!tncCache.getRequestData().getTncType().equals(MiBConstant.PRODUCT_CODE_ONFX)){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("tnc type not matched. "+tncCache.getRequestData().getTncType());
			return;
		}
		RequestData requestData = new RequestData();
		ResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<ResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ONFX_POST_V2, 
				requestData, 
				ResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
		
		//If error on processing response, return 
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		
		if(requestBean.getAction() == null || requestBean.getAction().equalsIgnoreCase("SUBMIT")) {
			//Send the term and condition confirmation to omni and do not interrupt successful transaction
			try{
				TNCRequestData tncRequestData = new TNCRequestData();
				tncRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
				tncRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
				tncRequestData.setTnc_type(tncCache.getRequestData().getTncType());
				tncRequestData.setTxn_reference_no(responseData.getTrx_data().getBank_ref());
				tncRequestData.setFile(tncCache.getOmniResponse().getFile());
				vcService.callOmniVC(
						VCMethodConstant.REST_METHODS.OTHERS_TNC_CONFIRM, 
						tncRequestData, 
						TNCResponseData.class, 
						true);
				//Do not care about the negative response of tnc
			}catch(Exception e){
				log.catching(e);
			}
		}
	}
	
	private void processRequest(String locale, RequestData requestData, ObForexRequest requestBean, ObForexResponse responseBean) throws Exception{
		ObForexSessionCache onFXConfirmationCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ONFX_CONFIRMATION.getId());
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(MiBConstant.PRODUCT_CODE_ONFX);
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
//			if(acc.getId().equals(requestBean.getDebitAccountId())){
			if(acc.getUuid().equals(onFXConfirmationCache.getRequestBean().getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//Return back to FE later
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());//Return back to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		//Bene account select from list
		if(onFXConfirmationCache.getRequestBean().getBeneAccountUUID()!= null && !onFXConfirmationCache.getRequestBean().getBeneAccountUUID().isEmpty()){
			if(transactionSOFSessionCache.getVcBeneficiaryResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction beneficiary list cache is not found");
				return;
			}
			SourceAccountResponseData beneficiaryAcctResponseDataCache = transactionSOFSessionCache.getVcBeneficiaryResponseData().get(MiBConstant.PRODUCT_CODE_ONFX);
			boolean isBeneAccountFound = false;
			for(ListAccount acc:beneficiaryAcctResponseDataCache.getList_account()){
//				if(acc.getId().equals(requestBean.getBeneAccountId())){
				if(acc.getUuid().equals(onFXConfirmationCache.getRequestBean().getBeneAccountUUID())){
					requestData.setBene_acct_ccy(acc.getAcct_ccy());
					requestData.setBene_acct_no(acc.getAcct_no());
					requestData.setBene_acct_name(acc.getAcct_name());
					responseBean.setBeneAccountType(acc.getAcct_type_sibs());//Return back to FE later
					isBeneAccountFound = true;
					break;
				}
			}
			if(!isBeneAccountFound){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Account ID not matched with beneficiary list");
				return;
			}
		}
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAmount(onFXConfirmationCache.getRequestBean().getAmount());
		requestData.setAmount_ccy(onFXConfirmationCache.getRequestBean().getAmountCcy());
		requestData.setRemarks(onFXConfirmationCache.getRequestBean().getRemark());
		requestData.setPurpose(onFXConfirmationCache.getRequestBean().getPurpose());
		requestData.setDevice_id(requestBean.getObDevice().getDeviceId());
		requestData.setOnfx_rate(onFXConfirmationCache.getOnfxRateResponseData().getList_onfx_rate_v2().get(0));
		if(requestBean.getAction() != null && requestBean.getAction().equalsIgnoreCase("CANCEL")) {
			requestData.setPrepost_check("X");
		}
		
		responseBean.setPurpose(onFXConfirmationCache.getRequestBean().getPurpose());//Use to return back to FE later
		GeneralCode purposeGnCode = gnDao.findByGnCodeAndGnCodeType(responseBean.getPurpose(), MiBConstant.PURPOSE_LIST, locale);
		if(purposeGnCode != null){
			responseBean.setPurpose(purposeGnCode.getGnCodeDescription());
		}
		responseBean.setRemark(onFXConfirmationCache.getRequestBean().getRemark());//Use to return back to FE later
	}
	
	private void processResponse(String locale, RequestData requestData, ResponseData responseData, ObForexRequest requestBean, ObForexResponse responseBean) throws Exception{
		if(responseData.getTrx_data() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
			return;
		}
		Transaction trxData = responseData.getTrx_data();
		responseBean.setRefId(responseData.getTrx_data().getBank_ref());
		
		responseBean.setPairCcy(requestData.getOnfx_rate().getBased_ccy()+"/"+requestData.getOnfx_rate().getQuote_ccy());
		if(trxData.getFx_rate() != null && trxData.getFx_rate().scale() < 2) {
			responseBean.setRate(trxData.getFx_rate().setScale(2));
		}else {
			responseBean.setRate(trxData.getFx_rate());
		}
		responseBean.setDebitAccountNo(trxData.getDebit_acct_no());
		responseBean.setBeneAccountAliasName(trxData.getBene_name());
		responseBean.setBeneAccountNo(trxData.getBene_acct_no());
		responseBean.setSellCcy(trxData.getDebit_acct_ccy());
		responseBean.setBuyCcy(trxData.getBene_acct_ccy());
		/**
		 * Amount that filled up by customer set to either selling amount or buying amount depending on the amount currency that is being chosen.
		 * Calculate selling amount when buying amount is set
		 * Calculate buying amount when selling amount is set
		 */
		if(trxData.getDebit_acct_ccy().equals(trxData.getAmount_ccy())){
			responseBean.setSellingAmount(trxData.getAmount());
			if(trxData.getAmount_ccy().equalsIgnoreCase(requestData.getOnfx_rate().getQuote_ccy())) {
				responseBean.setBuyingAmount(trxData.getAmount().divide(trxData.getFx_rate(), 2, RoundingMode.HALF_UP));
			}else {
				responseBean.setBuyingAmount(trxData.getAmount().multiply(trxData.getFx_rate()));
			}
		} else{
			responseBean.setBuyingAmount(trxData.getAmount());
			if(trxData.getAmount_ccy().equalsIgnoreCase(requestData.getOnfx_rate().getQuote_ccy())) {
				responseBean.setSellingAmount(trxData.getAmount().divide(trxData.getFx_rate(), 2, RoundingMode.HALF_UP));
			}else {
				responseBean.setSellingAmount(trxData.getAmount().multiply(trxData.getFx_rate()));
			}
		}
		
		responseBean.setTrxStatusCode(trxData.getTrx_status());
		responseBean.setTrxStatusDesc(getTrxStatusDesc(locale, trxData.getTrx_status()));
		if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			responseBean.setErrorDesc(responseData.getError_message_en());	
		}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			responseBean.setErrorDesc(responseData.getError_message_cn());	
		}else {
			responseBean.setErrorDesc(responseData.getError_message_id());
		}
	}
	
//	public BigDecimal calculateAmount(BigDecimal amount, String amountCcy, String sellCcy, String buyCcy, BigDecimal exchangeRate){
//		if(exchangeRate != null && amount != null && amountCcy != null){
//			if(amountCcy.equalsIgnoreCase(MiBConstant.CURRENCY_IDR)){
//				return amount.divide(exchangeRate, 2, RoundingMode.HALF_UP);
//			}else{
//				return amount.multiply(exchangeRate);
//			}
//		}else{
//			return null;
//		}
//	}
	
	public String getTrxStatusDesc(String locale, String trxStatus) throws Exception{
		if(trxStatus == null){
			return null;
		}
		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE_IN);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_CN.toLowerCase())){
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE_CN);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}else{
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}
		return null;
	}
}
