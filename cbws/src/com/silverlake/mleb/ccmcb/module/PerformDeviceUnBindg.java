package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObDeviceUnbindgResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceUnbindgRequest;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DEVICE_UNBINDING)
public class PerformDeviceUnBindg extends SessionMiBServices<ObDeviceUnbindgRequest, ObDeviceUnbindgResponse>{
	
	@WSParameter(isMandatory = true)
	protected String device_id;
	
	@WSParameter(isMandatory = true)
	protected String cif;
	
	
	public PerformDeviceUnBindg(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception {

		obRequest.setDevice_id(device_id);
		obRequest.setCif(cif);
	
	}

	@Override
	public void processSessionResponse() {

	}
}
