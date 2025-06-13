package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserRequest;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import java.util.List;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_MANAGEMENT_CREATE_MODIFY)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		previousCacheIds={
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_USER_ROLE_LIST,
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_USER_MANAGEMENT_DATA_LIST},
		isPrevCacheMandatory={false, false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)

public class AdministrationUserCreateModify extends CacheSessionMiBServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {

	@WSParameter(isMandatory=true)
	protected String actionCode;
	
	@WSParameter(isMandatory=false)
	protected String userId;
	
	@WSParameter(isMandatory=true)
	protected String userCode;
	
	@WSParameter(isMandatory=true)
	protected String userName;
	
	@WSParameter(isMandatory=false)
	protected String userProfileCode;
	
	@WSParameter(isMandatory=true)
	protected String email;
	
	@WSParameter(isMandatory=true)
	protected List<com.silverlake.mleb.mcb.bean.RoleList> roleList;
	
	@WSParameter(isMandatory=false)
	protected String authOwn;
	
    public AdministrationUserCreateModify(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setActionCode(actionCode);
        obRequest.setUserId(userId);
        obRequest.setUserCode(userCode);
        obRequest.setUserName(userName);
        obRequest.setUserProfileCode(userProfileCode);
        obRequest.setUserEmail(email);
        obRequest.setRoleList(roleList);
        obRequest.setAuthOwn(authOwn);
    }

    @Override
    protected void processSessionResponse() {

    }
}
