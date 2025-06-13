package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_BENEFICIARIES)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARIES,
		previousCacheIds={},
		isPrevCacheMandatory={},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionBeneficiaries extends CacheSessionMiBServices<ObTransactionBeneficiaryRequest, ObTransactionBeneficiaryResponse, ObTransactionBeneficiarySessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=false)
	protected String accountNo;
	
	@WSParameter(isMandatory=false)
	protected String accountCcy;

	
	public TransactionBeneficiaries(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setAccountNo(accountNo);
		obRequest.setAccountCcy(accountCcy);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
