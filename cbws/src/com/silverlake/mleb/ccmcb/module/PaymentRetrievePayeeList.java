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

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_PAYEE_LIST)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_PAYMENT_PAYEE_LIST,
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class PaymentRetrievePayeeList extends CacheSessionMiBServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	@WSParameter(isMandatory=false)
	protected String groupName;
	
	@WSParameter(isMandatory=false)
	protected String billerCode;
	
	public PaymentRetrievePayeeList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setGroupName(groupName);
		obRequest.setBillerCode(billerCode);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
