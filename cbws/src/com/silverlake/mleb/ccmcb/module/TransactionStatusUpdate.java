package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_UPDATE_STATUS)
@WSCache(
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_DATA_LIST,
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST},
		isPrevCacheMandatory={false, false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionStatusUpdate extends CacheSessionMiBServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse, ObSessionCache>{
	@WSParameter(isMandatory=true)
	protected String sourceTrx;
	
	@WSParameter(isMandatory=true)
	protected String pymtMasterId;
	
	@WSParameter(isMandatory=true)
	protected Integer index;
	
	@WSParameter(isMandatory=true)
	protected String trxStatus;
	
	@WSParameter(isMandatory=false)
	protected String validationCheck;
	
	public TransactionStatusUpdate(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setSourceTrx(sourceTrx);
		obRequest.setPymtMasterId(pymtMasterId);
		obRequest.setIndex(index);
		obRequest.setTrxStatus(trxStatus);
		obRequest.setValidationCheck(validationCheck);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
