package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObASLoanBean extends ObAccountBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//----- new as loan category ------------
	private String vehicleRegNo; 
	private BigDecimal loanAmount; 
	private BigDecimal releaseAmount;
	private BigDecimal outstandingBalance;
	private BigDecimal advancePayment;
	private String maturityDate;
	private BigDecimal installmentAmount; 
	private BigDecimal installmentArrears;
	private BigDecimal interestArrears; 
	private BigDecimal lateCharges;
	private String lastPaymentDate; 
	private BigDecimal lastPaymentAmount; 	
	private BigDecimal totalPaymentDue; 
	private String paymentDueDate; 
	private BigDecimal interestRate;
	private BigDecimal accuredInterest;
	private BigDecimal nextPaymentAmount;
	//---------------------------------------
	

	private int indexAccountSelected; 
	private String accountType;
	private String references;
	private String references3;
	private String referencesCombine; 
	private Double availableBal; 
	private Double currentBal; 	
	private int transactionPeriod; 
	private BigDecimal transactionAmount;
	private String transactionDescription; 
	private String transactonRef;
	private String tranActualDate; 
	private String chequeNumber; 
	private String indexCheck;
	
	private BigDecimal creditAmount;
	private BigDecimal debitAmount;
	private BigDecimal outstandingRunningBalance;
	private String description;
	


	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public BigDecimal getOutstandingRunningBalance() {
		return outstandingRunningBalance;
	}

	public void setOutstandingRunningBalance(BigDecimal outstandingRunningBalance) {
		this.outstandingRunningBalance = outstandingRunningBalance;
	}

	public String getIndexCheck() {
		return indexCheck;
	}

	public void setIndexCheck(String indexCheck) {
		this.indexCheck = indexCheck;
	}

	public String getTranActualDate() {
		return tranActualDate;
	}

	public void setTranActualDate(String tranActualDate) {
		this.tranActualDate = tranActualDate;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getTransactonRef() {
		return transactonRef;
	}

	public void setTransactonRef(String transactonRef) {
		this.transactonRef = transactonRef;
	}

	public int getTransactionPeriod() {
		return transactionPeriod;
	}

	public void setTransactionPeriod(int transactionPeriod) {
		this.transactionPeriod = transactionPeriod;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
	}

	public String getReferences3() {
		return references3;
	}

	public void setReferences3(String references3) {
		this.references3 = references3;
	}

	public String getReferencesCombine() {
		return referencesCombine;
	}

	public void setReferencesCombine(String referencesCombine) {
		this.referencesCombine = referencesCombine;
	}

	public Double getAvailableBal() {
		return availableBal;
	}

	public void setAvailableBal(Double availableBal) {
		this.availableBal = availableBal;
	}

	public Double getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(Double currentBal) {
		this.currentBal = currentBal;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getInterestArrears() {
		return interestArrears;
	}

	public void setInterestArrears(BigDecimal interestArrears) {
		this.interestArrears = interestArrears;
	}

	public BigDecimal getReleaseAmount() {
		return releaseAmount;
	}

	public void setReleaseAmount(BigDecimal releaseAmount) {
		this.releaseAmount = releaseAmount;
	}

	public BigDecimal getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(BigDecimal outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public BigDecimal getAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(BigDecimal advancePayment) {
		this.advancePayment = advancePayment;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public BigDecimal getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(BigDecimal installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public BigDecimal getInstallmentArrears() {
		return installmentArrears;
	}

	public void setInstallmentArrears(BigDecimal installmentArrears) {
		this.installmentArrears = installmentArrears;
	}

	public BigDecimal getLateCharges() {
		return lateCharges;
	}

	public void setLateCharges(BigDecimal lateCharges) {
		this.lateCharges = lateCharges;
	}

	public BigDecimal getLastPaymentAmount() {
		return lastPaymentAmount;
	}

	public void setLastPaymentAmount(BigDecimal lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}

	public String getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public BigDecimal getTotalPaymentDue() {
		return totalPaymentDue;
	}

	public void setTotalPaymentDue(BigDecimal totalPaymentDue) {
		this.totalPaymentDue = totalPaymentDue;
	}

	public String getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(String paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public int getIndexAccountSelected() {
		return indexAccountSelected;
	}

	public void setIndexAccountSelected(int indexAccountSelected) {
		this.indexAccountSelected = indexAccountSelected;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getVehicleRegNo() {
		return vehicleRegNo;
	}

	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getAccuredInterest() {
		return accuredInterest;
	}

	public void setAccuredInterest(BigDecimal accuredInterest) {
		this.accuredInterest = accuredInterest;
	}

	public BigDecimal getNextPaymentAmount() {
		return nextPaymentAmount;
	}

	public void setNextPaymentAmount(BigDecimal nextPaymentAmount) {
		this.nextPaymentAmount = nextPaymentAmount;
	}

	

	
}

