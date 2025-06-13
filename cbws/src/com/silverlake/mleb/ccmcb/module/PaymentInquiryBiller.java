package com.silverlake.mleb.ccmcb.module;

import java.math.BigDecimal;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObPaymentRequest;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_BILLER_INQUIRY)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_PAYMENT_BILLER_INQUIRY,
		previousCacheIds={
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND, 
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_PAYMENT_PAYEE_LIST},
		isPrevCacheMandatory={true, false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class PaymentInquiryBiller extends CacheSessionMiBServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	@WSParameter(isMandatory=true)
	protected Integer flowType;
	
	@WSParameter(isMandatory=true)
	protected String billerType;
	
	@WSParameter(isMandatory=true)
	protected String debitAccountUUID;
	
	@WSParameter(isMandatory=false)
	protected String billerId;
	
	@WSParameter(isMandatory=false)
	protected String billerCode;
	
	@WSParameter(isMandatory=false)
	protected String billingId;
	
	@WSParameter(isMandatory=false)
	protected BigDecimal amount;
	
	@WSParameter(isMandatory=false)
	protected String valueDate;
	
	@WSParameter(isMandatory=false)
	protected String customerRef;
	
	@WSParameter(isMandatory=false)
	protected String ppType;
	
	
	public PaymentInquiryBiller(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setFlowType(flowType);
		obRequest.setBillerType(billerType);
		obRequest.setDebitAccountUUID(debitAccountUUID);
		obRequest.setBillerId(billerId);
		obRequest.setBillerCode(billerCode);
		obRequest.setBillingId(billingId);
		obRequest.setAmount(amount);
		obRequest.setValueDate(valueDate);
		obRequest.setCustomerRef(customerRef);
		obRequest.setPpType(ppType);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
