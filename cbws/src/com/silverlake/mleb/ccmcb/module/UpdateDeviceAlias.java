package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObUpdateDeviceAliasRequest;
import com.silverlake.mleb.mcb.bean.ObUpdateDeviceAliasResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DEVICE_ALIAS)
public class UpdateDeviceAlias extends SessionMiBServices<ObUpdateDeviceAliasRequest, ObUpdateDeviceAliasResponse>{
	
	@WSParameter(isMandatory = true)
	protected String device_id;
	
	@WSParameter(isMandatory = true)
	protected String device_alias;

	public UpdateDeviceAlias(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception {

		obRequest.setDevice_id(device_id);
		obRequest.setDevice_alias(device_alias);

	}

	@Override
	public void processSessionResponse() {

	}
}
