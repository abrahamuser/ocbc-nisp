package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositCalculateInterestRequest extends ObRequest implements Serializable {

	private static final long serialVersionUID = -4634086211368616634L;
	
	String maturity;
	String currency;
	String tenor;
	
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
	
	
}
