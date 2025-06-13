package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BENE_COUNTRY)

public class RetrieveBeneficiaryCountry extends SessionMiBServices<ObTransactionRequest, ObTransactionResponse> {
	
	@WSParameter(isMandatory=true)
	protected String purposeCode;
			
    public RetrieveBeneficiaryCountry(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setPurposeCode(purposeCode);
    }

    @Override
    protected void processSessionResponse() {

    }
}
