package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTransactionSourceOfFundRequest extends ObRequestCache<ObTransactionSourceOfFundSessionCache> implements Serializable{
	private String transferServiceType;
	private String currencyCode;
	private Boolean isSourceOfFund;
	private String accountNo;

	public String getTransferServiceType() {
		return transferServiceType;
	}

	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Boolean getIsSourceOfFund() {
		return isSourceOfFund;
	}

	public void setIsSourceOfFund(Boolean isSourceOfFund) {
		this.isSourceOfFund = isSourceOfFund;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	
	
	
}
