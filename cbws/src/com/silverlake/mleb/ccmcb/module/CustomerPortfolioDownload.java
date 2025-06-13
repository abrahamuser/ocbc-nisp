package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListRequest;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerLoanDatav2;

import java.util.List;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CUSTOMER_PORTFOLIO_DOWNLOAD)

public class CustomerPortfolioDownload extends CacheSessionMiBServices<ObAccountListRequest, ObAccountListResponse, ObAccountListSessionCache> {

	@WSParameter(isMandatory=true)
	protected String customerNumber;
	
	@WSParameter(isMandatory=true)
	protected String productCode;
			
	@WSParameter(isMandatory=false)
	protected String periodFrom;
	
	@WSParameter(isMandatory=false)
	protected String periodTo;
	
	@WSParameter(isMandatory=false)
	protected List<CustomerLoanDatav2> customer_loan_data;
	
		

    public CustomerPortfolioDownload(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setCustomerNumber(customerNumber);
        obRequest.setProductCode(productCode);
        obRequest.setPeriodFrom(periodFrom);
        obRequest.setPeriodTo(periodTo);
        obRequest.setCustomer_loan_data(customer_loan_data);
        
    }

    @Override
    protected void processSessionResponse() {

    }
}
