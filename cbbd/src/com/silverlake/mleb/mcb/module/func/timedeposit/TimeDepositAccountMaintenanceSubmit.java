package com.silverlake.mleb.mcb.module.func.timedeposit;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListRolloverCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductDetailsRequestData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductDetailsResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositSubmitRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefResponseData;

@Service
public class TimeDepositAccountMaintenanceSubmit extends CacheSessionFuncServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache>{
	private static Logger log = LogManager.getLogger(TimeDepositAccountMaintenanceSubmit.class);
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ObTimeDepositSessionCache cacheBean, VCGenericService vcService) throws Exception {
		ProductDetailsResponseData accountDetailsResponseData = null;
		ObTimeDepositSessionCache accountDetailsSessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_MAINTENANCE_DETAILS.getId());
		if(accountDetailsSessionCache == null || 
				accountDetailsSessionCache.getProductDetailsResponseData() == null ||
				!accountDetailsSessionCache.getProductDetailsResponseData().getAccount().getAcct_no().equals(requestBean.getAccountNo())){
			log.info("Account details cache not found / not matched");
			accountDetailsResponseData = retrieveAccountMaintenanceDetails(locale, requestBean, responseBean, vcService);
			if(responseBean.getObHeader().getStatusCode() != null){
				return;
			}
		}else {
			accountDetailsResponseData = accountDetailsSessionCache.getProductDetailsResponseData();
		}
		
		if(accountDetailsResponseData.getAccount() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Account details not found");
			return;
		}
		
		TimeDepositSubmitRequestData requestData = new TimeDepositSubmitRequestData();
		TimeDepositResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean, accountDetailsResponseData, vcService);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<TimeDepositResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TIME_DEPOSIT_MAINTENANCE_SUBMIT, 
				requestData, 
				TimeDepositResponseData.class, 
				true,
				null,
				requestBean.getIp());
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean, accountDetailsResponseData);
	}
	
	private void processRequest(String locale, TimeDepositSubmitRequestData requestData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ProductDetailsResponseData accountDetailsResponseData, VCGenericService vcService) throws Exception{
		ObTimeDepositSessionCache step1SessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1.getId());
		if(step1SessionCache.getParameterResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Step 1 cache is not found");
			return;
		}
		
		//Generate bank reference number
		TransactionBankRefRequestData requestBankRefData = new TransactionBankRefRequestData();
		requestBankRefData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestBankRefData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestBankRefData.setProd_cd(MiBConstant.PRODUCT_CODE_TDAM);
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
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setRollover_type_cd_new(requestBean.getRolloverTypeCode());
		requestData.setRollover_type_cd(accountDetailsResponseData.getAccount().getAuto_renewal());
		requestData.setAcct_no(accountDetailsResponseData.getAccount().getAcct_no());
		requestData.setAcct_ccy(accountDetailsResponseData.getAccount().getAcct_ccy());
		requestData.setTenor(accountDetailsResponseData.getAccount().getTerm());
		requestData.setTenor_type(accountDetailsResponseData.getAccount().getTerm_cd());
		requestData.setInterest_term(accountDetailsResponseData.getAccount().getInterest_term());
		requestData.setInterest_term_code(accountDetailsResponseData.getAccount().getInterest_term_code());
		
		requestData.setDevice_id(requestBean.getObDevice()==null?null:requestBean.getObDevice().getDeviceId());
		requestData.setDevice_os(requestBean.getObDevice()==null?null:requestBean.getObDevice().getOs());
		requestData.setDevice_type(requestBean.getObDevice()==null?null:requestBean.getObDevice().getModel());
	}
	
	private void processResponse(String locale, TimeDepositSubmitRequestData requestData, TimeDepositResponseData responseData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ProductDetailsResponseData accountDetailsResponseData) throws Exception{
		if(responseData.getTrx_data() != null) {
			ObTimeDepositSessionCache step1SessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1.getId());
			
			responseBean.setDebitAccountAliasName(accountDetailsResponseData.getAccount().getAcct_alias());
			responseBean.setDebitAccountNo(accountDetailsResponseData.getAccount().getAcct_no());
			responseBean.setDebitAccountType(accountDetailsResponseData.getAccount().getAcct_type_sibs());
			responseBean.setAmountCcy(accountDetailsResponseData.getAccount().getAcct_ccy());
			if(accountDetailsResponseData.getAccount().getPrincipal_amout() != null && 
					!accountDetailsResponseData.getAccount().getPrincipal_amout().isEmpty()) {
				responseBean.setAmount(new BigDecimal(accountDetailsResponseData.getAccount().getPrincipal_amout()));
			}
			if(accountDetailsResponseData.getAccount().getInterest_rate() != null && 
					!accountDetailsResponseData.getAccount().getInterest_rate().isEmpty()) {
				responseBean.setInterestRate(new BigDecimal(accountDetailsResponseData.getAccount().getInterest_rate()));
			}
			responseBean.setOriginalRolloverTypeCode(requestData.getRollover_type_cd());
			responseBean.setRolloverTypeCode(requestData.getRollover_type_cd_new());
			//Get the description from common parameter
			for(ListRolloverCode temp:step1SessionCache.getParameterResponseData().getRollover_type_list()) {
				if(temp.getRollover_code().equals(responseBean.getOriginalRolloverTypeCode())){
					if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
						responseBean.setOriginalRolloverTypeDesc(temp.getRollover_desc_en());
					}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
						responseBean.setOriginalRolloverTypeDesc(temp.getRollover_desc_cn());
					}else {
						responseBean.setOriginalRolloverTypeDesc(temp.getRollover_desc_id());
					}
				}
				if(temp.getRollover_code().equals(requestBean.getRolloverTypeCode())){
					if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
						responseBean.setRolloverTypeDesc(temp.getRollover_desc_en());
					}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
						responseBean.setRolloverTypeDesc(temp.getRollover_desc_cn());
					}
					else {
						responseBean.setRolloverTypeDesc(temp.getRollover_desc_id());
					}
				}
			}
			responseBean.setTenor(accountDetailsResponseData.getAccount().getTerm());
			responseBean.setTenorType(accountDetailsResponseData.getAccount().getTerm_cd());
			responseBean.setInterestTerm(accountDetailsResponseData.getAccount().getInterest_term());
			responseBean.setInterestTermCode(accountDetailsResponseData.getAccount().getInterest_term_code());
			responseBean.setBankRef(responseData.getTrx_data().getBank_ref());
			responseBean.setTrxStatusCode(responseData.getTrx_data().getTrx_status());
			responseBean.setTrxStatusDesc(getTrxStatusDesc(locale, responseData.getTrx_data().getTrx_status()));
		}
	}
	
	public String getTrxStatusDesc(String locale, String trxStatus) throws Exception{
		if(trxStatus == null){
			return null;
		}
		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE_IN);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE_CN);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}else{
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}
		return null;
	}
	
	public ProductDetailsResponseData retrieveAccountMaintenanceDetails(String locale, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, VCGenericService vcService) throws Exception {
		ProductDetailsRequestData requestData = new ProductDetailsRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
        requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAcct_no(requestBean.getAccountNo());
        requestData.setAcct_ccy(requestBean.getCurrencyCode());
        
        VCResponse<ProductDetailsResponseData> vcProductListResponseData = vcService.callOmniVC(
                VCMethodConstant.REST_METHODS.TIME_DEPOSIT_MAINTENANCE_ACCOUNT_DETAILS,
                requestData,
                ProductDetailsResponseData.class,
                true
        );
        if (processVCResponseError(vcProductListResponseData, responseBean)) {
            return null;
        }
        
        return vcProductListResponseData.getData();
	}
}
