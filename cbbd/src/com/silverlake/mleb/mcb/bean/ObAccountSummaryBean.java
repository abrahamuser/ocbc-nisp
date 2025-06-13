package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class ObAccountSummaryBean implements Serializable{

	private static final long serialVersionUID = 1L;


	private String accountIndex;

	//acc summary
	private String accountDescription;
	private String accountName;
	private String accountNumber;
	private String currencyCode;
	private String productTypeCode;
	private boolean allowDisplay;
	private boolean allowTransactionFrom;
	private boolean allowTransactionTo;
	private boolean isIslamic;
	private boolean isMach;
	
	

	private String productCode;
	private String productStatusCode;	
	private BigDecimal availableBalance;
	private BigDecimal currentBalance;
	private BigDecimal outStandingBalance;
	private Double repaymentAmount; 
	private Double totalPlacement; 
	private String currentDate; 
	private String dueDate; 
	
	
	//account summary detail
	private String transactionName; 
	private String transactionValue;
	private String transactionDate; 
	private String accountType; 
	
	//loan list
//	private String vehical; 
//	private BigDecimal releaseAmount; 
//	private BigDecimal outStandingBalance;
//	private BigDecimal advancePayment;
//	private XMLGregorianCalendar maturityDate;
//	private BigDecimal installmentAmount;
//	private BigDecimal installmentInArrears;
//	private BigDecimal interestInArrears;
//	private BigDecimal lateCharge;
//	private BigDecimal lastPaymentAmount;
//	private XMLGregorianCalendar lastPaymentDate; 
//	private BigDecimal totalAmountDue;
	
	
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
//	public String getVehical() {
//		return vehical;
//	}
//	public void setVehical(String vehical) {
//		this.vehical = vehical;
//	}
//	public BigDecimal getReleaseAmount() {
//		return releaseAmount;
//	}
//	public void setReleaseAmount(BigDecimal releaseAmount) {
//		this.releaseAmount = releaseAmount;
//	}
	public BigDecimal getOutStandingBalance() {
		return outStandingBalance;
	}
	public void setOutStandingBalance(BigDecimal outStandingBalance) {
		this.outStandingBalance = outStandingBalance;
	}
//	public BigDecimal getAdvancePayment() {
//		return advancePayment;
//	}
//	public void setAdvancePayment(BigDecimal advancePayment) {
//		this.advancePayment = advancePayment;
//	}
//	public XMLGregorianCalendar getMaturityDate() {
//		return maturityDate;
//	}
//	public void setMaturityDate(XMLGregorianCalendar maturityDate) {
//		this.maturityDate = maturityDate;
//	}
//	public BigDecimal getInstallmentAmount() {
//		return installmentAmount;
//	}
//	public void setInstallmentAmount(BigDecimal installmentAmount) {
//		this.installmentAmount = installmentAmount;
//	}
//	public BigDecimal getInstallmentInArrears() {
//		return installmentInArrears;
//	}
//	public void setInstallmentInArrears(BigDecimal installmentInArrears) {
//		this.installmentInArrears = installmentInArrears;
//	}
//	public BigDecimal getInterestInArrears() {
//		return interestInArrears;
//	}
//	public void setInterestInArrears(BigDecimal interestInArrears) {
//		this.interestInArrears = interestInArrears;
//	}
//	public BigDecimal getLateCharge() {
//		return lateCharge;
//	}
//	public void setLateCharge(BigDecimal lateCharge) {
//		this.lateCharge = lateCharge;
//	}
//	public BigDecimal getLastPaymentAmount() {
//		return lastPaymentAmount;
//	}
//	public void setLastPaymentAmount(BigDecimal lastPaymentAmount) {
//		this.lastPaymentAmount = lastPaymentAmount;
//	}
//	public XMLGregorianCalendar getLastPaymentDate() {
//		return lastPaymentDate;
//	}
//	public void setLastPaymentDate(XMLGregorianCalendar lastPaymentDate) {
//		this.lastPaymentDate = lastPaymentDate;
//	}
//	public BigDecimal getTotalAmountDue() {
//		return totalAmountDue;
//	}
//	public void setTotalAmountDue(BigDecimal totalAmountDue) {
//		this.totalAmountDue = totalAmountDue;
//	}
	
	
	
	
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
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public String getTransactionValue() {
		return transactionValue;
	}
	public void setTransactionValue(String transactionValue) {
		this.transactionValue = transactionValue;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
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
	public Double getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(Double repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public Double getTotalPlacement() {
		return totalPlacement;
	}
	public void setTotalPlacement(Double totalPlacement) {
		this.totalPlacement = totalPlacement;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAccountIndex() {
		return accountIndex;
	}
	public void setAccountIndex(String accountIndex) {
		this.accountIndex = accountIndex;
	}
	
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
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getProductTypeCode() {
		return productTypeCode;
	}
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
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
	


}
