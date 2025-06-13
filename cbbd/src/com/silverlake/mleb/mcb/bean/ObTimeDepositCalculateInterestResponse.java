package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ObTimeDepositCalculateInterestResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = -363416872564715476L;

	private String maturity;
	private String currency;
	private String tenor;
	
//	Map<String, String> listInterest;
	
	private ListMapPojo listInterestPayment;

	public String getMaturity() {
		return maturity;
	}

	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	public ListMapPojo getListInterestPayment() {
		return listInterestPayment;
	}

	public void setListInterestPayment(ListMapPojo listInterestPayment) {
		this.listInterestPayment = listInterestPayment;
	}


}
