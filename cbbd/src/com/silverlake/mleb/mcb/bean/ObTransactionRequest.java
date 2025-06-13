package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ObTransactionRequest extends ObRequestCache<ObSessionCache> implements Serializable
{
	private static final long serialVersionUID = 7709279929407878829L;

	private String transferServiceType;
	private List<String> transferServiceTypes;//To support multiple transfer service type
	private String debitAccountUUID;
	private String beneAccountUUID;
	private String beneAccountNo;
	private String beneAccountName;
	private String beneAccountCcy;
	private String beneAddress1;
	private String beneAddress2;
	private String beneAddress3;
	private String beneBankCode;
	private String beneBankBranch;
	private String residentStatus;
	private String beneCategory;
	private String customerReferenceNo;
	private BigDecimal amount;
	private String amountCcy;
	private String remark;
	private Boolean isSaveBene;
	private Boolean isFavBene;
	private String beneAliasName;
	private String senderName;
	private String remitterCategory;
	
	private String fxType;
	private BigDecimal fxDealerRate;
	private String fxDealerName;
	private String fxContractNo;
	private BigDecimal fxRate;
	
	private Boolean isSendSenderNotification;
	private String senderNotificationEmail;
	private Boolean isSenderNotifyCompleted;
	private Boolean isSenderNotifyRejected;
	private Boolean isSenderNotifySuspected;
	private Boolean isSendBeneNotificaation;
	private String beneNotificationEmail;
	private String valueDate;
	private Boolean isRecurring;
	private String recurringType;
	private String recurringValue;
	private String recurringStartDate;
	private String recurringEndDate;
	
	private String beneBankName;
	private String beneBankCountryCode;
	private String beneBankAddress1;
	private String beneBankAddress2;
	private String beneBankAddress3;
	private String senderCountryCode;
	private String beneCountryCode;
	private String beneRelationshipStatus;
	private String paymentPurpose;
	private String chargesMethod;
	
	//
	private String beneID;
	private String beneBankNetworkClearingCode;
	private String beneBankCity;
	
	private String exec_time_batch_cd;
	private String recurring_exec_time_batch_cd;
	
	private String additional_info;
	
	private String proxy_data;
	private String proxy_type;
	private String bene_bank_id;
	private String trx_purpose;
	private String charges_acct_no;
	private String charges_acct_ccy;
	private String sender_address1;
	private String sender_address2;
	private String sender_address3;
	
	private String purposeCode;
	private String beneCategoryCode;
	private String remitterCountryCode;
	private String remitterCategoryCode;
	private String ip;
	
	public String getTransferServiceType() {
		return transferServiceType;
	}
	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
	}
	public String getDebitAccountUUID() {
		return debitAccountUUID;
	}
	public void setDebitAccountUUID(String debitAccountUUID) {
		this.debitAccountUUID = debitAccountUUID;
	}
	public String getBeneAccountUUID() {
		return beneAccountUUID;
	}
	public void setBeneAccountUUID(String beneAccountUUID) {
		this.beneAccountUUID = beneAccountUUID;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneAccountName() {
		return beneAccountName;
	}
	public void setBeneAccountName(String beneAccountName) {
		this.beneAccountName = beneAccountName;
	}
	public String getCustomerReferenceNo() {
		return customerReferenceNo;
	}
	public void setCustomerReferenceNo(String customerReferenceNo) {
		this.customerReferenceNo = customerReferenceNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIsSaveBene() {
		return isSaveBene;
	}
	public void setIsSaveBene(Boolean isSaveBene) {
		this.isSaveBene = isSaveBene;
	}
	public String getBeneAliasName() {
		return beneAliasName;
	}
	public void setBeneAliasName(String beneAliasName) {
		this.beneAliasName = beneAliasName;
	}
	public Boolean getIsSendSenderNotification() {
		return isSendSenderNotification;
	}
	public void setIsSendSenderNotification(Boolean isSendSenderNotification) {
		this.isSendSenderNotification = isSendSenderNotification;
	}
	public String getSenderNotificationEmail() {
		return senderNotificationEmail;
	}
	public void setSenderNotificationEmail(String senderNotificationEmail) {
		this.senderNotificationEmail = senderNotificationEmail;
	}
	public Boolean getIsSendBeneNotificaation() {
		return isSendBeneNotificaation;
	}
	public void setIsSendBeneNotificaation(Boolean isSendBeneNotificaation) {
		this.isSendBeneNotificaation = isSendBeneNotificaation;
	}
	public String getBeneNotificationEmail() {
		return beneNotificationEmail;
	}
	public void setBeneNotificationEmail(String beneNotificationEmail) {
		this.beneNotificationEmail = beneNotificationEmail;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public Boolean getIsRecurring() {
		return isRecurring;
	}
	public void setIsRecurring(Boolean isRecurring) {
		this.isRecurring = isRecurring;
	}
	public String getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
	}
	public String getRecurringValue() {
		return recurringValue;
	}
	public void setRecurringValue(String recurringValue) {
		this.recurringValue = recurringValue;
	}
	public String getRecurringStartDate() {
		return recurringStartDate;
	}
	public void setRecurringStartDate(String recurringStartDate) {
		this.recurringStartDate = recurringStartDate;
	}
	public String getRecurringEndDate() {
		return recurringEndDate;
	}
	public void setRecurringEndDate(String recurringEndDate) {
		this.recurringEndDate = recurringEndDate;
	}
	public String getBeneAccountCcy() {
		return beneAccountCcy;
	}
	public void setBeneAccountCcy(String beneAccountCcy) {
		this.beneAccountCcy = beneAccountCcy;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public String getFxType() {
		return fxType;
	}
	public void setFxType(String fxType) {
		this.fxType = fxType;
	}
	public BigDecimal getFxDealerRate() {
		return fxDealerRate;
	}
	public void setFxDealerRate(BigDecimal fxDealerRate) {
		this.fxDealerRate = fxDealerRate;
	}
	public String getFxDealerName() {
		return fxDealerName;
	}
	public void setFxDealerName(String fxDealerName) {
		this.fxDealerName = fxDealerName;
	}
	public String getFxContractNo() {
		return fxContractNo;
	}
	public void setFxContractNo(String fxContractNo) {
		this.fxContractNo = fxContractNo;
	}
	public Boolean getIsSenderNotifyCompleted() {
		return isSenderNotifyCompleted;
	}
	public void setIsSenderNotifyCompleted(Boolean isSenderNotifyCompleted) {
		this.isSenderNotifyCompleted = isSenderNotifyCompleted;
	}
	public Boolean getIsSenderNotifyRejected() {
		return isSenderNotifyRejected;
	}
	public void setIsSenderNotifyRejected(Boolean isSenderNotifyRejected) {
		this.isSenderNotifyRejected = isSenderNotifyRejected;
	}
	public Boolean getIsSenderNotifySuspected() {
		return isSenderNotifySuspected;
	}
	public void setIsSenderNotifySuspected(Boolean isSenderNotifySuspected) {
		this.isSenderNotifySuspected = isSenderNotifySuspected;
	}
	public BigDecimal getFxRate() {
		return fxRate;
	}
	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getBeneAddress1() {
		return beneAddress1;
	}
	public void setBeneAddress1(String beneAddress1) {
		this.beneAddress1 = beneAddress1;
	}
	public String getBeneBankCode() {
		return beneBankCode;
	}
	public void setBeneBankCode(String beneBankCode) {
		this.beneBankCode = beneBankCode;
	}
	public String getBeneBankBranch() {
		return beneBankBranch;
	}
	public void setBeneBankBranch(String beneBankBranch) {
		this.beneBankBranch = beneBankBranch;
	}
	public String getBeneCategory() {
		return beneCategory;
	}
	public void setBeneCategory(String beneCategory) {
		this.beneCategory = beneCategory;
	}
	public String getRemitterCategory() {
		return remitterCategory;
	}
	public void setRemitterCategory(String remitterCategory) {
		this.remitterCategory = remitterCategory;
	}
	public String getResidentStatus() {
		return residentStatus;
	}
	public void setResidentStatus(String residentStatus) {
		this.residentStatus = residentStatus;
	}
	public String getBeneAddress2() {
		return beneAddress2;
	}
	public void setBeneAddress2(String beneAddress2) {
		this.beneAddress2 = beneAddress2;
	}
	public String getBeneAddress3() {
		return beneAddress3;
	}
	public void setBeneAddress3(String beneAddress3) {
		this.beneAddress3 = beneAddress3;
	}
	public String getBeneBankName() {
		return beneBankName;
	}
	public void setBeneBankName(String beneBankName) {
		this.beneBankName = beneBankName;
	}
	public String getBeneBankCountryCode() {
		return beneBankCountryCode;
	}
	public void setBeneBankCountryCode(String beneBankCountryCode) {
		this.beneBankCountryCode = beneBankCountryCode;
	}
	public String getBeneBankAddress1() {
		return beneBankAddress1;
	}
	public void setBeneBankAddress1(String beneBankAddress1) {
		this.beneBankAddress1 = beneBankAddress1;
	}
	public String getBeneBankAddress2() {
		return beneBankAddress2;
	}
	public void setBeneBankAddress2(String beneBankAddress2) {
		this.beneBankAddress2 = beneBankAddress2;
	}
	public String getBeneBankAddress3() {
		return beneBankAddress3;
	}
	public void setBeneBankAddress3(String beneBankAddress3) {
		this.beneBankAddress3 = beneBankAddress3;
	}
	public String getSenderCountryCode() {
		return senderCountryCode;
	}
	public void setSenderCountryCode(String senderCountryCode) {
		this.senderCountryCode = senderCountryCode;
	}
	public String getBeneCountryCode() {
		return beneCountryCode;
	}
	public void setBeneCountryCode(String beneCountryCode) {
		this.beneCountryCode = beneCountryCode;
	}
	public String getBeneRelationshipStatus() {
		return beneRelationshipStatus;
	}
	public void setBeneRelationshipStatus(String beneRelationshipStatus) {
		this.beneRelationshipStatus = beneRelationshipStatus;
	}
	public String getPaymentPurpose() {
		return paymentPurpose;
	}
	public void setPaymentPurpose(String paymentPurpose) {
		this.paymentPurpose = paymentPurpose;
	}
	public String getChargesMethod() {
		return chargesMethod;
	}
	public void setChargesMethod(String chargesMethod) {
		this.chargesMethod = chargesMethod;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Boolean getIsFavBene() {
		return isFavBene;
	}
	public void setIsFavBene(Boolean isFavBene) {
		this.isFavBene = isFavBene;
	}
	public List<String> getTransferServiceTypes() {
		return transferServiceTypes;
	}
	public void setTransferServiceTypes(List<String> transferServiceTypes) {
		this.transferServiceTypes = transferServiceTypes;
	}
	public String getBeneID() {
		return beneID;
	}
	public void setBeneID(String beneID) {
		this.beneID = beneID;
	}
	public String getBeneBankNetworkClearingCode() {
		return beneBankNetworkClearingCode;
	}
	public void setBeneBankNetworkClearingCode(String beneBankNetworkClearingCode) {
		this.beneBankNetworkClearingCode = beneBankNetworkClearingCode;
	}
	public String getBeneBankCity() {
		return beneBankCity;
	}
	public void setBeneBankCity(String beneBankCity) {
		this.beneBankCity = beneBankCity;
	}
	public String getExec_time_batch_cd() {
		return exec_time_batch_cd;
	}
	public void setExec_time_batch_cd(String exec_time_batch_cd) {
		this.exec_time_batch_cd = exec_time_batch_cd;
	}
	public String getRecurring_exec_time_batch_cd() {
		return recurring_exec_time_batch_cd;
	}
	public void setRecurring_exec_time_batch_cd(String recurring_exec_time_batch_cd) {
		this.recurring_exec_time_batch_cd = recurring_exec_time_batch_cd;
	}
	public String getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}
	
	public String getBene_bank_id() {
		return bene_bank_id;
	}
	public void setBene_bank_id(String bene_bank_id) {
		this.bene_bank_id = bene_bank_id;
	}
	public String getTrx_purpose() {
		return trx_purpose;
	}
	public void setTrx_purpose(String trx_purpose) {
		this.trx_purpose = trx_purpose;
	}
	public String getProxy_data() {
		return proxy_data;
	}
	public void setProxy_data(String proxy_data) {
		this.proxy_data = proxy_data;
	}
	public String getProxy_type() {
		return proxy_type;
	}
	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	public String getCharges_acct_no() {
		return charges_acct_no;
	}
	public void setCharges_acct_no(String charges_acct_no) {
		this.charges_acct_no = charges_acct_no;
	}
	public String getCharges_acct_ccy() {
		return charges_acct_ccy;
	}
	public void setCharges_acct_ccy(String charges_acct_ccy) {
		this.charges_acct_ccy = charges_acct_ccy;
	}
	public String getSender_address1() {
		return sender_address1;
	}
	public void setSender_address1(String sender_address1) {
		this.sender_address1 = sender_address1;
	}
	public String getSender_address2() {
		return sender_address2;
	}
	public void setSender_address2(String sender_address2) {
		this.sender_address2 = sender_address2;
	}
	public String getSender_address3() {
		return sender_address3;
	}
	public void setSender_address3(String sender_address3) {
		this.sender_address3 = sender_address3;
	}
	public String getPurposeCode() {
		return purposeCode;
	}
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	public String getBeneCategoryCode() {
		return beneCategoryCode;
	}
	public void setBeneCategoryCode(String beneCategoryCode) {
		this.beneCategoryCode = beneCategoryCode;
	}
	public String getRemitterCountryCode() {
		return remitterCountryCode;
	}
	public void setRemitterCountryCode(String remitterCountryCode) {
		this.remitterCountryCode = remitterCountryCode;
	}
	public String getRemitterCategoryCode() {
		return remitterCategoryCode;
	}
	public void setRemitterCategoryCode(String remitterCategoryCode) {
		this.remitterCategoryCode = remitterCategoryCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
