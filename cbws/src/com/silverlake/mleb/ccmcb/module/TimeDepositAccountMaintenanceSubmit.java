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

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AM_SUBMIT)
@WSCache(
		previousCacheIds={ 
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_MAINTENANCE_DETAILS,
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_STEP_1},
		isPrevCacheMandatory={false, true},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TimeDepositAccountMaintenanceSubmit extends CacheSessionMiBServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache>{
	@WSParameter(isMandatory=true)
	protected String accountNo;
	
	@WSParameter(isMandatory=true)
	protected String rolloverTypeCode;
	
	@WSParameter(isMandatory=true)
	protected String accountCcy;
	
	@WSParameter(isMandatory=false)
	protected String ip;
	
	public TimeDepositAccountMaintenanceSubmit(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setRolloverTypeCode(rolloverTypeCode);
		obRequest.setAccountNo(accountNo);
		obRequest.setCurrencyCode(accountCcy);
		obRequest.setIp(ip);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
