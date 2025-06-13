package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingRequest;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DVBINDING_UPDATE)
public class PerformDeviceBindingUpdate extends SessionMiBServices<ObDeviceBindingRequest, ObDeviceBindingResponse>{
	
	@WSParameter(isMandatory = true)
	protected String device_id;
	
	@WSParameter(isMandatory = true)
	protected String cif;
	
	@WSParameter(isMandatory = true)
	protected String action;
	
	public PerformDeviceBindingUpdate(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception {

		obRequest.setDevId(device_id);
		obRequest.setCif(cif);
		obRequest.setLoginType(action);
	}

	@Override
	public void processSessionResponse() {

	}
}
