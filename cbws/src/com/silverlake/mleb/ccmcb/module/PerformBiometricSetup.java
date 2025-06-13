package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_BIOMETRIC_SETUP)
public class PerformBiometricSetup extends SessionMiBServices<ObAuthenticationRequest, ObAuthenticationResponse>{
	@WSParameter(isMandatory=true)
	protected String action;
	
	@WSParameter(isMandatory=false)
	protected String type;
	
	@WSParameter(isMandatory=false)
	protected String orgCode;
	
	@WSParameter(isMandatory=false)
	protected String usrCode;
	
	public PerformBiometricSetup(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setLoginType(action);
		obRequest.setEnrollNonce(type);
		obRequest.setAccountNumber(orgCode);
		obRequest.setAccountType(usrCode);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
