package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObBlockMyUserRequest;
import com.silverlake.mleb.mcb.bean.ObBlockMyUserResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BLOCK_USER)
public class PerformBlockMyUser extends SessionMiBServices<ObBlockMyUserRequest, ObBlockMyUserResponse>{
	
	@WSParameter(isMandatory = true)
	protected String org_cd;
	
	@WSParameter(isMandatory = true)
	protected String usr_cd;
			
	@WSParameter(isMandatory = true)
	protected String email;
	
	@WSParameter(isMandatory = false)
	protected String phone;

	public PerformBlockMyUser(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception {
		
		obRequest.setOrg_cd(org_cd);
		obRequest.setUsr_cd(usr_cd);
		obRequest.setEmail(email);
		obRequest.setPhone(phone);

	}

	@Override
	public void processSessionResponse() {

	}
}
