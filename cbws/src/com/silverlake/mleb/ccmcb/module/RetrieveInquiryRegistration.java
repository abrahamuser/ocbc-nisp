package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationeUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_INQUIRY_REGISTRATION)

public class RetrieveInquiryRegistration extends CacheSessionMiBServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache> {

	@WSParameter(isMandatory=true)
	protected String role;
	
	@WSParameter(isMandatory=true)
	protected String email;
		
	@WSParameter(isMandatory=true)
	protected String mobileNo;
	
	@WSParameter(isMandatory=false)
	protected String ktpNo;
	
	@WSParameter(isMandatory=true)
	protected String registrationNo;
	
	@WSParameter(isMandatory=true)
	protected String accountName;
		
	@WSParameter(isMandatory=false)
	protected String verificationNo;
	
	@WSParameter(isMandatory=false)
	protected String revisionNo;

    public RetrieveInquiryRegistration(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setRole(role);
        obRequest.setEmail(email);
        obRequest.setMobileNo(mobileNo);
        obRequest.setKtpNo(ktpNo);
        obRequest.setRegistrationNo(registrationNo);
        obRequest.setAccountName(accountName);
        obRequest.setVerificationNo(verificationNo);
        obRequest.setRevisionNo(revisionNo);
    }

    @Override
    protected void processSessionResponse() {

    }
}
