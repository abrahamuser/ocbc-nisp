package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AM_DETAILS)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_MAINTENANCE_DETAILS,
		previousCacheIds={
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND, 
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1},
		isPrevCacheMandatory={true, true},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TimeDepositAccountMaintenanceDetails extends CacheSessionMiBServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache> {

    @WSParameter(isMandatory=true)
    protected String debitAccountUUID;

    public TimeDepositAccountMaintenanceDetails(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
    	obRequest.setDebitAccountUUID(debitAccountUUID);
    }

    @Override
    protected void processSessionResponse() {

    }
}
