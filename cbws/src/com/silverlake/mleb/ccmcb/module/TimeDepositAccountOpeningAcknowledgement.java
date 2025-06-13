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

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AO_ACK)
@WSCache(
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_CONFIRMATION,
				          VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_V2},
		isPrevCacheMandatory={true,false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={},
		doublePostingCacheCheck = VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_CONFIRMATION
)
public class TimeDepositAccountOpeningAcknowledgement extends CacheSessionMiBServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache>{
	@WSParameter(isMandatory=true)
	protected String transactionId;
	
	@WSParameter(isMandatory=false)
	protected String ip;
	
	public TimeDepositAccountOpeningAcknowledgement(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransactionId(transactionId);
		obRequest.setIp(ip);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
