package com.silverlake.mleb.mcb.module.func.timedeposit;

import java.math.BigDecimal;

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
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListRolloverCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductDetailsRequestData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductDetailsResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;

@Service
public class TimeDepositAccountMaintenanceDetails extends CacheSessionFuncServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache> {
	private static Logger log = LogManager.getLogger(TimeDepositAccountMaintenanceDetails.class);

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ObTimeDepositSessionCache cacheBean, VCGenericService vcService) throws Exception {
        ProductDetailsRequestData requestData = new ProductDetailsRequestData();
        processRequest(requestData, requestBean, responseBean);
        VCResponse<ProductDetailsResponseData> vcProductListResponseData = vcService.callOmniVC(
                VCMethodConstant.REST_METHODS.TIME_DEPOSIT_MAINTENANCE_ACCOUNT_DETAILS,
                requestData,
                ProductDetailsResponseData.class,
                true
        );
        if (processVCResponseError(vcProductListResponseData, responseBean)) {
            return;
        }

        ProductDetailsResponseData responseData = vcProductListResponseData.getData();
        processResponse(locale, responseData, requestBean, responseBean);
        cacheBean.setProductDetailsResponseData(responseData);
    }

    private void processRequest(ProductDetailsRequestData requestData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean) {
    	ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(MiBConstant.PRODUCT_CODE_TDAM);
		if(sofResponseDataCache == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund sub cache is not found");
			return;
		}
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setAcct_no(acc.getAcct_no());
		        requestData.setAcct_ccy(acc.getAcct_ccy());
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		
        requestData.setOrg_cd(requestBean.getObUser().getOrgId());
        requestData.setUsr_cd(requestBean.getObUser().getLoginId());
    }

    private void processResponse(String locale, ProductDetailsResponseData responseData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean) {
    	if(responseData.getAccount() != null) {
    		ObTimeDepositSessionCache step1SessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1.getId());
    		if(step1SessionCache.getParameterResponseData() == null){
    			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
    			log.info("Step 1 cache is not found");
    			return;
    		}
    		
    		responseBean.setDebitAccountAliasName(responseData.getAccount().getAcct_alias());
    		responseBean.setDebitAccountName(responseData.getAccount().getAcct_name());
    		responseBean.setDebitAccountNo(responseData.getAccount().getAcct_no());
    		responseBean.setDebitAccountType(responseData.getAccount().getAcct_type_sibs());
    		responseBean.setAmountCcy(responseData.getAccount().getAcct_ccy());
    		if(responseData.getAccount().getBalance_available() != null && !responseData.getAccount().getBalance_available().isEmpty()) {
    			responseBean.setAmount(new BigDecimal(responseData.getAccount().getBalance_available()));
    		}
    		responseBean.setPrincipalAmount(new BigDecimal(responseData.getAccount().getPrincipal_amout()));
    		responseBean.setInterestRate(new BigDecimal(responseData.getAccount().getInterest_rate()));
    		responseBean.setInterestAmount(responseData.getAccount().getInterest_amount());
    		responseBean.setTenor(responseData.getAccount().getTerm());
    		responseBean.setTenorType(responseData.getAccount().getTerm_cd());
    		responseBean.setInterestTerm(responseData.getAccount().getInterest_term());
    		responseBean.setInterestTermCode(responseData.getAccount().getInterest_term_code());
    		responseBean.setRolloverTypeCode(responseData.getAccount().getAuto_renewal());
    		for(ListRolloverCode temp:step1SessionCache.getParameterResponseData().getRollover_type_list()) {
    			if(temp.getRollover_code().equals(responseData.getAccount().getAuto_renewal())){
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
    		responseBean.setMaturityDate(responseData.getAccount().getMaturity_date());
    		responseBean.setEffectiveDate(responseData.getAccount().getEffective_date());
    	}
    }

}
