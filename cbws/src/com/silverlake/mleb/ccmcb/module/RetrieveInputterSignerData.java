package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationeUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_INPUTTER_SIGNER_DATA)


public class RetrieveInputterSignerData extends CacheSessionMiBServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache> {

	@WSParameter(isMandatory=true)
	protected String user_id;
	
	@WSParameter(isMandatory=true)
	protected String record_id;
	
    public RetrieveInputterSignerData(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setUser_id(user_id);
        obRequest.setRecord_id(record_id);
        
    }

    @Override
    protected void processSessionResponse() {

    }
}
