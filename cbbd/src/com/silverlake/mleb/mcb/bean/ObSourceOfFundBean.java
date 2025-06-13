package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class ObSourceOfFundBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal availableBalance;
	private String accountBranch;
	private String accountId;
	private String accountNumber;
	private String accountType;
	private String currencyCode;
	private String ProductName;
	private String mcBit;

	// The 4 params below were introduce in ONATandaHoldStep1Service
	private String purposeCode;
	private String purposeDescription;
	private String sourceOfFundCode;
	private String sourceOfFundDescription;
	
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getMcBit() {
		return mcBit;
	}
	public void setMcBit(String mcBit) {
		this.mcBit = mcBit;
	}

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public String getSourceOfFundCode() {
		return sourceOfFundCode;
	}

	public void setSourceOfFundCode(String sourceOfFundCode) {
		this.sourceOfFundCode = sourceOfFundCode;
	}

    public String getPurposeDescription() {
        return purposeDescription;
    }

    public void setPurposeDescription(String purposeDescription) {
        this.purposeDescription = purposeDescription;
    }

    public String getSourceOfFundDescription() {
        return sourceOfFundDescription;
    }

    public void setSourceOfFundDescription(String sourceOfFundDescription) {
        this.sourceOfFundDescription = sourceOfFundDescription;
    }
}
