package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObPaymentRequest;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_BILLER_LIST)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class PaymentRetrieveBillerList extends CacheSessionMiBServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	@WSParameter(isMandatory=false)
	protected String billerType;
	
	public PaymentRetrieveBillerList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setBillerType(billerType);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
