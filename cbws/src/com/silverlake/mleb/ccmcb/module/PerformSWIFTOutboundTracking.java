package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObSwiftOutboundTrackingRequest;
import com.silverlake.mleb.mcb.bean.ObSwiftOutboundTrackingResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_SWIFT_OUTBTRKNG)
public class PerformSWIFTOutboundTracking extends SessionMiBServices<ObSwiftOutboundTrackingRequest, ObSwiftOutboundTrackingResponse>{
	
	@WSParameter(isMandatory = true)
	protected String remittance_no;

	public PerformSWIFTOutboundTracking(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception {

		obRequest.setRemittance_no(remittance_no);

	}

	@Override
	public void processSessionResponse() {

	}
}
