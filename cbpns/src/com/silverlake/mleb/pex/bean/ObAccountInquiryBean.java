package com.silverlake.mleb.pex.bean;

import java.io.Serializable;




public class ObAccountInquiryBean extends ObEAIHeader implements Serializable
{

	
	String accNumber;
	String accName;
	String accType;
	String currencyType;
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	
	
	
	
	
}
