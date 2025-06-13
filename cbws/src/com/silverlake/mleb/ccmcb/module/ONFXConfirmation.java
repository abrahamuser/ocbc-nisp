package com.silverlake.mleb.ccmcb.module;

import java.math.BigDecimal;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObForexRequest;
import com.silverlake.mleb.mcb.bean.ObForexResponse;
import com.silverlake.mleb.mcb.bean.ObForexSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ONFX_CONFIRMATION)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ONFX_CONFIRMATION,
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND},
		isPrevCacheMandatory={true},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class ONFXConfirmation extends CacheSessionMiBServices<ObForexRequest, ObForexResponse, ObForexSessionCache>{
	@WSParameter(isMandatory=true)
	protected String debitAccountUUID;
	
	@WSParameter(isMandatory=false)
	protected String beneAccountUUID;
	
	@WSParameter(isMandatory=false)
	protected String beneAccountNo;

	@WSParameter(isMandatory=false)
	protected String beneAccountName;
	
	@WSParameter(isMandatory=true)
	protected String currency;
	
	@WSParameter(isMandatory=true)
	protected BigDecimal amount;
	
	@WSParameter(isMandatory=true)
	protected String amountCcy;
	
	@WSParameter(isMandatory=false)
	protected String remark;
	
	@WSParameter(isMandatory=true)
	protected String purpose;
	
	@WSParameter(isMandatory=false)
	protected String currency2;
	
	@WSParameter(isMandatory=false)
	protected Boolean isPreValidate;
	
	public ONFXConfirmation(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setDebitAccountUUID(debitAccountUUID);
		obRequest.setBeneAccountUUID(beneAccountUUID);
		obRequest.setBeneAccountName(beneAccountName);
		obRequest.setBeneAccountNo(beneAccountNo);
		obRequest.setCurrency(currency);
		obRequest.setAmount(amount);
		obRequest.setAmountCcy(amountCcy);
		obRequest.setRemark(remark);
		obRequest.setPurpose(purpose);
		obRequest.setCurrency2(currency2);
		obRequest.setIsPreValidate(isPreValidate);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
