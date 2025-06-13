package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionCurrencyRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionCurrencyResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_CURRENCY_LIST)
public class RetrieveTransactionCurrencyList extends SessionMiBServices<ObTransactionCurrencyRequest, ObTransactionCurrencyResponse>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	public RetrieveTransactionCurrencyList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
