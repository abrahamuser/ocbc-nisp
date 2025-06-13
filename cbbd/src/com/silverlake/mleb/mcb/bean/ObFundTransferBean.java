package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;



public class ObFundTransferBean extends ObAccountBean implements Serializable
{
	
	//general use 
	private Boolean isSelected;  
	private String fromAccount;  
	private String toAccount;
	private BigDecimal payAmount;
	private String referenceNumber; 
//	private XMLGregorianCalendar transactionDateTime;
	private String transDescription;
	private String transStatus;
	private BigDecimal accountBalance; 
	private String fromCurrencyCode;
	private String toCurrencyCode; 
	private String fromProductTypeCode; 
	private String toProductTypeCode;
	//private String accountTypeCode; 
	private String typeCode;
	private String payeeUuid; 
	private String fromAccDesc; 
	private String toAccDesc; 
	private String transTypeCode;
	//specific for fund transfer pay (favorite acc)
	private String receivingBank;
	private String receivingAccNo;
	private String receivingName;
	private String receivingID;
	private BigDecimal serviceCharge; 
	private int indexFavAccSelected; 
	private BigDecimal balAftTrans;
	
	
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getTransDescription() {
		return transDescription;
	}
	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}
	public String getFromAccDesc() {
		return fromAccDesc;
	}
	public void setFromAccDesc(String fromAccDesc) {
		this.fromAccDesc = fromAccDesc;
	}
	public String getToAccDesc() {
		return toAccDesc;
	}
	public void setToAccDesc(String toAccDesc) {
		this.toAccDesc = toAccDesc;
	}
	/*public String getAccountTypeCode() {
		return accountTypeCode;
	}
	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}*/
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getPayeeUuid() {
		return payeeUuid;
	}
	public void setPayeeUuid(String payeeUuid) {
		this.payeeUuid = payeeUuid;
	}
//	public XMLGregorianCalendar getTransactionDateTime() {
//		return transactionDateTime;
//	}
//	public void setTransactionDateTime(XMLGregorianCalendar transactionDateTime) {
//		this.transactionDateTime = transactionDateTime;
//	}
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getReceivingBank() {
		return receivingBank;
	}
	public void setReceivingBank(String receivingBank) {
		this.receivingBank = receivingBank;
	}
	public String getReceivingAccNo() {
		return receivingAccNo;
	}
	public void setReceivingAccNo(String receivingAccNo) {
		this.receivingAccNo = receivingAccNo;
	}
	public String getReceivingName() {
		return receivingName;
	}
	public void setReceivingName(String receivingName) {
		this.receivingName = receivingName;
	}
	public int getIndexFavAccSelected() {
		return indexFavAccSelected;
	}
	public void setIndexFavAccSelected(int indexFavAccSelected) {
		this.indexFavAccSelected = indexFavAccSelected;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}
	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}
	public String getToCurrencyCode() {
		return toCurrencyCode;
	}
	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}
	public String getFromProductTypeCode() {
		return fromProductTypeCode;
	}
	public void setFromProductTypeCode(String fromProductTypeCode) {
		this.fromProductTypeCode = fromProductTypeCode;
	}
	public String getToProductTypeCode() {
		return toProductTypeCode;
	}
	public void setToProductTypeCode(String toProductTypeCode) {
		this.toProductTypeCode = toProductTypeCode;
	}
	public String getTransTypeCode() {
		return transTypeCode;
	}
	public void setTransTypeCode(String transTypeCode) {
		this.transTypeCode = transTypeCode;
	}
	public String getReceivingID() {
		return receivingID;
	}
	public void setReceivingID(String receivingID) {
		this.receivingID = receivingID;
	}
	public BigDecimal getBalAftTrans() {
		return balAftTrans;
	}
	public void setBalAftTrans(BigDecimal balAftTrans) {
		this.balAftTrans = balAftTrans;
	} 
	

	
	
}