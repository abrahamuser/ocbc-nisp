package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ESTATEMENT_DOWNLOAD)

public class EstatementDownload extends SessionMiBServices<ObAccountOverviewRequest, ObAccountOverviewResponse> {

	@WSParameter(isMandatory=true)
	protected String acct_no;
	
	@WSParameter(isMandatory=true)
	protected String acct_ccy;
			
	@WSParameter(isMandatory=true)
	protected String periode;
	
			

    public EstatementDownload(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setAcct_no(acct_no);
        obRequest.setAcct_ccy(acct_ccy);
        obRequest.setPeriode(periode);
        
        
    }

    @Override
    protected void processSessionResponse() {

    }
}
