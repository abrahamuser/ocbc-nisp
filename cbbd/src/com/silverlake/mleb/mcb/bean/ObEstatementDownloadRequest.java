package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObEstatementDownloadRequest extends ObRequest implements Serializable {

	private static final long serialVersionUID = -7869165473408651726L;
	
	private String accountNo;
	private String accountType;
	private String year;
	private String month;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	

}
