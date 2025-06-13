package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObForexRequest extends ObRequestCache<ObForexSessionCache> implements Serializable{

	private static final long serialVersionUID = 1508564778445364713L;
	
	private String debitAccountId;
	private String beneAccountId;
	private String debitAccountUUID;
	private String beneAccountUUID;
	private String beneAccountNo;
	private String beneAccountName;
	private String remark;
	private String purpose;
	private String deviceId;
	private String currency;
	private BigDecimal amount;
	private String amountCcy;
	private Boolean isCrossCurrency;
	private String currency2;
	private Boolean isPreValidate;
	private String action;

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDebitAccountId() {
		return debitAccountId;
	}
	public void setDebitAccountId(String debitAccountId) {
		this.debitAccountId = debitAccountId;
	}
	public String getBeneAccountId() {
		return beneAccountId;
	}
	public void setBeneAccountId(String beneAccountId) {
		this.beneAccountId = beneAccountId;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneAccountName() {
		return beneAccountName;
	}
	public void setBeneAccountName(String beneAccountName) {
		this.beneAccountName = beneAccountName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public String getDebitAccountUUID() {
		return debitAccountUUID;
	}
	public void setDebitAccountUUID(String debitAccountUUID) {
		this.debitAccountUUID = debitAccountUUID;
	}
	public String getBeneAccountUUID() {
		return beneAccountUUID;
	}
	public void setBeneAccountUUID(String beneAccountUUID) {
		this.beneAccountUUID = beneAccountUUID;
	}
	public Boolean getIsCrossCurrency() {
		return isCrossCurrency;
	}
	public String getCurrency2() {
		return currency2;
	}
	public void setIsCrossCurrency(Boolean isCrossCurrency) {
		this.isCrossCurrency = isCrossCurrency;
	}
	public void setCurrency2(String currency2) {
		this.currency2 = currency2;
	}
	public Boolean getIsPreValidate() {
		return isPreValidate;
	}
	public void setIsPreValidate(Boolean isPreValidate) {
		this.isPreValidate = isPreValidate;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
