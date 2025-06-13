package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAutoRenewalRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1434658768674979996L;

	private String userCif;
	private String userName;
	private String accountNo;
	private String accountCcy;
	private String index;
	private String accountType;
	private String productCode;
	private String productName;
	private String enableAutoRenewal;
	public String getUserCif() {
		return userCif;
	}
	public void setUserCif(String userCif) {
		this.userCif = userCif;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountCcy() {
		return accountCcy;
	}
	public void setAccountCcy(String accountCcy) {
		this.accountCcy = accountCcy;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
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
	public String getEnableAutoRenewal() {
		return enableAutoRenewal;
	}
	public void setEnableAutoRenewal(String enableAutoRenewal) {
		this.enableAutoRenewal = enableAutoRenewal;
	}

	

}