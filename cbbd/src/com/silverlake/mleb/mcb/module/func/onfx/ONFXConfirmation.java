package com.silverlake.mleb.mcb.module.func.onfx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObForexRequest;
import com.silverlake.mleb.mcb.bean.ObForexResponse;
import com.silverlake.mleb.mcb.bean.ObForexSessionCache;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.bean.common.ObCurrencyRate;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.onfx.ListOnfxRate;
import com.silverlake.mleb.mcb.module.vc.onfx.RequestData;
import com.silverlake.mleb.mcb.module.vc.onfx.ResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;

/**
 * Confirmation step of onfx which performing currency rate calculation by calling omni web service of
 * onfx/rate - Depreciated onfx/rate due to incorrect calculation on omni side
 * v2/onfx/rate
 * 
 * @author Alex Loo
 */

@Service
public class ONFXConfirmation extends CacheSessionFuncServices<ObForexRequest, ObForexResponse, ObForexSessionCache>{
	private static Logger log = LogManager.getLogger(ONFXConfirmation.class);
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObForexRequest requestBean, ObForexResponse responseBean, ObForexSessionCache cacheBean, VCGenericService vcService) throws Exception {
		RequestData requestData = new RequestData();
		ResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<ResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ONFX_RATE_V2, 
				requestData, 
				ResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, responseData, requestBean, responseBean);
		if(requestBean.getIsPreValidate() != null && requestBean.getIsPreValidate()) {
			//If error on processing response, return 
			if(responseBean.getObHeader().getStatusCode() != null){
				return;
			}
			RequestData requestDataONFXPost = new RequestData();
			processRequestONFXPost(locale, requestDataONFXPost, requestBean, responseBean, responseData);
			//If error on processing response, return 
			if(responseBean.getObHeader().getStatusCode() != null){
				return;
			}
			VCResponse<ResponseData> vcResponseONFXPost = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.ONFX_POST_V2, 
					requestDataONFXPost, 
					ResponseData.class, 
					true);
			if(processVCResponseError(vcResponseONFXPost, responseBean)){
				return;
			}
		}
		requestBean.setSessionCache(null);
		cacheBean.setRequestBean(requestBean);
		cacheBean.setRate(responseBean.getRate());
		cacheBean.setOnfxRateResponseData(responseData);
	}
	
	private void processRequest(RequestData requestData, ObForexRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setCcy_cd1(requestBean.getCurrency());
		if(requestBean.getCurrency2() == null || requestBean.getCurrency2().isEmpty()) {
			requestData.setCcy_cd2(MiBConstant.CURRENCY_IDR);//Always set IDR for this field
		}else {
			requestData.setCcy_cd2(requestBean.getCurrency2());
		}
	}
	
	private void processResponse(String locale, ResponseData responseData, ObForexRequest requestBean, ObForexResponse responseBean) throws Exception{
		responseBean.setListCurrency(new ArrayList<ObCurrencyRate>());
		List<ListOnfxRate> listOnfxRate = responseData.getList_onfx_rate_v2();
		if(listOnfxRate == null || listOnfxRate.isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Currency not found in omni response");
			return;
		}
		ListOnfxRate rateItem = listOnfxRate.get(0);
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(MiBConstant.PRODUCT_CODE_ONFX);
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());
				responseBean.setDebitAccountNo(acc.getAcct_no());
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());
				responseBean.setSellCcy(acc.getAcct_ccy());
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
		if(requestBean.getBeneAccountUUID()!= null && !requestBean.getBeneAccountUUID().isEmpty()){
			if(transactionSOFSessionCache.getVcBeneficiaryResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction beneficiary list cache is not found");
				return;
			}
			SourceAccountResponseData beneficiaryAcctResponseDataCache = transactionSOFSessionCache.getVcBeneficiaryResponseData().get(MiBConstant.PRODUCT_CODE_ONFX);
			boolean isBeneAccountFound = false;
			for(ListAccount acc:beneficiaryAcctResponseDataCache.getList_account()){
//						if(acc.getId().equals(requestBean.getBeneAccountId())){
				if(acc.getUuid().equals(requestBean.getBeneAccountUUID())){
					responseBean.setBeneAccountAliasName(acc.getAcct_name());
					responseBean.setBeneAccountNo(acc.getAcct_no());
					responseBean.setBeneAccountType(acc.getAcct_type_sibs());
					responseBean.setBuyCcy(acc.getAcct_ccy());
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
		
		responseBean.setPairCcy(rateItem.getBased_ccy()+"/"+rateItem.getQuote_ccy());
		responseBean.setPurpose(requestBean.getPurpose());
		GeneralCode purposeGnCode = gnDao.findByGnCodeAndGnCodeType(responseBean.getPurpose(), MiBConstant.PURPOSE_LIST, locale);
		if(purposeGnCode != null){
			responseBean.setPurpose(purposeGnCode.getGnCodeDescription());
		}
		responseBean.setRemark(requestBean.getRemark());
		
		/**
		 * Amount that filled up by customer set to either selling amount or buying amount depending on the amount currency that is being chosen.
		 * Calculate selling amount when buying amount is set
		 * Calculate buying amount when selling amount is set
		 */
		if(responseBean.getSellCcy().equalsIgnoreCase(rateItem.getBased_ccy())) {
			if(rateItem.getBank_buy_rate() != null && rateItem.getBank_buy_rate().scale() < 2) {
				responseBean.setRate(rateItem.getBank_buy_rate().setScale(2));
			}else {
				responseBean.setRate(rateItem.getBank_buy_rate());
			}
		}else {
			if(rateItem.getBank_sell_rate() != null && rateItem.getBank_sell_rate().scale() < 2) {
				responseBean.setRate(rateItem.getBank_sell_rate().setScale(2));
			}else {
				responseBean.setRate(rateItem.getBank_sell_rate());
			}
		}
		if(responseBean.getSellCcy().equals(requestBean.getAmountCcy())){
			responseBean.setSellingAmount(requestBean.getAmount());
			if(requestBean.getAmountCcy().equalsIgnoreCase(rateItem.getQuote_ccy())) {
				responseBean.setBuyingAmount(requestBean.getAmount().divide(responseBean.getRate(), 2, RoundingMode.HALF_UP));
			}else {
				responseBean.setBuyingAmount(requestBean.getAmount().multiply(responseBean.getRate()));
			}
		}else {
			responseBean.setBuyingAmount(requestBean.getAmount());
			if(requestBean.getAmountCcy().equalsIgnoreCase(rateItem.getQuote_ccy())) {
				responseBean.setSellingAmount(requestBean.getAmount().divide(responseBean.getRate(), 2, RoundingMode.HALF_UP));
			}else {
				responseBean.setSellingAmount(requestBean.getAmount().multiply(responseBean.getRate()));
			}
		}
	}
	
	private void processRequestONFXPost(String locale, RequestData requestData, ObForexRequest requestBean, ObForexResponse responseBean, ResponseData responseDataONFXRate) throws Exception{
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
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
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
		if(requestBean.getBeneAccountUUID()!= null && !requestBean.getBeneAccountUUID().isEmpty()){
			if(transactionSOFSessionCache.getVcBeneficiaryResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction beneficiary list cache is not found");
				return;
			}
			SourceAccountResponseData beneficiaryAcctResponseDataCache = transactionSOFSessionCache.getVcBeneficiaryResponseData().get(MiBConstant.PRODUCT_CODE_ONFX);
			boolean isBeneAccountFound = false;
			for(ListAccount acc:beneficiaryAcctResponseDataCache.getList_account()){
//				if(acc.getId().equals(requestBean.getBeneAccountId())){
				if(acc.getUuid().equals(requestBean.getBeneAccountUUID())){
					requestData.setBene_acct_ccy(acc.getAcct_ccy());
					requestData.setBene_acct_no(acc.getAcct_no());
					requestData.setBene_acct_name(acc.getAcct_name());
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
		requestData.setAmount(requestBean.getAmount());
		requestData.setAmount_ccy(requestBean.getAmountCcy());
		requestData.setRemarks(requestBean.getRemark());
		requestData.setPurpose(requestBean.getPurpose());
		requestData.setDevice_id(requestBean.getObDevice().getDeviceId());
		requestData.setOnfx_rate(responseDataONFXRate.getList_onfx_rate_v2().get(0));
		requestData.setPrepost_check("Y");
	}
}
