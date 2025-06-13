package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionLimitEqUSDRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionLimitEqUSDResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_LIMIT_PURCHASE)

public class RetrieveTransactionLimitPurchase extends SessionMiBServices<ObTransactionLimitEqUSDRequest, ObTransactionLimitEqUSDResponse> {

	@WSParameter(isMandatory=true)
	protected String fromCcy;
	
	@WSParameter(isMandatory=true)
	protected String toCcy;
			
	@WSParameter(isMandatory=false)
	protected String fromAmount;
	
	@WSParameter(isMandatory=false)
	protected String toAmount;
	
	@WSParameter(isMandatory=true)
	protected String accountNo;

	@WSParameter(isMandatory=false)
	protected String cancelBit;
	
    public RetrieveTransactionLimitPurchase(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setFromCcy(fromCcy);
        obRequest.setToCcy(toCcy);
        obRequest.setFromAmount(fromAmount);
        obRequest.setToAmount(toAmount);
        obRequest.setAccountNo(accountNo);
        obRequest.setCancelBit(cancelBit);
    }

    @Override
    protected void processSessionResponse() {

    }
}
