package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ObAccountTransactionHistoryResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -3945904171641704280L;
	private String productCode;
	private String productName;
	private BigDecimal balance;
	private BigDecimal availableBalance;
	private BigDecimal holdBalance;
	private BigDecimal overdraftBalance;
	private String accountNo;
	private String accountCcy;
	private String accountType;
	private String tenor;
	private String tenorCode;
	private String interestRate;
	private BigDecimal amountDue;
	private String effectiveDate;
	private String maturityDate;
	private String receiptNo;
	private BigDecimal estimatedAmount;
	private BigDecimal outstandingUnit;
	private BigDecimal netAssetValue;
	private String statusDesc;
	private String policyNo;
	private BigDecimal amount;
	private String lastCapitalRepaymentDate;
	private String ccNumber;
	private String pointAvailable;
	private BigDecimal ccLimit;
	private BigDecimal lastBilledPay;
	private Integer totalPage;
	private Integer totalAllData;
	private List<ObRefreshMultiCurrencyBean> refreshMultiCurrencyBeanList;
	private List<ObRefreshPointAccountBean> refreshPointAccountBeanList;
	private List<ObAccountTransactionHistoryBean> accountTransactionHistoryBeanList;
	private List<ObAccountOverviewBean> accountListing;
	
	private List<ObMaturityInstructionBean> maturityInstructionListing;
	private Boolean isSyariahProduct;
	
	// Obligasi
	private String accountId;
	private String maturityValue;
	private String initialInvestment;
	private String cutOffTime;
	private String investmentLength;
	private List<String> investmentPolicy;
	private String denomination;
	
	// Taka Bunga & Taka Hadiah & Taka 360 Hold
	private BigDecimal targetAmount;
	private String initialDeposit;
	private BigDecimal paymentAmount;
	private String dueDate;
	private String durationTerm;
	private String durationPeriod;
	private String hadiah;
	private String messageType;
	private String message;
	private String penaltyFee;
	private String amountHold;
	
	//Time Deposit
	private BigDecimal principleAmount;
	private String interestAmount;
	private String relatedAccountProductName;
	private String relatedAccountNo;
	private String autoRenewal; // Y -  Yes, N - No
	private String interestPaymentMethod; // D - Daily, M - Monthly, Y - Yearly
	private ObAccountMaintenanceListBean  obAccountMaintenanceListBean;
	private String tdRenewalCounter;
	private String tdRenewalCounterCode; // autoRenewal
	
	// Insurance
	private String policyStatus;
	private String cif;

	private String productCompany;
	private String fundValue;
	private String NAV;
	private String change;
//	private String cutOffTime;
//	private String investmentLength;
//	private List<String> investmentPolicy;
	private String riskTolerance;
	private List<String> investmentProduct;
	
	// Insurance detail
	private String insuredName;
	private String coveragePeriod;
	private String sumAssured;
//	private String paymentAmount;
	private String subscriptionDate;
	private String paymentDuration;
	private String paymentFrequency;
	private List<String> ridersInsurance;
	
	//
//	//Credit Card
//	private String points;
//	private String expiryDate;
//	private String creditLimit;
	private BigDecimal lastStatementBalance;
	private BigDecimal minPayment;
//	private String due;
	
	// Loan
	private BigDecimal monthlyPayment;
	private BigDecimal outstandingBalance;
	private BigDecimal totalLoan;
	
	// Available Type Payment
	private Boolean isAllowedTransfer;
	private Boolean isAllowedPayment;
	private Boolean isAllowedPurchase;
	
	// unit trust
	private Boolean isRDB;
	private String autoSubsUntil;
	private BigDecimal monthlySubs;
	private Boolean withInsurance;
	private Boolean isAutoReedem;
	private Integer isFailedAutoDebet;
	private Boolean autoDebetStatus;
	private Boolean isRDBAllowRedeem;
	private Boolean isRDBAllowSwitch;
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getHoldBalance() {
		return holdBalance;
	}
	public void setHoldBalance(BigDecimal holdBalance) {
		this.holdBalance = holdBalance;
	}
	public BigDecimal getOverdraftBalance() {
		return overdraftBalance;
	}
	public void setOverdraftBalance(BigDecimal overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountCcy() {
		return accountCcy;
	}
	public void setAccountCcy(String accountCcy) {
		this.accountCcy = accountCcy;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public String getTenorCode() {
		return tenorCode;
	}
	public void setTenorCode(String tenorCode) {
		this.tenorCode = tenorCode;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public BigDecimal getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public BigDecimal getEstimatedAmount() {
		return estimatedAmount;
	}
	public void setEstimatedAmount(BigDecimal estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
	public BigDecimal getOutstandingUnit() {
		return outstandingUnit;
	}
	public void setOutstandingUnit(BigDecimal outstandingUnit) {
		this.outstandingUnit = outstandingUnit;
	}
	public BigDecimal getNetAssetValue() {
		return netAssetValue;
	}
	public void setNetAssetValue(BigDecimal netAssetValue) {
		this.netAssetValue = netAssetValue;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getLastCapitalRepaymentDate() {
		return lastCapitalRepaymentDate;
	}
	public void setLastCapitalRepaymentDate(String lastCapitalRepaymentDate) {
		this.lastCapitalRepaymentDate = lastCapitalRepaymentDate;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getPointAvailable() {
		return pointAvailable;
	}
	public void setPointAvailable(String pointAvailable) {
		this.pointAvailable = pointAvailable;
	}
	public BigDecimal getCcLimit() {
		return ccLimit;
	}
	public void setCcLimit(BigDecimal ccLimit) {
		this.ccLimit = ccLimit;
	}
	public BigDecimal getLastBilledPay() {
		return lastBilledPay;
	}
	public void setLastBilledPay(BigDecimal lastBilledPay) {
		this.lastBilledPay = lastBilledPay;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<ObRefreshMultiCurrencyBean> getRefreshMultiCurrencyBeanList() {
		return refreshMultiCurrencyBeanList;
	}
	public void setRefreshMultiCurrencyBeanList(
			List<ObRefreshMultiCurrencyBean> refreshMultiCurrencyBeanList) {
		this.refreshMultiCurrencyBeanList = refreshMultiCurrencyBeanList;
	}
	public List<ObRefreshPointAccountBean> getRefreshPointAccountBeanList() {
		return refreshPointAccountBeanList;
	}
	public void setRefreshPointAccountBeanList(
			List<ObRefreshPointAccountBean> refreshPointAccountBeanList) {
		this.refreshPointAccountBeanList = refreshPointAccountBeanList;
	}
	public List<ObAccountTransactionHistoryBean> getAccountTransactionHistoryBeanList() {
		return accountTransactionHistoryBeanList;
	}
	public void setAccountTransactionHistoryBeanList(
			List<ObAccountTransactionHistoryBean> accountTransactionHistoryBeanList) {
		this.accountTransactionHistoryBeanList = accountTransactionHistoryBeanList;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMaturityValue() {
		return maturityValue;
	}
	public void setMaturityValue(String maturityValue) {
		this.maturityValue = maturityValue;
	}
	public String getInitialInvestment() {
		return initialInvestment;
	}
	public void setInitialInvestment(String initialInvestment) {
		this.initialInvestment = initialInvestment;
	}
	public String getCutOffTime() {
		return cutOffTime;
	}
	public void setCutOffTime(String cutOffTime) {
		this.cutOffTime = cutOffTime;
	}
	public String getInvestmentLength() {
		return investmentLength;
	}
	public void setInvestmentLength(String investmentLength) {
		this.investmentLength = investmentLength;
	}
	public List<String> getInvestmentPolicy() {
		return investmentPolicy;
	}
	public void setInvestmentPolicy(List<String> investmentPolicy) {
		this.investmentPolicy = investmentPolicy;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public BigDecimal getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}
	public String getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(String initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDurationTerm() {
		return durationTerm;
	}
	public void setDurationTerm(String durationTerm) {
		this.durationTerm = durationTerm;
	}
	public String getDurationPeriod() {
		return durationPeriod;
	}
	public void setDurationPeriod(String durationPeriod) {
		this.durationPeriod = durationPeriod;
	}
	public String getHadiah() {
		return hadiah;
	}
	public void setHadiah(String hadiah) {
		this.hadiah = hadiah;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPenaltyFee() {
		return penaltyFee;
	}
	public void setPenaltyFee(String penaltyFee) {
		this.penaltyFee = penaltyFee;
	}
	public String getAmountHold() {
		return amountHold;
	}
	public void setAmountHold(String amountHold) {
		this.amountHold = amountHold;
	}
	public BigDecimal getPrincipleAmount() {
		return principleAmount;
	}
	public void setPrincipleAmount(BigDecimal principleAmount) {
		this.principleAmount = principleAmount;
	}
	public String getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}
	public String getRelatedAccountProductName() {
		return relatedAccountProductName;
	}
	public void setRelatedAccountProductName(String relatedAccountProductName) {
		this.relatedAccountProductName = relatedAccountProductName;
	}
	public String getRelatedAccountNo() {
		return relatedAccountNo;
	}
	public void setRelatedAccountNo(String relatedAccountNo) {
		this.relatedAccountNo = relatedAccountNo;
	}
	public String getAutoRenewal() {
		return autoRenewal;
	}
	public void setAutoRenewal(String autoRenewal) {
		this.autoRenewal = autoRenewal;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getProductCompany() {
		return productCompany;
	}
	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}
	public String getFundValue() {
		return fundValue;
	}
	public void setFundValue(String fundValue) {
		this.fundValue = fundValue;
	}
	public String getNAV() {
		return NAV;
	}
	public void setNAV(String nAV) {
		NAV = nAV;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getRiskTolerance() {
		return riskTolerance;
	}
	public void setRiskTolerance(String riskTolerance) {
		this.riskTolerance = riskTolerance;
	}
	public List<String> getInvestmentProduct() {
		return investmentProduct;
	}
	public void setInvestmentProduct(List<String> investmentProduct) {
		this.investmentProduct = investmentProduct;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getCoveragePeriod() {
		return coveragePeriod;
	}
	public void setCoveragePeriod(String coveragePeriod) {
		this.coveragePeriod = coveragePeriod;
	}
	public String getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}
	public String getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public String getPaymentDuration() {
		return paymentDuration;
	}
	public void setPaymentDuration(String paymentDuration) {
		this.paymentDuration = paymentDuration;
	}
	public String getPaymentFrequency() {
		return paymentFrequency;
	}
	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}
	public List<String> getRidersInsurance() {
		return ridersInsurance;
	}
	public void setRidersInsurance(List<String> ridersInsurance) {
		this.ridersInsurance = ridersInsurance;
	}
	public BigDecimal getLastStatementBalance() {
		return lastStatementBalance;
	}
	public void setLastStatementBalance(BigDecimal lastStatementBalance) {
		this.lastStatementBalance = lastStatementBalance;
	}
	public BigDecimal getMinPayment() {
		return minPayment;
	}
	public void setMinPayment(BigDecimal minPayment) {
		this.minPayment = minPayment;
	}
	public BigDecimal getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(BigDecimal monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public BigDecimal getOutstandingBalance() {
		return outstandingBalance;
	}
	public void setOutstandingBalance(BigDecimal outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}
	public BigDecimal getTotalLoan() {
		return totalLoan;
	}
	public void setTotalLoan(BigDecimal totalLoan) {
		this.totalLoan = totalLoan;
	}
	public String getInterestPaymentMethod() {
		return interestPaymentMethod;
	}
	public void setInterestPaymentMethod(String interestPaymentMethod) {
		this.interestPaymentMethod = interestPaymentMethod;
	}

	public ObAccountMaintenanceListBean getObAccountMaintenanceListBean() {
		return obAccountMaintenanceListBean;
	}
	public void setObAccountMaintenanceListBean(
			ObAccountMaintenanceListBean obAccountMaintenanceListBean) {
		this.obAccountMaintenanceListBean = obAccountMaintenanceListBean;
	}
	public String getTdRenewalCounter() {
		return tdRenewalCounter;
	}
	public void setTdRenewalCounter(String tdRenewalCounter) {
		this.tdRenewalCounter = tdRenewalCounter;
	}
	public String getTdRenewalCounterCode() {
		return tdRenewalCounterCode;
	}
	public void setTdRenewalCounterCode(String tdRenewalCounterCode) {
		this.tdRenewalCounterCode = tdRenewalCounterCode;
	}

	public List<ObAccountOverviewBean> getAccountListing() {
		return accountListing;
	}
	public void setAccountListing(List<ObAccountOverviewBean> accountListing) {
		this.accountListing = accountListing;
	}
	public Boolean getIsAllowedTransfer() {
		return isAllowedTransfer;
	}
	public void setIsAllowedTransfer(Boolean isAllowedTransfer) {
		this.isAllowedTransfer = isAllowedTransfer;
	}
	public Boolean getIsAllowedPayment() {
		return isAllowedPayment;
	}
	public void setIsAllowedPayment(Boolean isAllowedPayment) {
		this.isAllowedPayment = isAllowedPayment;
	}
	public Boolean getIsAllowedPurchase() {
		return isAllowedPurchase;
	}
	public void setIsAllowedPurchase(Boolean isAllowedPurchase) {
		this.isAllowedPurchase = isAllowedPurchase;
	}
	public List<ObMaturityInstructionBean> getMaturityInstructionListing() {
		return maturityInstructionListing;
	}
	public void setMaturityInstructionListing(
			List<ObMaturityInstructionBean> maturityInstructionListing) {
		this.maturityInstructionListing = maturityInstructionListing;
	}
	public Boolean getIsSyariahProduct() {
		return isSyariahProduct;
	}
	public void setIsSyariahProduct(Boolean isSyariahProduct) {
		this.isSyariahProduct = isSyariahProduct;
	}
	public Integer getTotalAllData() {
		return totalAllData;
	}
	public void setTotalAllData(Integer totalAllData) {
		this.totalAllData = totalAllData;
	}
	public Boolean getIsRDB() {
		return isRDB;
	}
	public void setIsRDB(Boolean isRDB) {
		this.isRDB = isRDB;
	}
	public String getAutoSubsUntil() {
		return autoSubsUntil;
	}
	public void setAutoSubsUntil(String autoSubsUntil) {
		this.autoSubsUntil = autoSubsUntil;
	}
	public BigDecimal getMonthlySubs() {
		return monthlySubs;
	}
	public void setMonthlySubs(BigDecimal monthlySubs) {
		this.monthlySubs = monthlySubs;
	}
	public Boolean getWithInsurance() {
		return withInsurance;
	}
	public void setWithInsurance(Boolean withInsurance) {
		this.withInsurance = withInsurance;
	}
	public Boolean getIsAutoReedem() {
		return isAutoReedem;
	}
	public void setIsAutoReedem(Boolean isAutoReedem) {
		this.isAutoReedem = isAutoReedem;
	}
	public Integer getIsFailedAutoDebet() {
		return isFailedAutoDebet;
	}
	public void setIsFailedAutoDebet(Integer isFailedAutoDebet) {
		this.isFailedAutoDebet = isFailedAutoDebet;
	}
	public Boolean getAutoDebetStatus() {
		return autoDebetStatus;
	}
	public void setAutoDebetStatus(Boolean autoDebetStatus) {
		this.autoDebetStatus = autoDebetStatus;
	}
	public Boolean getIsRDBAllowRedeem() {
		return isRDBAllowRedeem;
	}
	public void setIsRDBAllowRedeem(Boolean isRDBAllowRedeem) {
		this.isRDBAllowRedeem = isRDBAllowRedeem;
	}
	public Boolean getIsRDBAllowSwitch() {
		return isRDBAllowSwitch;
	}
	public void setIsRDBAllowSwitch(Boolean isRDBAllowSwitch) {
		this.isRDBAllowSwitch = isRDBAllowSwitch;
	}
	
}
