package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.xml.ws.WebServiceContext;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AO_STEP_1)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1
)
public class TimeDepositAccountOpeningStep1 extends CacheSessionMiBServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache> {

    @WSParameter(isMandatory=false)
    protected Boolean isCheckCutOff;
    
    @WSParameter(isMandatory=false)
    protected String productCode;

    public TimeDepositAccountOpeningStep1(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setIsCheckCutOff(isCheckCutOff);
        obRequest.setProductCode(productCode);
    }

    @Override
    protected void processSessionResponse() {

    }
}
