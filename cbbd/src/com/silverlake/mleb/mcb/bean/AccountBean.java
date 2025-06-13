package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class AccountBean implements Serializable {

	private String productCode;
	private String productName;
	private String accountCcy;
	private String accountNo;
	private String balance;
	private String availableBalance;
	private String holdBalance;
	private String overdraftBalance;
	private String accountType;
	private String branchCode;
	private String mcBit;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAccountCcy() {
		return accountCcy;
	}

	public void setAccountCcy(String accountCcy) {
		this.accountCcy = accountCcy;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getHoldBalance() {
		return holdBalance;
	}

	public void setHoldBalance(String holdBalance) {
		this.holdBalance = holdBalance;
	}

	public String getOverdraftBalance() {
		return overdraftBalance;
	}

	public void setOverdraftBalance(String overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getMcBit() {
		return mcBit;
	}

	public void setMcBit(String mcBit) {
		this.mcBit = mcBit;
	}

}
