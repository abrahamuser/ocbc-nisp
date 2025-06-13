package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ObTransactionResponse extends ObResponseCache implements Serializable{
	private Boolean allowProcess;
	private String startTime;
	private String endTime;
	private String errorMessageCOT;
	private String notAllowedFromCurrency;
	private String notAllowedToCurrency;
	private String notAllowedProvinceCode;
	private Boolean isPublicHoliday;
	private Boolean isCrossCurrencyAllowed;
	private Boolean isEligiblePurchaseForeignCcy;
	private Map<String, String> recurringList;
	private Map<String, String> fxOptionList;
	private Map<String, String> residentStatusList;
	private Map<String, String> categoryList;
	private Map<String, String> chargesMethodList;
	private Map<String, String> relationshipStatusList;
	private Map<String, String> paymentPurposeList;
	private Map<String, String> beneficiaryCategoryList;
	private Map<String, String> beneficiaryCountryList;
	private Map<String, String> remitterCountryList;
	private Map<String, String> remitterCategoryList;
	private Map<String, String> beneficiaryAffiliationList;
	private Map<String, String> menu;
	
	private BigDecimal rate;
	private BigDecimal amount;
	private String amountCcy;
	
	private String debitAccountNo;
	private String debitAccountCcy;
	private String debitAccountAliasName;
	private String debitAccountType;
	
	
	private String beneAccountNo;
	private String beneAccountCcy;
	private String beneAccountAliasName;
	private String beneAccountName;
	private String beneAccountType;
	
	private String valueDate;
	private String refId;//Bank reference id
	private String customerRefNo;
	private String remark;
	private Boolean isRecurring;
	private String recurringType;
	private String recurringValue;
	private String recurringStartDate;
	private String recurringEndDate;
	
	private String fxType;
	private String fxDealerName;
	private String fxContractNo;
	
	private String senderName;
	private String beneAddress1;
	private String beneAddress2;
	private String beneAddress3;
	private String residentStatus;
	private String beneCategory;
	private String remitterCategory;
	
	private String trxStatusCode;
	private String trxStatusDesc;
	
	private String bankRef;
	
	private List<String> accessRestriction;
	
	private String bankCode;
	
	private Boolean isBackDated;
	private Boolean isCot;
	private Boolean isHoliday;
	private String nextValueDate;
	
	private List<ObCOTConfigDataBean> ccyList;
	
	private List<TimeBatchDataBean> time_batch_list;
	
	private List<ObBiFastBean> proxyTypeList;
	private List<ObBiFastBean> trxPurposeList;
	
	private String bene_name;
	private String bene_acct_no;
	private String bene_acct_cc;
	private String bene_bank_id;
	private String bene_bank_name;
	
	private String trx_purpose;
	
	private String additional_info;
	private String documentId;
	private String documentType;
	
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
	public Boolean getIsPublicHoliday() {
		return isPublicHoliday;
	}
	public void setIsPublicHoliday(Boolean isPublicHoliday) {
		this.isPublicHoliday = isPublicHoliday;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDebitAccountNo() {
		return debitAccountNo;
	}
	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}
	public String getDebitAccountAliasName() {
		return debitAccountAliasName;
	}
	public void setDebitAccountAliasName(String debitAccountAliasName) {
		this.debitAccountAliasName = debitAccountAliasName;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneAccountAliasName() {
		return beneAccountAliasName;
	}
	public void setBeneAccountAliasName(String beneAccountAliasName) {
		this.beneAccountAliasName = beneAccountAliasName;
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
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
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
	public Map<String, String> getRecurringList() {
		return recurringList;
	}
	public void setRecurringList(Map<String, String> recurringList) {
		this.recurringList = recurringList;
	}
	public String getDebitAccountCcy() {
		return debitAccountCcy;
	}
	public void setDebitAccountCcy(String debitAccountCcy) {
		this.debitAccountCcy = debitAccountCcy;
	}
	public String getBeneAccountCcy() {
		return beneAccountCcy;
	}
	public void setBeneAccountCcy(String beneAccountCcy) {
		this.beneAccountCcy = beneAccountCcy;
	}
	public String getBankRef() {
		return bankRef;
	}
	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}
	public Map<String, String> getFxOptionList() {
		return fxOptionList;
	}
	public void setFxOptionList(Map<String, String> fxOptionList) {
		this.fxOptionList = fxOptionList;
	}
	public String getFxType() {
		return fxType;
	}
	public void setFxType(String fxType) {
		this.fxType = fxType;
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
	public Boolean getIsCrossCurrencyAllowed() {
		return isCrossCurrencyAllowed;
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
	public void setIsCrossCurrencyAllowed(Boolean isCrossCurrencyAllowed) {
		this.isCrossCurrencyAllowed = isCrossCurrencyAllowed;
	}
	public String getNotAllowedProvinceCode() {
		return notAllowedProvinceCode;
	}
	public void setNotAllowedProvinceCode(String notAllowedProvinceCode) {
		this.notAllowedProvinceCode = notAllowedProvinceCode;
	}
	public Map<String, String> getResidentStatusList() {
		return residentStatusList;
	}
	public void setResidentStatusList(Map<String, String> residentStatusList) {
		this.residentStatusList = residentStatusList;
	}
	public Map<String, String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(Map<String, String> categoryList) {
		this.categoryList = categoryList;
	}
	public String getDebitAccountType() {
		return debitAccountType;
	}
	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}
	public String getBeneAccountType() {
		return beneAccountType;
	}
	public void setBeneAccountType(String beneAccountType) {
		this.beneAccountType = beneAccountType;
	}
	public List<String> getAccessRestriction() {
		return accessRestriction;
	}
	public void setAccessRestriction(List<String> accessRestriction) {
		this.accessRestriction = accessRestriction;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getResidentStatus() {
		return residentStatus;
	}
	public void setResidentStatus(String residentStatus) {
		this.residentStatus = residentStatus;
	}
	public String getRemitterCategory() {
		return remitterCategory;
	}
	public void setRemitterCategory(String remitterCategory) {
		this.remitterCategory = remitterCategory;
	}
	public String getBeneCategory() {
		return beneCategory;
	}
	public void setBeneCategory(String beneCategory) {
		this.beneCategory = beneCategory;
	}
	public String getBeneAddress1() {
		return beneAddress1;
	}
	public void setBeneAddress1(String beneAddress1) {
		this.beneAddress1 = beneAddress1;
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
	public Boolean getIsEligiblePurchaseForeignCcy() {
		return isEligiblePurchaseForeignCcy;
	}
	public void setIsEligiblePurchaseForeignCcy(Boolean isEligiblePurchaseForeignCcy) {
		this.isEligiblePurchaseForeignCcy = isEligiblePurchaseForeignCcy;
	}
	public String getBeneAccountName() {
		return beneAccountName;
	}
	public void setBeneAccountName(String beneAccountName) {
		this.beneAccountName = beneAccountName;
	}
	public Map<String, String> getChargesMethodList() {
		return chargesMethodList;
	}
	public void setChargesMethodList(Map<String, String> chargesMethodList) {
		this.chargesMethodList = chargesMethodList;
	}
	public Map<String, String> getRelationshipStatusList() {
		return relationshipStatusList;
	}
	public void setRelationshipStatusList(Map<String, String> relationshipStatusList) {
		this.relationshipStatusList = relationshipStatusList;
	}
	public Map<String, String> getPaymentPurposeList() {
		return paymentPurposeList;
	}
	public void setPaymentPurposeList(Map<String, String> paymentPurposeList) {
		this.paymentPurposeList = paymentPurposeList;
	}
	public Map<String, String> getBeneficiaryCategoryList() {
		return beneficiaryCategoryList;
	}
	public void setBeneficiaryCategoryList(Map<String, String> beneficiaryCategoryList) {
		this.beneficiaryCategoryList = beneficiaryCategoryList;
	}
	public Map<String, String> getBeneficiaryCountryList() {
		return beneficiaryCountryList;
	}
	public void setBeneficiaryCountryList(Map<String, String> beneficiaryCountryList) {
		this.beneficiaryCountryList = beneficiaryCountryList;
	}
	public Map<String, String> getRemitterCountryList() {
		return remitterCountryList;
	}
	public void setRemitterCountryList(Map<String, String> remitterCountryList) {
		this.remitterCountryList = remitterCountryList;
	}
	public Map<String, String> getRemitterCategoryList() {
		return remitterCategoryList;
	}
	public void setRemitterCategoryList(Map<String, String> remitterCategoryList) {
		this.remitterCategoryList = remitterCategoryList;
	}
	public Map<String, String> getBeneficiaryAffiliationList() {
		return beneficiaryAffiliationList;
	}
	public void setBeneficiaryAffiliationList(Map<String, String> beneficiaryAffiliationList) {
		this.beneficiaryAffiliationList = beneficiaryAffiliationList;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public Boolean getIsBackDated() {
		return isBackDated;
	}
	public void setIsBackDated(Boolean isBackDated) {
		this.isBackDated = isBackDated;
	}
	public Boolean getIsCot() {
		return isCot;
	}
	public void setIsCot(Boolean isCot) {
		this.isCot = isCot;
	}
	public Boolean getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(Boolean isHoliday) {
		this.isHoliday = isHoliday;
	}
	public String getNextValueDate() {
		return nextValueDate;
	}
	public void setNextValueDate(String nextValueDate) {
		this.nextValueDate = nextValueDate;
	}
	public Map<String, String> getMenu() {
		return menu;
	}
	public void setMenu(Map<String, String> menu) {
		this.menu = menu;
	}
	public List<ObCOTConfigDataBean> getCcyList() {
		return ccyList;
	}
	public void setCcyList(List<ObCOTConfigDataBean> ccyList) {
		this.ccyList = ccyList;
	}
	public List<TimeBatchDataBean> getTime_batch_list() {
		return time_batch_list;
	}
	public void setTime_batch_list(List<TimeBatchDataBean> time_batch_list) {
		this.time_batch_list = time_batch_list;
	}
	public List<ObBiFastBean> getProxyTypeList() {
		return proxyTypeList;
	}
	public void setProxyTypeList(List<ObBiFastBean> proxyTypeList) {
		this.proxyTypeList = proxyTypeList;
	}
	public List<ObBiFastBean> getTrxPurposeList() {
		return trxPurposeList;
	}
	public void setTrxPurposeList(List<ObBiFastBean> trxPurposeList) {
		this.trxPurposeList = trxPurposeList;
	}
	public String getBene_name() {
		return bene_name;
	}
	public void setBene_name(String bene_name) {
		this.bene_name = bene_name;
	}
	public String getBene_acct_no() {
		return bene_acct_no;
	}
	public void setBene_acct_no(String bene_acct_no) {
		this.bene_acct_no = bene_acct_no;
	}
	public String getBene_acct_cc() {
		return bene_acct_cc;
	}
	public void setBene_acct_cc(String bene_acct_cc) {
		this.bene_acct_cc = bene_acct_cc;
	}
	public String getBene_bank_id() {
		return bene_bank_id;
	}
	public void setBene_bank_id(String bene_bank_id) {
		this.bene_bank_id = bene_bank_id;
	}
	public String getBene_bank_name() {
		return bene_bank_name;
	}
	public void setBene_bank_name(String bene_bank_name) {
		this.bene_bank_name = bene_bank_name;
	}
	public String getTrx_purpose() {
		return trx_purpose;
	}
	public void setTrx_purpose(String trx_purpose) {
		this.trx_purpose = trx_purpose;
	}
	public String getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}
	public String getDocumentId() {
		return documentId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	
}
