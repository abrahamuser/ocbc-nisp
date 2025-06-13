
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;



public class WSPExRequest extends WSRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    private String collectionCode;
    private String mobileNumber;
    private String pin;
    private String collectionType;
    private WSAmount amount;
    private String cardNoIBFT;
    private WSBeneficiaryDetails beneficiaryDetails;
    
    
    
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public WSAmount getAmount() {
		return amount;
	}
	public void setAmount(WSAmount amount) {
		this.amount = amount;
	}
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public WSBeneficiaryDetails getBeneficiaryDetails() {
		return beneficiaryDetails;
	}
	public void setBeneficiaryDetails(WSBeneficiaryDetails beneficiaryDetails) {
		this.beneficiaryDetails = beneficiaryDetails;
	}
	public String getCardNoIBFT() {
		return cardNoIBFT;
	}
	public void setCardNoIBFT(String cardNoIBFT) {
		this.cardNoIBFT = cardNoIBFT;
	}
    

    
    
}
