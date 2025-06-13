package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_AUDIT_TRAIL)
public class RetrieveTransactionAuditTrail extends SessionMiBServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse>{
	@WSParameter(isMandatory=true)
	protected String pymtMasterId;
	
	public RetrieveTransactionAuditTrail(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setPymtMasterId(pymtMasterId);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
