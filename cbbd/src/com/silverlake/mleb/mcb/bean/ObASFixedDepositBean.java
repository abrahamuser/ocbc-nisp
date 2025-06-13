package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObASFixedDepositBean extends ObAccountBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//-----new as FD category ------
	private BigDecimal principalAmount; 
	private String renewalInstruction;  //profit crediting 
	private String tenure;
	private String tenureCode;
	private String placementDate; 	
	private String maturityDate;
	private BigDecimal rate;  
	private String referenceNo;
	private Boolean stepUp;
	private String interestDispositionCode; 
	private String interestPaymentMethod;
	private String stepUpMessage;
	//------------------------------
	//vn extra
	private String receiptSerialNumber;
	private String receiptStatus;
	private String lastRenewalDate;
	private String interestPaymentAccount;
	private BigDecimal accruedInterest;
	private String autoRenewal;
	private String lienStatus;
	
	
	
	
	
	//private String accountIndex;

	

	public Boolean getStepUp() {
		return stepUp;
	}

	public void setStepUp(Boolean stepUp) {
		this.stepUp = stepUp;
	}

	public String getPlacementDate() {
		return placementDate;
	}

	public void setPlacementDate(String placementDate) {
		this.placementDate = placementDate;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}


	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	public String getRenewalInstruction() {
		return renewalInstruction;
	}


	public void setRenewalInstruction(String renewalInstruction) {
		this.renewalInstruction = renewalInstruction;
	}


	public String getTenure() {
		return tenure;
	}


	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*public String getAccountIndex() {
		return accountIndex;
	}

	public void setAccountIndex(String accountIndex) {
		this.accountIndex = accountIndex;
	}*/

	public String getTenureCode() {
		return tenureCode;
	}

	public void setTenureCode(String tenureCode) {
		this.tenureCode = tenureCode;
	}

	public String getInterestDispositionCode() {
		return interestDispositionCode;
	}

	public void setInterestDispositionCode(String interestDispositionCode) {
		this.interestDispositionCode = interestDispositionCode;
	}

	public String getInterestPaymentMethod() {
		return interestPaymentMethod;
	}

	public void setInterestPaymentMethod(String interestPaymentMethod) {
		this.interestPaymentMethod = interestPaymentMethod;
	}

	public String getStepUpMessage() {
		return stepUpMessage;
	}

	public void setStepUpMessage(String stepUpMessage) {
		this.stepUpMessage = stepUpMessage;
	}

	public String getReceiptSerialNumber() {
		return receiptSerialNumber;
	}

	public void setReceiptSerialNumber(String receiptSerialNumber) {
		this.receiptSerialNumber = receiptSerialNumber;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getLastRenewalDate() {
		return lastRenewalDate;
	}

	public void setLastRenewalDate(String lastRenewalDate) {
		this.lastRenewalDate = lastRenewalDate;
	}

	public String getInterestPaymentAccount() {
		return interestPaymentAccount;
	}

	public void setInterestPaymentAccount(String interestPaymentAccount) {
		this.interestPaymentAccount = interestPaymentAccount;
	}

	public BigDecimal getAccruedInterest() {
		return accruedInterest;
	}

	public void setAccruedInterest(BigDecimal accruedInterest) {
		this.accruedInterest = accruedInterest;
	}

	public String getAutoRenewal() {
		return autoRenewal;
	}

	public void setAutoRenewal(String autoRenewal) {
		this.autoRenewal = autoRenewal;
	}

	public String getLienStatus() {
		return lienStatus;
	}

	public void setLienStatus(String lienStatus) {
		this.lienStatus = lienStatus;
	}

	
	
	

}
