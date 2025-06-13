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

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_TRANS_VAL_DATE)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionFundTransferValidateDate extends CacheSessionMiBServices<ObTransactionRequest, ObTransactionResponse, ObSessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=true)
	protected String valueDate;
	
	public TransactionFundTransferValidateDate(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setValueDate(valueDate);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
