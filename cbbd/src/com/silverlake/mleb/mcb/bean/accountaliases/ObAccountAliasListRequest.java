package com.silverlake.mleb.mcb.bean.accountaliases;

import java.io.Serializable;


import com.silverlake.mleb.mcb.bean.ObRequestCache;

public class ObAccountAliasListRequest extends ObRequestCache<ObAccountAliasListSessionCache> implements Serializable {

     
	private String accountNumber;
    private String accountName;
    private String aliasName;
    private String pageNo;
    private String pageSize;
    private String accountId;
    private String currencyCode;
    
    
    public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
              
    
}
