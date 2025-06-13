package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObBankListRequest;
import com.silverlake.mleb.mcb.bean.ObBankListResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BANK_LIST)
public class RetrieveBankList extends SessionMiBServices<ObBankListRequest, ObBankListResponse>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=false)
	protected String bankCode;
	
	@WSParameter(isMandatory=false)
	protected String branchName;
	
	@WSParameter(isMandatory=false)
	protected String countryCode;
	
	@WSParameter(isMandatory=false)
	protected String networkClearingCode;
	
	public RetrieveBankList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setBankCode(bankCode);
		obRequest.setBranchName(branchName);
		obRequest.setCountryCode(countryCode);
		obRequest.setNetworkClearingCode(networkClearingCode);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
