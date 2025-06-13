package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Date;

public class ObBranchBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String branchCode;
	private String branchName;
	private String branchNameLocale1;
	private String bankCode;
	private String provinceCode;
	private Date updatedDate;
	private String isOnTop;
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchNameLocale1() {
		return branchNameLocale1;
	}
	public void setBranchNameLocale1(String branchNameLocale1) {
		this.branchNameLocale1 = branchNameLocale1;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getIsOnTop() {
		return isOnTop;
	}
	public void setIsOnTop(String isOnTop) {
		this.isOnTop = isOnTop;
	}
	
}
