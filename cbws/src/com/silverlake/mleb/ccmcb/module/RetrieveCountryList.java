package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.mcb.bean.ObCountryListRequest;
import com.silverlake.mleb.mcb.bean.ObCountryListResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_COUNTRY_LIST)
public class RetrieveCountryList extends SessionMiBServices<ObCountryListRequest, ObCountryListResponse>{
	
	public RetrieveCountryList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		
	}

	@Override
	public void processSessionResponse() {
		
	}
}
