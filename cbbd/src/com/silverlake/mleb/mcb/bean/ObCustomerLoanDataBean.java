package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ObCustomerLoanDataBean implements Serializable{
	 
	private String groupCode;
	private String groupName;
	private String loanAcctName;
	private String loanCustomerNumber;
	private BigDecimal amountLimit;
	private BigDecimal outstandingAmount;
	private BigDecimal availableAmount;
	private String currencyCode;
	private String lastUpdate;
	
    List<ObCustomerFacilityDataListBean> facilityList;
    

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLoanAcctName() {
		return loanAcctName;
	}

	public void setLoanAcctName(String loanAcctName) {
		this.loanAcctName = loanAcctName;
	}

	public String getLoanCustomerNumber() {
		return loanCustomerNumber;
	}

	public void setLoanCustomerNumber(String loanCustomerNumber) {
		this.loanCustomerNumber = loanCustomerNumber;
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<ObCustomerFacilityDataListBean> getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(List<ObCustomerFacilityDataListBean> facilityList) {
		this.facilityList = facilityList;
	}
	
	
    
	    
}

