package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_REMITTER_COUNTRY)

public class RetrieveRemitterCountry extends SessionMiBServices<ObTransactionRequest, ObTransactionResponse> {
	
	@WSParameter(isMandatory=true)
	protected String purposeCode;
	
	@WSParameter(isMandatory=true)
	protected String beneCategoryCode;
	
	@WSParameter(isMandatory=true)
	protected String beneCountryCode;
			
    public RetrieveRemitterCountry(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setPurposeCode(purposeCode);
        obRequest.setBeneCategoryCode(beneCategoryCode);
        obRequest.setBeneCountryCode(beneCountryCode);
    }

    @Override
    protected void processSessionResponse() {

    }
}
