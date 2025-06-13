package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.util.List;

public class ObSubBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String bankCode;
	private String transferService;
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getTransferService() {
		return transferService;
	}
	public void setTransferService(String transferService) {
		this.transferService = transferService;
	}
	
}
