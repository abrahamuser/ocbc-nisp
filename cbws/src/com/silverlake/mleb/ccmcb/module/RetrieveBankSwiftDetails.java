package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObBankListRequest;
import com.silverlake.mleb.mcb.bean.ObBankListResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BANK_SWIFT_DETAILS)
public class RetrieveBankSwiftDetails extends SessionMiBServices<ObBankListRequest, ObBankListResponse>{	
	@WSParameter(isMandatory=false)
	protected String networkClearingCode;
	
	public RetrieveBankSwiftDetails(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setNetworkClearingCode(networkClearingCode);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
