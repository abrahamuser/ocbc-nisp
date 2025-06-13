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

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CHARGE_ACCOUNT_LIST)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_CHARGE_ACCOUNT_LIST
)
public class RetrieveChargeAccountList extends CacheSessionMiBServices<ObTransactionSourceOfFundRequest, ObTransactionSourceOfFundResponse, ObTransactionSourceOfFundSessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=false)
	protected String currency;
	
	@WSParameter(isMandatory=true)
	protected String accountNo;
	
	public RetrieveChargeAccountList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setCurrencyCode(currency);
		obRequest.setAccountNo(accountNo);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
