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

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_MANAGEMENT_DATA_MAINTENANCE)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_USER_MANAGEMENT_DATA_MAINTENANCE
	
)

public class AdministrationUserGeneralMaintenance extends CacheSessionMiBServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {

	@WSParameter(isMandatory=true)
	protected String actionCode;
	
	@WSParameter(isMandatory=true)
	protected String userId;
	
	@WSParameter(isMandatory=true)
	protected String userCode;
	
    public AdministrationUserGeneralMaintenance(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setActionCode(actionCode);
        obRequest.setUserId(userId);
        obRequest.setUserCode(userCode);
        
    }

    @Override
    protected void processSessionResponse() {

    }
}
