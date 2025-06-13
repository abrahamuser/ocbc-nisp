package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_SET_FAV)
public class TransactionManageBeneficiarySetFavorite extends SessionMiBServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse>{
	@WSParameter(isMandatory=true)
	protected String beneId;
	
	@WSParameter(isMandatory=true)
	protected Boolean isFavorite;
	
	public TransactionManageBeneficiarySetFavorite(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setBeneId(beneId);
		obRequest.setIsFavorite(isFavorite);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
