package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObCustomerFacilityDataListBean implements Serializable{
	
	private String id;
	private String parentId;
	private String description;
	private String productCodeStr;
	private String groupId;
	private String groupName;
	private String sourceApplication;
	private String debtorCif;
	private String debtorName;
	private String currencyCode;
	private BigDecimal amountLimit;
	private BigDecimal outstandingAmount;
	private BigDecimal availableAmount;
	private String valueDate;
	private String dueDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductCodeStr() {
		return productCodeStr;
	}
	public void setProductCodeStr(String productCodeStr) {
		this.productCodeStr = productCodeStr;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSourceApplication() {
		return sourceApplication;
	}
	public void setSourceApplication(String sourceApplication) {
		this.sourceApplication = sourceApplication;
	}
	public String getDebtorCif() {
		return debtorCif;
	}
	public void setDebtorCif(String debtorCif) {
		this.debtorCif = debtorCif;
	}
	public String getDebtorName() {
		return debtorName;
	}
	public void setDebtorName(String debtorName) {
		this.debtorName = debtorName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getAmountLimit() {
		return amountLimit;
	}
	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
	}
	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	
}
