package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObFundTransferTransactionDetailsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String amount;
	private String amountCcy;
	private String remark;
	private String email; 
	private String telephone;
	private String alias;
	private String transferDate;
	private Boolean saveBeneFlag;
	private String refNo;
	private String beneName;
	private String isFavFlag;
	private DataHandler benePicture;
	private String accCurrency;
	private String transactionUUID;
	
	//only use in intrabank
	private String transferPurpose; 
	//only use in interbank
	private String residentStatus;
	private String beneCategory;
	
	
	//interback service type
	private String transferService;
	
	//recurring
	private String interval;
	private String recurrStartDate;
	private String recurringFlag;
	private String recurTimes;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	public Boolean getSaveBeneFlag() {
		return saveBeneFlag;
	}
	public void setSaveBeneFlag(Boolean saveBeneFlag) {
		this.saveBeneFlag = saveBeneFlag;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
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
	public DataHandler getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(DataHandler benePicture) {
		this.benePicture = benePicture;
	}
	public String getTransferPurpose() {
		return transferPurpose;
	}
	public void setTransferPurpose(String transferPurpose) {
		this.transferPurpose = transferPurpose;
	}
	public String getAccCurrency() {
		return accCurrency;
	}
	public void setAccCurrency(String accCurrency) {
		this.accCurrency = accCurrency;
	}
	public String getTransactionUUID() {
		return transactionUUID;
	}
	public void setTransactionUUID(String transactionUUID) {
		this.transactionUUID = transactionUUID;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getRecurrStartDate() {
		return recurrStartDate;
	}
	public void setRecurrStartDate(String recurrStartDate) {
		this.recurrStartDate = recurrStartDate;
	}
	public String getRecurringFlag() {
		return recurringFlag;
	}
	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}
	public String getRecurTimes() {
		return recurTimes;
	}
	public void setRecurTimes(String recurTimes) {
		this.recurTimes = recurTimes;
	}
	public String getTransferService() {
		return transferService;
	}
	public void setTransferService(String transferService) {
		this.transferService = transferService;
	}
	public String getResidentStatus() {
		return residentStatus;
	}
	public void setResidentStatus(String residentStatus) {
		this.residentStatus = residentStatus;
	}
	public String getBeneCategory() {
		return beneCategory;
	}
	public void setBeneCategory(String beneCategory) {
		this.beneCategory = beneCategory;
	}
	
	
	
}
