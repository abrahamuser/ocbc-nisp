package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_ACCT_PEND_LIST)
@WSCache(cacheId = VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_AUTH_ADMIN_ACCT_PEND_AUTH_LIST)
public class AuthorizationRetrievePendingAccountList extends CacheSessionMiBServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{

	@WSParameter(isMandatory=false)
	protected String accountNumber;
	
	@WSParameter(isMandatory=false)
	protected String accountName;

    public AuthorizationRetrievePendingAccountList(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setAccountNumber(accountNumber);
        obRequest.setAccountName(accountName);
    }

    @Override
    protected void processSessionResponse() {

    }
}
