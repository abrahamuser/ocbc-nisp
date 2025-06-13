package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_SOURCE_OF_FUND)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND,
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND},
		isPrevCacheMandatory={false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionSourceOfFund extends CacheSessionMiBServices<ObTransactionSourceOfFundRequest, ObTransactionSourceOfFundResponse, ObTransactionSourceOfFundSessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=true)
	protected Boolean isSourceOfFund;
	
	@WSParameter(isMandatory=false)
	protected String currency;
	
	@WSParameter(isMandatory=false)
	protected String accountNo;
	
	public TransactionSourceOfFund(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setCurrencyCode(currency);
		obRequest.setIsSourceOfFund(isSourceOfFund);
		obRequest.setAccountNo(accountNo);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
