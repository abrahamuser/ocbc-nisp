package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_TRANS_STEP1)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionFundTransferStep1 extends CacheSessionMiBServices<ObTransactionRequest, ObTransactionResponse, ObSessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	public TransactionFundTransferStep1(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
