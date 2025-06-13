package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObCurrencyCodeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> listCurrencyCode;
	private String currencyCode;

	public List<String> getListCurrencyCode() {
		return listCurrencyCode;
	}

	public void setListCurrencyCode(List<String> listCurrencyCode) {
		this.listCurrencyCode = listCurrencyCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	

}
