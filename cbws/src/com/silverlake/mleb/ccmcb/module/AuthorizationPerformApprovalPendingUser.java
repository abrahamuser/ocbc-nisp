package com.silverlake.mleb.ccmcb.module;

import java.util.List;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_USR_AUTH)
@WSCache(
		previousCacheIds = {VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_AUTH_ADMIN_USER_PEND_AUTH_LIST},
		isPrevCacheMandatory = {true},
		clearPreviousCacheIds = {VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_AUTH_ADMIN_USER_PEND_AUTH_LIST},
		isSuccessClearPreviousCache = {true}
)
public class AuthorizationPerformApprovalPendingUser extends CacheSessionMiBServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{

	@WSParameter(isMandatory=true)
	protected List<String> recordIds;
	
	@WSParameter(isMandatory=true)
	protected String action;

    public AuthorizationPerformApprovalPendingUser(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setRecordIds(recordIds);
        obRequest.setAction_cd(action);
    }

    @Override
    protected void processSessionResponse() {

    }
}
