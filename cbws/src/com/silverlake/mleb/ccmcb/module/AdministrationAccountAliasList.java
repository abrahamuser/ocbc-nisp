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

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ACCOUNT_ALIAS_LIST)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ACCOUNT_ALIAS_LIST
)

public class AdministrationAccountAliasList extends CacheSessionMiBServices<ObAccountAliasListRequest, ObAccountAliasListResponse, ObAccountAliasListSessionCache> {

	@WSParameter(isMandatory=false)
	protected String accountNumber;
	
	@WSParameter(isMandatory=false)
	protected String accountName;
	
	@WSParameter(isMandatory=false)
	protected String aliasName;
		
	@WSParameter(isMandatory=false)
	protected String pageNo;
	
	@WSParameter(isMandatory=false)
	protected String pageSize;

    public AdministrationAccountAliasList(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setAccountNumber(accountNumber);
        obRequest.setAccountName(accountName);
        obRequest.setAliasName(aliasName); 
        obRequest.setPageNo(pageNo);
        obRequest.setPageSize(pageSize);
    }

    @Override
    protected void processSessionResponse() {

    }
}
