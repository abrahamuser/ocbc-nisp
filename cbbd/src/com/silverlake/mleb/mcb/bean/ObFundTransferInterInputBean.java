package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.activation.DataHandler;

public class ObFundTransferInterInputBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	
	private String accFrom;
	private String accTo;
	private String accNo;
	private String toBank;
	private String amount;
	private String description;
	private String recurringType;
	private String numOfRecurring;
	private String transferDate;
	private String email; 
	private String phoneNum;
	private String alias;
	private String amountCcy;
	private String saveBene;
	private String recurringDate;
	private String residentStatus;
	private String recurringFlag;
	private String transferService;
	private String refNo;
	private String beneCategory;
	private String beneName;
	private String isFavFlag;
	private String tranxID;
	private DataHandler benePicture;
	
	public String getAccFrom() {
		return accFrom;
	}
	public void setAccFrom(String accFrom) {
		this.accFrom = accFrom;
	}
	public String getAccTo() {
		return accTo;
	}
	public void setAccTo(String accTo) {
		this.accTo = accTo;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getToBank() {
		return toBank;
	}
	public void setToBank(String toBank) {
		this.toBank = toBank;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
	}
	public String getNumOfRecurring() {
		return numOfRecurring;
	}
	public void setNumOfRecurring(String numOfRecurring) {
		this.numOfRecurring = numOfRecurring;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public String getSaveBene() {
		return saveBene;
	}
	public void setSaveBene(String saveBene) {
		this.saveBene = saveBene;
	}
	public String getRecurringDate() {
		return recurringDate;
	}
	public void setRecurringDate(String recurringDate) {
		this.recurringDate = recurringDate;
	}
	public String getResidentStatus() {
		return residentStatus;
	}
	public void setResidentStatus(String residentStatus) {
		this.residentStatus = residentStatus;
	}
	public String getRecurringFlag() {
		return recurringFlag;
	}
	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}
	public String getTransferService() {
		return transferService;
	}
	public void setTransferService(String transferService) {
		this.transferService = transferService;
	}

	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getBeneCategory() {
		return beneCategory;
	}
	public void setBeneCategory(String beneCategory) {
		this.beneCategory = beneCategory;
	}
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public String getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(String isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	public String getTranxID() {
		return tranxID;
	}
	public void setTranxID(String tranxID) {
		this.tranxID = tranxID;
	}
	public DataHandler getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(DataHandler benePicture) {
		this.benePicture = benePicture;
	}
	
	
	

}
