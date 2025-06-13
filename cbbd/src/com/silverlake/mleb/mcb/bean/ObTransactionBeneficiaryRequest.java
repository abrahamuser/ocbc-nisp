package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTransactionBeneficiaryRequest extends ObRequestCache<ObTransactionBeneficiarySessionCache> implements Serializable{
	private String transferServiceType;
	
	private String accountNo;
	private String accountCcy;
	
	private String debitAccountNo;
	private String bankCode;
	private String bankName;
	private Boolean isInquiry;
	
	private String productCode;
	
	private String proxy_data;
	private String proxy_type;

	public Boolean getIsInquiry() {
		return isInquiry;
	}

	public void setIsInquiry(Boolean isInquiry) {
		this.isInquiry = isInquiry;
	}

	public String getTransferServiceType() {
		return transferServiceType;
	}

	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
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

	public String getDebitAccountNo() {
		return debitAccountNo;
	}

	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProxy_data() {
		return proxy_data;
	}

	public void setProxy_data(String proxy_data) {
		this.proxy_data = proxy_data;
	}

	public String getProxy_type() {
		return proxy_type;
	}

	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	
}
