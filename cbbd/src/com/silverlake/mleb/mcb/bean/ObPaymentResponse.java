package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ObPaymentResponse extends ObResponseCache implements Serializable{
	private Boolean allowProcess;
	private String startTime;
	private String endTime;
	private String errorMessageCOT;
	private String notAllowedFromCurrency;
	private String notAllowedToCurrency;
	private String notAllowedProvinceCode;
	private Boolean isPublicHoliday;
	private Map<String, String> recurringList;
	private List<ObPaymentBillerGroupBean> billerGroupList;
	private List<Payee> payeeList;
	private List<ObAmountOptionBean> amountOptions;
	private List<MapPojo> details;
	
	private String debitAccountNo;
	private String debitAccountCcy;
	private String debitAccountType;
	private String debitAccountAliasName;
	
	private BigDecimal amount;
	private String amountCcy;
	private String valueDate;
	private String refId;//Bank reference id
	private String customerRefNo;
	private String remark;
	private Boolean isRecurring;
	private String recurringType;
	private String recurringValue;
	private String recurringStartDate;
	private String recurringEndDate;
	
	private String billingId;
	private String billerName;
	
	private String trxStatusCode;
	private String trxStatusDesc;
	
	private String bankRef;
	private List<String> accessRestriction;

	private List<TimeBatchDataBean> time_batch_list;
	
	public Boolean getAllowProcess() {
		return allowProcess;
	}

	public void setAllowProcess(Boolean allowProcess) {
		this.allowProcess = allowProcess;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getErrorMessageCOT() {
		return errorMessageCOT;
	}

	public void setErrorMessageCOT(String errorMessageCOT) {
		this.errorMessageCOT = errorMessageCOT;
	}

	public String getNotAllowedFromCurrency() {
		return notAllowedFromCurrency;
	}

	public void setNotAllowedFromCurrency(String notAllowedFromCurrency) {
		this.notAllowedFromCurrency = notAllowedFromCurrency;
	}

	public String getNotAllowedToCurrency() {
		return notAllowedToCurrency;
	}

	public void setNotAllowedToCurrency(String notAllowedToCurrency) {
		this.notAllowedToCurrency = notAllowedToCurrency;
	}

	public String getNotAllowedProvinceCode() {
		return notAllowedProvinceCode;
	}

	public void setNotAllowedProvinceCode(String notAllowedProvinceCode) {
		this.notAllowedProvinceCode = notAllowedProvinceCode;
	}

	public Boolean getIsPublicHoliday() {
		return isPublicHoliday;
	}

	public void setIsPublicHoliday(Boolean isPublicHoliday) {
		this.isPublicHoliday = isPublicHoliday;
	}

	public String getDebitAccountNo() {
		return debitAccountNo;
	}

	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}

	public String getDebitAccountCcy() {
		return debitAccountCcy;
	}

	public void setDebitAccountCcy(String debitAccountCcy) {
		this.debitAccountCcy = debitAccountCcy;
	}

	public String getDebitAccountAliasName() {
		return debitAccountAliasName;
	}

	public void setDebitAccountAliasName(String debitAccountAliasName) {
		this.debitAccountAliasName = debitAccountAliasName;
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

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getCustomerRefNo() {
		return customerRefNo;
	}

	public void setCustomerRefNo(String customerRefNo) {
		this.customerRefNo = customerRefNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getTrxStatusCode() {
		return trxStatusCode;
	}

	public void setTrxStatusCode(String trxStatusCode) {
		this.trxStatusCode = trxStatusCode;
	}

	public String getTrxStatusDesc() {
		return trxStatusDesc;
	}

	public void setTrxStatusDesc(String trxStatusDesc) {
		this.trxStatusDesc = trxStatusDesc;
	}

	public String getBankRef() {
		return bankRef;
	}

	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}

	public Map<String, String> getRecurringList() {
		return recurringList;
	}

	public void setRecurringList(Map<String, String> recurringList) {
		this.recurringList = recurringList;
	}

	public List<ObPaymentBillerGroupBean> getBillerGroupList() {
		return billerGroupList;
	}

	public void setBillerGroupList(List<ObPaymentBillerGroupBean> billerGroupList) {
		this.billerGroupList = billerGroupList;
	}

	public List<Payee> getPayeeList() {
		return payeeList;
	}

	public void setPayeeList(List<Payee> payeeList) {
		this.payeeList = payeeList;
	}

	public List<ObAmountOptionBean> getAmountOptions() {
		return amountOptions;
	}

	public void setAmountOptions(List<ObAmountOptionBean> amountOptions) {
		this.amountOptions = amountOptions;
	}

	public List<MapPojo> getDetails() {
		return details;
	}

	public void setDetails(List<MapPojo> details) {
		this.details = details;
	}

	public String getAmountCcy() {
		return amountCcy;
	}

	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}

	public String getBillingId() {
		return billingId;
	}

	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	public String getBillerName() {
		return billerName;
	}

	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}

	public String getDebitAccountType() {
		return debitAccountType;
	}

	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}

	public List<String> getAccessRestriction() {
		return accessRestriction;
	}

	public void setAccessRestriction(List<String> accessRestriction) {
		this.accessRestriction = accessRestriction;
	}

	public List<TimeBatchDataBean> getTime_batch_list() {
		return time_batch_list;
	}

	public void setTime_batch_list(List<TimeBatchDataBean> time_batch_list) {
		this.time_batch_list = time_batch_list;
	}
	
	
}
