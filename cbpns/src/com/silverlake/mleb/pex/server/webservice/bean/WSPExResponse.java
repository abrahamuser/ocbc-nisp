
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;



public class WSPExResponse extends WSResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    
   
    private String cif;
    private String pexReferenceNo;
    private String transactionStatus;
    private String reasonFailureCode;
    private String reasonFailure;
    private String transactionDate;
    private WSAmount amount;
    private String mobileNumber;
    private WSBeneficiaryDetails beneficiaryDetails;
    private String fromAccountNo;
    private String fromProductTypeCode;
    private String fromCurrencyCode;



	public String getFromAccountNo() {
		return fromAccountNo;
	}


	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}


	public String getFromProductTypeCode() {
		return fromProductTypeCode;
	}


	public void setFromProductTypeCode(String fromProductTypeCode) {
		this.fromProductTypeCode = fromProductTypeCode;
	}


	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}


	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}


	public WSBeneficiaryDetails getBeneficiaryDetails() {
		return beneficiaryDetails;
	}


	public void setBeneficiaryDetails(WSBeneficiaryDetails beneficiaryDetails) {
		this.beneficiaryDetails = beneficiaryDetails;
	}


	public String getPexReferenceNo() {
		return pexReferenceNo;
	}


	public void setPexReferenceNo(String pexReferenceNo) {
		this.pexReferenceNo = pexReferenceNo;
	}


	public String getTransactionStatus() {
		return transactionStatus;
	}


	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


	public String getReasonFailure() {
		return reasonFailure;
	}


	public void setReasonFailure(String reasonFailure) {
		this.reasonFailure = reasonFailure;
	}


	public String getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}


	public WSAmount getAmount() {
		return amount;
	}


	public void setAmount(WSAmount amount) {
		this.amount = amount;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public String getReasonFailureCode() {
		return reasonFailureCode;
	}


	public void setReasonFailureCode(String reasonFailureCode) {
		this.reasonFailureCode = reasonFailureCode;
	}


	
    

}

