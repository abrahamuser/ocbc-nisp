 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class ObPaymentRequest extends ObRequestCache<ObPaymentSessionCache> implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String billerType;
	protected String groupName;
	protected String billerCode;
	protected String billerName;
	protected Integer flowType;
	protected String debitAccountUUID;
	protected String billerId;
	protected String billingId;
	protected String customerRef;
	protected BigDecimal amount;
	protected String valueDate;
	protected Boolean isSavePayee;
	protected String payeeNickName;
	protected String ppType;
	
	private Boolean isSendSenderNotification;
	private String senderNotificationEmail;
	private Boolean isSenderNotifyCompleted;
	private Boolean isSenderNotifyRejected;
	private Boolean isSenderNotifySuspected;
	
	private Boolean isRecurring;
	private String recurringType;
	private String recurringValue;
	private String recurringStartDate;
	private String recurringEndDate;
	
	private String amountOptionId;
	private String exec_time_batch_cd;
	private String recurring_exec_time_batch_cd;
	private String ip;
	
	private String lang;
	
	public String getBillerType() {
		return billerType;
	}
	public void setBillerType(String billerType) {
		this.billerType = billerType;
	}
	public String getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(String billerCode) {
		this.billerCode = billerCode;
	}
	public String getDebitAccountUUID() {
		return debitAccountUUID;
	}
	public void setDebitAccountUUID(String debitAccountUUID) {
		this.debitAccountUUID = debitAccountUUID;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getBillingId() {
		return billingId;
	}
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}
	public String getCustomerRef() {
		return customerRef;
	}
	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public Integer getFlowType() {
		return flowType;
	}
	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
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
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getAmountOptionId() {
		return amountOptionId;
	}
	public void setAmountOptionId(String amountOptionId) {
		this.amountOptionId = amountOptionId;
	}
	public Boolean getIsSavePayee() {
		return isSavePayee;
	}
	public void setIsSavePayee(Boolean isSavePayee) {
		this.isSavePayee = isSavePayee;
	}
	public String getPayeeNickName() {
		return payeeNickName;
	}
	public void setPayeeNickName(String payeeNickName) {
		this.payeeNickName = payeeNickName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPpType() {
		return ppType;
	}
	public void setPpType(String ppType) {
		this.ppType = ppType;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
}

