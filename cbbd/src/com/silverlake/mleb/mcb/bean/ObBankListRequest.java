package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObBankListRequest extends ObRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String transferServiceType;
	private String countryCode;
	private String bankCode;
	private String branchName;
	private String networkClearingCode;

	public String getTransferServiceType() {
		return transferServiceType;
	}

	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getNetworkClearingCode() {
		return networkClearingCode;
	}

	public void setNetworkClearingCode(String networkClearingCode) {
		this.networkClearingCode = networkClearingCode;
	}

	
}