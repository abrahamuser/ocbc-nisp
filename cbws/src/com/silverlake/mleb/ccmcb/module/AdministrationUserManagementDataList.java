package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserRequest;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_MANAGEMENT_DATA_LIST)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_USER_MANAGEMENT_DATA_LIST
)

public class AdministrationUserManagementDataList extends CacheSessionMiBServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {

	@WSParameter(isMandatory=false)
	protected String userCode;
	
	@WSParameter(isMandatory=false)
	protected String userName;
		
	@WSParameter(isMandatory=false)
	protected String pageNo;
	
	@WSParameter(isMandatory=false)
	protected String pageSize;

    public AdministrationUserManagementDataList(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setUserCode(userCode);
        obRequest.setUserName(userName);        
        obRequest.setPageNo(pageNo);
        obRequest.setPageSize(pageSize);
    }

    @Override
    protected void processSessionResponse() {

    }
}
