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

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_SUBMIT)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		previousCacheIds={
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND, 
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_PAYMENT_BILLER_INQUIRY},
		isPrevCacheMandatory={true, true},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={},
		doublePostingCacheCheck = VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND
)
public class PaymentSubmit extends CacheSessionMiBServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	@WSParameter(isMandatory=true)
	protected String billerType;
	
	@WSParameter(isMandatory=true)
	protected String debitAccountUUID;
	
	@WSParameter(isMandatory=false)
	protected String billerName;
	
	@WSParameter(isMandatory=false)
	protected String amountOptionId;
	
	@WSParameter(isMandatory=false)
	protected BigDecimal amount;
	
	@WSParameter(isMandatory=true)
	protected String valueDate;
	
	@WSParameter(isMandatory=false)
	protected String customerReferenceNo;
	
	@WSParameter(isMandatory=false)
	protected Boolean isSavePayee;
	
	@WSParameter(isMandatory=false)
	protected String payeeNickName;
	
	@WSParameter(isMandatory=false)
	protected Boolean isSendNotificationSender;
	
	@WSParameter(isMandatory=false)
	protected Boolean isNotifySenderCompleted;
	
	@WSParameter(isMandatory=false)
	protected Boolean isNotifySenderRejected;
	
	@WSParameter(isMandatory=false)
	protected Boolean isNotifySenderSuspected;
	
	@WSParameter(isMandatory=false)
	protected String notificationSenderEmail;
	
	@WSParameter(isMandatory=false)
	protected Boolean isRecurring;
	
	@WSParameter(isMandatory=false)
	protected String recurringType;
	
	@WSParameter(isMandatory=false)
	protected String recurringValue;
	
	@WSParameter(isMandatory=false)
	protected String recurringStartDate;
	
	@WSParameter(isMandatory=false)
	protected String recurringEndDate;
	
	@WSParameter(isMandatory=false)
	protected String exec_time_batch_cd;
	
	@WSParameter(isMandatory=false)
	protected String recurring_exec_time_batch_cd;
	
	@WSParameter(isMandatory=false)
	protected String ip;
	
	@WSParameter(isMandatory=true)
	protected String lang;
	
	public PaymentSubmit(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setBillerType(billerType);
		obRequest.setDebitAccountUUID(debitAccountUUID);
		obRequest.setBillerName(billerName);
		obRequest.setAmountOptionId(amountOptionId);
		obRequest.setAmount(amount);
		obRequest.setCustomerRef(customerReferenceNo);
		obRequest.setValueDate(valueDate);
		obRequest.setIsSavePayee(isSavePayee);
		obRequest.setPayeeNickName(payeeNickName);
		obRequest.setIsSendSenderNotification(isSendNotificationSender);
		obRequest.setIsSenderNotifyCompleted(isNotifySenderCompleted);
		obRequest.setIsSenderNotifyRejected(isNotifySenderRejected);
		obRequest.setIsSenderNotifySuspected(isNotifySenderSuspected);
		obRequest.setSenderNotificationEmail(notificationSenderEmail);
		obRequest.setIsRecurring(isRecurring);
		obRequest.setRecurringType(recurringType);
		obRequest.setRecurringValue(recurringValue);
		obRequest.setRecurringStartDate(recurringStartDate);
		obRequest.setRecurringEndDate(recurringEndDate);
		obRequest.setExec_time_batch_cd(exec_time_batch_cd);
		obRequest.setRecurring_exec_time_batch_cd(recurring_exec_time_batch_cd);
		obRequest.setIp(ip);
		obRequest.setLang(lang);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
