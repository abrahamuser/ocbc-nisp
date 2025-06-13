package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListRequest;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CUSTOMER_PORTFOLIO_DATA_V2)

public class CustomerPortfolioDatav2 extends CacheSessionMiBServices<ObAccountListRequest, ObAccountListResponse, ObAccountListSessionCache> {

	@WSParameter(isMandatory=true)
	protected String cifNumber;
	
	@WSParameter(isMandatory=true)
	protected String productCode;
			
	@WSParameter(isMandatory=false)
	protected String agreementID;

    public CustomerPortfolioDatav2(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setCifNumber(cifNumber);
        obRequest.setProductCode(productCode);
        obRequest.setAgreementID(agreementID);
        
    }

    @Override
    protected void processSessionResponse() {

    }
}
