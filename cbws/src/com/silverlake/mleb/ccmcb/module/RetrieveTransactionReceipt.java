package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_RECEIPT)
public class RetrieveTransactionReceipt extends SessionMiBServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse>{
	@WSParameter(isMandatory=true)
	protected String pymtMasterId;
	
	@WSParameter(isMandatory=false)
	protected Boolean isRetrieveDetails;
	
	@WSParameter(isMandatory=false)
	protected String is_additional_info;
	
	public RetrieveTransactionReceipt(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setPymtMasterId(pymtMasterId);
		obRequest.setIsRetrieveDetails(isRetrieveDetails);
		obRequest.setIs_additional_info(is_additional_info);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
