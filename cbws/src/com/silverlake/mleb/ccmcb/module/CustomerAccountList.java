package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListRequest;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CUSTOMER_ACCOUNT_LIST)

public class CustomerAccountList extends CacheSessionMiBServices<ObAccountListRequest, ObAccountListResponse, ObAccountListSessionCache> {

	@WSParameter(isMandatory=false)
	protected String searchCustNo;
	
	@WSParameter(isMandatory=false)
	protected String searchCustName;
	
	@WSParameter(isMandatory=true)
	protected String productCode;
	
	@WSParameter(isMandatory=false)
	protected String pageSize;
	
	@WSParameter(isMandatory=false)
	protected String pageNo;

    public CustomerAccountList(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setSearchCustNo(searchCustNo);
        obRequest.setSearchCustName(searchCustName);
        obRequest.setProductCode(productCode);
        obRequest.setPageSize(pageSize);
        obRequest.setPageNo(pageNo);
    }

    @Override
    protected void processSessionResponse() {

    }
}
