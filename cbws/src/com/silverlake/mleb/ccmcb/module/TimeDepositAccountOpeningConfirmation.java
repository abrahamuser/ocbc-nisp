package com.silverlake.mleb.ccmcb.module;

import java.math.BigDecimal;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AO_CONF)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_CONFIRMATION,
		previousCacheIds={
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND, 
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1,
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_PROD_LIST},
		isPrevCacheMandatory={true, true, true},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TimeDepositAccountOpeningConfirmation extends CacheSessionMiBServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache>{
	@WSParameter(isMandatory=true)
	protected String debitAccountUUID;
	
	@WSParameter(isMandatory=true)
	protected String purposeCode;
	
	@WSParameter(isMandatory=true)
	protected String sourceOfFundCode;
	
	@WSParameter(isMandatory=true)
	protected String rolloverTypeCode;
	
	@WSParameter(isMandatory=true)
	protected BigDecimal amount;
	
	@WSParameter(isMandatory=true)
	protected String amountCcy;
	
	@WSParameter(isMandatory=true)
	protected Integer tenor;
	
	@WSParameter(isMandatory=true)
	protected String tenorType;
	
	@WSParameter(isMandatory=true)
	protected Integer interestTerm;
	
	@WSParameter(isMandatory=true)
	protected String interestTermCode;
	
	@WSParameter(isMandatory=false)
	protected String promoCode;
	
	public TimeDepositAccountOpeningConfirmation(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setDebitAccountUUID(debitAccountUUID);
		obRequest.setPurposeCode(purposeCode);
		obRequest.setSourceOfFundCode(sourceOfFundCode);
		obRequest.setRolloverTypeCode(rolloverTypeCode);
		obRequest.setAmount(amount);
		obRequest.setAmountCcy(amountCcy);
		obRequest.setTenor(tenor);
		obRequest.setTenorType(tenorType);
		obRequest.setInterestTerm(interestTerm);
		obRequest.setInterestTermCode(interestTermCode);
		obRequest.setPromoCode(promoCode);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
