package com.silverlake.mleb.ccmcb.module;

import java.math.BigDecimal;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_TRANS_SUBMIT)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND, 
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARIES,
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY,
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_GET,
				VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ADMINISTRATION_BENEFICIARIES},
		isPrevCacheMandatory={true, false, false, false, false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={},
		doublePostingCacheCheck = VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND
)
public class TransactionFundTransferSubmit extends CacheSessionMiBServices<ObTransactionRequest, ObTransactionResponse, ObSessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=true)
	protected String debitAccountUUID;
	
	@WSParameter(isMandatory=false)
	protected String beneAccountUUID;
	
	@WSParameter(isMandatory=false)
	protected String beneAccountNo;
	
	@WSParameter(isMandatory=false)
	protected String beneAccountName;
	
	@WSParameter(isMandatory=false)
	protected String beneAccountCcy;

	@WSParameter(isMandatory=false)
	protected String beneAddress1;
	
	@WSParameter(isMandatory=false)
	protected String beneAddress2;
	
	@WSParameter(isMandatory=false)
	protected String beneAddress3;
	
	@WSParameter(isMandatory=false)
	protected String beneBankCode;
	
	@WSParameter(isMandatory=false)
	protected String beneBankBranch;
	
	@WSParameter(isMandatory=false)
	protected String residentStatus;
	
	@WSParameter(isMandatory=false)
	protected String beneCategory;
	
	@WSParameter(isMandatory=false)
	protected String senderName;
	
	@WSParameter(isMandatory=false)
	protected String remitterCategory;	
	
	@WSParameter(isMandatory=true)
	protected BigDecimal amount;
	
	@WSParameter(isMandatory=false)
	protected String amountCcy;
	
	@WSParameter(isMandatory=true)
	protected String valueDate;
	
	@WSParameter(isMandatory=false)
	protected String customerReferenceNo;
	
	@WSParameter(isMandatory=false)
	protected String remark;
	
	@WSParameter(isMandatory=false)
	protected Boolean isSaveBene;
	
	@WSParameter(isMandatory=false)
	protected Boolean isFavBene;
	
	@WSParameter(isMandatory=false)
	protected String beneAliasName;
	
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
	protected Boolean isSendNotificationBene;
	
	@WSParameter(isMandatory=false)
	protected String notificationBeneEmail;
	
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
	protected String fxType;
	
	@WSParameter(isMandatory=false)
	protected String fxDealerName;
	
	@WSParameter(isMandatory=false)
	protected BigDecimal fxDealerRate;
	
	@WSParameter(isMandatory=false)
	protected String fxContractNo;
	
	@WSParameter(isMandatory=false)
	protected BigDecimal fxRate;
	
	@WSParameter(isMandatory=false)
	protected String beneBankName;
	
	@WSParameter(isMandatory=false)
	protected String beneBankCountryCode;

	@WSParameter(isMandatory=false)
	protected String beneBankAddress1;
	
	@WSParameter(isMandatory=false)
	protected String beneBankAddress2;
	
	@WSParameter(isMandatory=false)
	protected String beneBankAddress3;
	
	@WSParameter(isMandatory=false)
	protected String senderCountryCode;
	
	@WSParameter(isMandatory=false)
	protected String beneCountryCode;
	
	@WSParameter(isMandatory=false)
	protected String beneRelationshipStatus;
	
	@WSParameter(isMandatory=false)
	protected String paymentPurpose;
	
	@WSParameter(isMandatory=false)
	protected String chargesMethod;
	
	@WSParameter(isMandatory=false)
	protected String beneID;
	
	@WSParameter(isMandatory=false)
	protected String beneBankNetworkClearingCode;
	
	@WSParameter(isMandatory=false)
	protected String beneBankCity;
	
	@WSParameter(isMandatory=false)
	protected String exec_time_batch_cd;
	
	@WSParameter(isMandatory=false)
	protected String recurring_exec_time_batch_cd;
	
	@WSParameter(isMandatory=false)
	protected String additional_info;
	
	@WSParameter(isMandatory=false)
	protected String bene_bank_id;
	
	@WSParameter(isMandatory=false)
	protected String trx_purpose;
	
	@WSParameter(isMandatory=false)
	protected String charges_acct_no;
	
	@WSParameter(isMandatory=false)
	protected String charges_acct_ccy;
	
	@WSParameter(isMandatory=false)
	protected String sender_address1;
	
	@WSParameter(isMandatory=false)
	protected String sender_address2;
	
	@WSParameter(isMandatory=false)
	protected String sender_address3;
	
	@WSParameter(isMandatory=false)
	protected String ip;
	
	public TransactionFundTransferSubmit(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setDebitAccountUUID(debitAccountUUID);
		obRequest.setBeneAccountUUID(beneAccountUUID);
		obRequest.setAmount(amount);
		obRequest.setAmountCcy(amountCcy);
		obRequest.setCustomerReferenceNo(customerReferenceNo);
		obRequest.setValueDate(valueDate);
		obRequest.setBeneAccountNo(beneAccountNo);
		obRequest.setBeneAccountName(beneAccountName);
		obRequest.setBeneAccountCcy(beneAccountCcy);
		obRequest.setBeneAddress1(beneAddress1);
		obRequest.setBeneAddress2(beneAddress2);
		obRequest.setBeneAddress3(beneAddress3);
		obRequest.setBeneBankCode(beneBankCode);
		obRequest.setBeneBankBranch(beneBankBranch);
		obRequest.setResidentStatus(residentStatus);
		obRequest.setBeneCategory(beneCategory);
		obRequest.setSenderName(senderName);
		obRequest.setRemitterCategory(remitterCategory);
		obRequest.setBeneAliasName(beneAliasName);
		obRequest.setRemark(remark);
		obRequest.setIsSaveBene(isSaveBene);
		obRequest.setIsFavBene(isFavBene);
		obRequest.setIsSendSenderNotification(isSendNotificationSender);
		obRequest.setIsSenderNotifyCompleted(isNotifySenderCompleted);
		obRequest.setIsSenderNotifyRejected(isNotifySenderRejected);
		obRequest.setIsSenderNotifySuspected(isNotifySenderSuspected);
		obRequest.setIsSendBeneNotificaation(isSendNotificationBene);
		obRequest.setBeneNotificationEmail(notificationBeneEmail);
		obRequest.setSenderNotificationEmail(notificationSenderEmail);
		obRequest.setIsRecurring(isRecurring);
		obRequest.setRecurringType(recurringType);
		obRequest.setRecurringValue(recurringValue);
		obRequest.setRecurringStartDate(recurringStartDate);
		obRequest.setRecurringEndDate(recurringEndDate);
		obRequest.setFxType(fxType);
		obRequest.setFxDealerName(fxDealerName);
		obRequest.setFxDealerRate(fxDealerRate);
		obRequest.setFxContractNo(fxContractNo);
		obRequest.setFxRate(fxRate);

		obRequest.setBeneBankName(beneBankName);
		obRequest.setBeneBankCountryCode(beneBankCountryCode);
		obRequest.setBeneBankAddress1(beneBankAddress1);
		obRequest.setBeneBankAddress2(beneBankAddress2);
		obRequest.setBeneBankAddress3(beneBankAddress3);
		obRequest.setSenderCountryCode(senderCountryCode);
		obRequest.setBeneCountryCode(beneCountryCode);
		obRequest.setBeneRelationshipStatus(beneRelationshipStatus);
		obRequest.setPaymentPurpose(paymentPurpose);
		obRequest.setChargesMethod(chargesMethod);
		
		obRequest.setBeneID(beneID);
		obRequest.setBeneBankNetworkClearingCode(beneBankNetworkClearingCode);
		obRequest.setBeneBankCity(beneBankCity);
		
		obRequest.setExec_time_batch_cd(exec_time_batch_cd);
		obRequest.setRecurring_exec_time_batch_cd(recurring_exec_time_batch_cd);
		obRequest.setAdditional_info(additional_info);
		
		obRequest.setBene_bank_id(bene_bank_id);
		obRequest.setTrx_purpose(trx_purpose);
		obRequest.setCharges_acct_ccy(charges_acct_ccy);
		obRequest.setCharges_acct_no(charges_acct_no);
		
		obRequest.setSender_address1(sender_address1);
		obRequest.setSender_address2(sender_address2);
		obRequest.setSender_address3(sender_address3);
		
		obRequest.setIp(ip);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
