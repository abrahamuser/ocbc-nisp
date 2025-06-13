package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListRequest;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListResponse;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ACCOUNT_ALIAS_NAME_UPDATE)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ACCOUNT_ALIAS_LIST},
		isPrevCacheMandatory={true},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)

public class AdministrationAccountAliasMaintenance extends CacheSessionMiBServices<ObAccountAliasListRequest, ObAccountAliasListResponse, ObAccountAliasListSessionCache> {

	@WSParameter(isMandatory=true)
	protected String accountId;
	
	@WSParameter(isMandatory=true)
	protected String currencyCode;
	
	@WSParameter(isMandatory=true)
	protected String aliasName;
	
    public AdministrationAccountAliasMaintenance(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setAccountId(accountId);
        obRequest.setCurrencyCode(currencyCode);
        obRequest.setAliasName(aliasName);        
    }

    @Override
    protected void processSessionResponse() {

    }
}
