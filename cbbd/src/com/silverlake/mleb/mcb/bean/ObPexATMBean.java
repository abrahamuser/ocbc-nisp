package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObPexATMBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	private int indexAccountSelected; 
	private String contactNo;
	private String contactName;
	private String messageRemark;
	private Double pexATMAmount;
	private int collectionType;
	private String collectionCode;
	private String referencePhoneNo;
	private String transactionDate;
	private String fromAccountName;
	private String fromAccountNo;
	private String fromAccountType; 
	private String toMoblieNo;
	private Double serviceCharge;
	
	
	public String getFromAccountName() {
		return fromAccountName;
	}


	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}


	public String getFromAccountNo() {
		return fromAccountNo;
	}


	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}


	public String getFromAccountType() {
		return fromAccountType;
	}


	public void setFromAccountType(String fromAccountType) {
		this.fromAccountType = fromAccountType;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getMessageRemark() {
		return messageRemark;
	}


	public void setMessageRemark(String messageRemark) {
		this.messageRemark = messageRemark;
	}


	public Double getPexATMAmount() {
		return pexATMAmount;
	}


	public void setPexATMAmount(Double pexATMAmount) {
		this.pexATMAmount = pexATMAmount;
	}


	public int getCollectionType() {
		return collectionType;
	}


	public void setCollectionType(int collectionType) {
		this.collectionType = collectionType;
	}


	public String getCollectionCode() {
		return collectionCode;
	}


	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}


	public String getReferencePhoneNo() {
		return referencePhoneNo;
	}


	public void setReferencePhoneNo(String referencePhoneNo) {
		this.referencePhoneNo = referencePhoneNo;
	}


	public String getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getToMoblieNo() {
		return toMoblieNo;
	}


	public void setToMoblieNo(String toMoblieNo) {
		this.toMoblieNo = toMoblieNo;
	}


	public Double getServiceCharge() {
		return serviceCharge;
	}


	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}


	public int getIndexAccountSelected() {
		return indexAccountSelected;
	}


	public void setIndexAccountSelected(int indexAccountSelected) {
		this.indexAccountSelected = indexAccountSelected;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
