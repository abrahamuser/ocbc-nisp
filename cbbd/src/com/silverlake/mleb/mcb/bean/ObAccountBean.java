package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ObAccountBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String accountDescription;
	private String accountName;
	private String accountAlias;
	private String accountNumber;
	private String accountTypeCode;
	private String accountTypeCodeDesc;
	private String accountUUID;
	private String currencyCode;
	private String productCode;
	private String productStatusCode;
	private String productTypeCode;
	private String relationshipCode;
	private String relationshipModeCode;
	private String refNo;
	private String reconReferenceId;
	private String failureReason;
	private String transtatus;
	private String createBy;
	private String createDt;
	private String accountIndex;
	private String accountOrder;
	private String bankCode;
	private String bankName;
	private String rejectCode;
	private String rejectMessage;
	private String transactionDate;
	private String transactionTime;
	private String debitCreditIndicator;
	private String maturityDate;
	private String effectiveDate;
	private BigDecimal availableBalance;
	private BigDecimal currentBalance;
	private BigDecimal convertedAmount;
	private BigDecimal exchangeRate;
	private BigDecimal principalAmount;
	private BigDecimal interestRate;
	private BigDecimal interestAmount;
	private Integer interestTerm;
    private String interestTermCode;
    private String rolloverTypeCode;
    private String rolloverTypeDesc;
    private Integer tenor;
    private String tenorType;

	private boolean allowDisplay;
	private boolean allowTransactionFrom;
	private boolean allowTransactionTo;
	private boolean isIslamic;
	private boolean isMach;
	private boolean isShareLimit;
	
	private boolean isServiceChargePayer;
	private String debitCardNumber;
	private String debitCardStatus;
	private String cif;
	private String lang;
	private String userID;
	
	private BigDecimal availableBalances;
	private String currentBalances;
	private String productName;
	private String labelDebitAcc;
	private String accountType;
	private String mcBit;
	private String accountBranch;
	private String debitBranchCode;
	private List<String> lsCcyCode;
	private String accountId;
	
	private String groupNumber;
	private String groupType;
	private String accountStatus;
	private String maintenanceType;
	private String authStatusCode;
	private String authStatusDesc;
	
	
    private String version;
	
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountTypeCode() {
		return accountTypeCode;
	}
	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}
	public String getAccountTypeCodeDesc() {
		return accountTypeCodeDesc;
	}
	public void setAccountTypeCodeDesc(String accountTypeCodeDesc) {
		this.accountTypeCodeDesc = accountTypeCodeDesc;
	}
	public String getAccountUUID() {
		return accountUUID;
	}
	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductStatusCode() {
		return productStatusCode;
	}
	public void setProductStatusCode(String productStatusCode) {
		this.productStatusCode = productStatusCode;
	}
	public String getProductTypeCode() {
		return productTypeCode;
	}
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}
	public String getRelationshipCode() {
		return relationshipCode;
	}
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}
	public String getRelationshipModeCode() {
		return relationshipModeCode;
	}
	public void setRelationshipModeCode(String relationshipModeCode) {
		this.relationshipModeCode = relationshipModeCode;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getReconReferenceId() {
		return reconReferenceId;
	}
	public void setReconReferenceId(String reconReferenceId) {
		this.reconReferenceId = reconReferenceId;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	public String getTranstatus() {
		return transtatus;
	}
	public void setTranstatus(String transtatus) {
		this.transtatus = transtatus;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getAccountIndex() {
		return accountIndex;
	}
	public void setAccountIndex(String accountIndex) {
		this.accountIndex = accountIndex;
	}
	public String getAccountOrder() {
		return accountOrder;
	}
	public void setAccountOrder(String accountOrder) {
		this.accountOrder = accountOrder;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getRejectCode() {
		return rejectCode;
	}
	public void setRejectCode(String rejectCode) {
		this.rejectCode = rejectCode;
	}
	public String getRejectMessage() {
		return rejectMessage;
	}
	public void setRejectMessage(String rejectMessage) {
		this.rejectMessage = rejectMessage;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getDebitCreditIndicator() {
		return debitCreditIndicator;
	}
	public void setDebitCreditIndicator(String debitCreditIndicator) {
		this.debitCreditIndicator = debitCreditIndicator;
	}
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}
	public void setConvertedAmount(BigDecimal convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public boolean isAllowDisplay() {
		return allowDisplay;
	}
	public void setAllowDisplay(boolean allowDisplay) {
		this.allowDisplay = allowDisplay;
	}
	public boolean isAllowTransactionFrom() {
		return allowTransactionFrom;
	}
	public void setAllowTransactionFrom(boolean allowTransactionFrom) {
		this.allowTransactionFrom = allowTransactionFrom;
	}
	public boolean isAllowTransactionTo() {
		return allowTransactionTo;
	}
	public void setAllowTransactionTo(boolean allowTransactionTo) {
		this.allowTransactionTo = allowTransactionTo;
	}
	public boolean isIslamic() {
		return isIslamic;
	}
	public void setIslamic(boolean isIslamic) {
		this.isIslamic = isIslamic;
	}
	public boolean isMach() {
		return isMach;
	}
	public void setMach(boolean isMach) {
		this.isMach = isMach;
	}
	public boolean isShareLimit() {
		return isShareLimit;
	}
	public void setShareLimit(boolean isShareLimit) {
		this.isShareLimit = isShareLimit;
	}
	public boolean isServiceChargePayer() {
		return isServiceChargePayer;
	}
	public void setServiceChargePayer(boolean isServiceChargePayer) {
		this.isServiceChargePayer = isServiceChargePayer;
	}
	public String getDebitCardNumber() {
		return debitCardNumber;
	}
	public void setDebitCardNumber(String debitCardNumber) {
		this.debitCardNumber = debitCardNumber;
	}
	public String getDebitCardStatus() {
		return debitCardStatus;
	}
	public void setDebitCardStatus(String debitCardStatus) {
		this.debitCardStatus = debitCardStatus;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public BigDecimal getAvailableBalances() {
		return availableBalances;
	}
	public void setAvailableBalances(BigDecimal availableBalances) {
		this.availableBalances = availableBalances;
	}
	public String getCurrentBalances() {
		return currentBalances;
	}
	public void setCurrentBalances(String currentBalances) {
		this.currentBalances = currentBalances;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLabelDebitAcc() {
		return labelDebitAcc;
	}
	public void setLabelDebitAcc(String labelDebitAcc) {
		this.labelDebitAcc = labelDebitAcc;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getMcBit() {
		return mcBit;
	}
	public void setMcBit(String mcBit) {
		this.mcBit = mcBit;
	}
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getDebitBranchCode() {
		return debitBranchCode;
	}
	public void setDebitBranchCode(String debitBranchCode) {
		this.debitBranchCode = debitBranchCode;
	}
	public List<String> getLsCcyCode() {
		return lsCcyCode;
	}
	public void setLsCcyCode(List<String> lsCcyCode) {
		this.lsCcyCode = lsCcyCode;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getAccountAlias() {
		return accountAlias;
	}
	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public BigDecimal getInterestAmount() {
		return interestAmount;
	}
	public Integer getInterestTerm() {
		return interestTerm;
	}
	public String getInterestTermCode() {
		return interestTermCode;
	}
	public String getRolloverTypeCode() {
		return rolloverTypeCode;
	}
	public String getRolloverTypeDesc() {
		return rolloverTypeDesc;
	}
	public Integer getTenor() {
		return tenor;
	}
	public String getTenorType() {
		return tenorType;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}
	public void setInterestTerm(Integer interestTerm) {
		this.interestTerm = interestTerm;
	}
	public void setInterestTermCode(String interestTermCode) {
		this.interestTermCode = interestTermCode;
	}
	public void setRolloverTypeCode(String rolloverTypeCode) {
		this.rolloverTypeCode = rolloverTypeCode;
	}
	public void setRolloverTypeDesc(String rolloverTypeDesc) {
		this.rolloverTypeDesc = rolloverTypeDesc;
	}
	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}
	public void setTenorType(String tenorType) {
		this.tenorType = tenorType;
	}
	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getMaintenanceType() {
		return maintenanceType;
	}
	public String getAuthStatusCode() {
		return authStatusCode;
	}
	public String getAuthStatusDesc() {
		return authStatusDesc;
	}
	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}
	public void setAuthStatusCode(String authStatusCode) {
		this.authStatusCode = authStatusCode;
	}
	public void setAuthStatusDesc(String authStatusDesc) {
		this.authStatusDesc = authStatusDesc;
	}
		
	
}
