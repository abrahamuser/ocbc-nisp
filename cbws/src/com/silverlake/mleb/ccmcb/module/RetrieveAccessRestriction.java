package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObAccessRestrictionRequest;
import com.silverlake.mleb.mcb.bean.ObAccessRestrictionResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ACCESS_RESTRICTION)
public class RetrieveAccessRestriction extends SessionMiBServices<ObAccessRestrictionRequest, ObAccessRestrictionResponse>{
	@WSParameter(isMandatory=true)
	protected String sourceTrx;
	
	@WSParameter(isMandatory=false)
	protected String productCode;
	
	@WSParameter(isMandatory=true)
	protected String trxStatus;
	
	public RetrieveAccessRestriction(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setSourceCode(sourceTrx);
		obRequest.setProductCode(productCode);
		obRequest.setStatusCode(trxStatus);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
