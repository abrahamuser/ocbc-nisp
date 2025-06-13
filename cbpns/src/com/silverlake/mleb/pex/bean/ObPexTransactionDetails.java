package com.silverlake.mleb.pex.bean;

import java.io.Serializable;

import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObRequest;


public class ObPexTransactionDetails extends ObRequest implements Serializable
{
	private String indexID;
	private String status;
	private String errorMessage;
	private String datetime;
	private String collectDate;
	private String cancelDate;
	private String failedDate;
	private String referenceNumber;
	private ObAccountBean fromAccount;
	private ObAccountBean collectionAccount;
	private String payeeMsisdn;
	private String payeeName;
	private String serviceCharge;
	private String collectionCode;
	private String collectionType;
	private String collectionName;
	private String pin;
	private String remark;
	private String amount;
	private String currency;
	private String notice;
	
	private boolean isInternetCollection;
	private boolean isAtmCollection;
	private boolean isPExDirect;
	private String senderName;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public ObAccountBean getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(ObAccountBean fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getPayeeMsisdn() {
		return payeeMsisdn;
	}
	public void setPayeeMsisdn(String payeeMsisdn) {
		this.payeeMsisdn = payeeMsisdn;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public boolean isInternetCollection() {
		return isInternetCollection;
	}
	public void setInternetCollection(boolean isInternetCollection) {
		this.isInternetCollection = isInternetCollection;
	}
	public boolean isAtmCollection() {
		return isAtmCollection;
	}
	public void setAtmCollection(boolean isAtmCollection) {
		this.isAtmCollection = isAtmCollection;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public ObAccountBean getCollectionAccount() {
		return collectionAccount;
	}
	public void setCollectionAccount(ObAccountBean collectionAccount) {
		this.collectionAccount = collectionAccount;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public boolean isPExDirect() {
		return isPExDirect;
	}
	public void setPExDirect(boolean isPExDirect) {
		this.isPExDirect = isPExDirect;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getIndexID() {
		return indexID;
	}
	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getFailedDate() {
		return failedDate;
	}
	public void setFailedDate(String failedDate) {
		this.failedDate = failedDate;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}

	
	
}
