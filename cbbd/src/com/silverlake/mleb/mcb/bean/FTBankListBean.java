package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class FTBankListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bankId;
	private String bankCode;
	private String bankName;
	private String transferService;
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
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
	public String getTransferService() {
		return transferService;
	}
	public void setTransferService(String transferService) {
		this.transferService = transferService;
	}
	
	
}

