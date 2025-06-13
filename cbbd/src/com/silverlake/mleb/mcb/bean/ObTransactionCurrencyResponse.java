package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTransactionCurrencyResponse extends ObResponseCache implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<String> currencyList;

	public List<String> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<String> currencyList) {
		this.currencyList = currencyList;
	}
}
