package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PROVINCE_LIST)
public class RetrieveProvinceList extends SessionMiBServices<ObRegistrationRequest, ObRegistrationResponse>{
	
	public RetrieveProvinceList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		
	}

	@Override
	public void processSessionResponse() {
		
	}
}
