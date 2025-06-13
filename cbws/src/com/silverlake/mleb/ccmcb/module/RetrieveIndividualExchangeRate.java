package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateRequest;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_EXCHANGE_RATE_INDIVIDUAL)
public class RetrieveIndividualExchangeRate extends SessionMiBServices<ObRetrieveExchangeRateRequest, ObRetrieveExchangeRateResponse>{
	@WSParameter(isMandatory=true)
	protected String sellCcy;
	
	@WSParameter(isMandatory=true)
	protected String buyCcy;
	
	public RetrieveIndividualExchangeRate(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setSellCcy(sellCcy);
		obRequest.setBuyCcy(buyCcy);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
