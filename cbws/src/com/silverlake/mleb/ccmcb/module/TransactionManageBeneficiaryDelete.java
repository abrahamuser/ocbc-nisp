package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_DELETE)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_MANAGEMENT_BENEFICIARY_DETAILS},
		isPrevCacheMandatory={true},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionManageBeneficiaryDelete extends CacheSessionMiBServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse, ObTransactionManageBeneficiarySessionCache>{
	@WSParameter(isMandatory=true)
	protected String beneId;
	
	public TransactionManageBeneficiaryDelete(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setBeneId(beneId);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
