package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class AmountSetup  implements Serializable
{
	  private String ccy_code;
	  private Integer amount;
	  private String operator;
	  
	  
	public String getCcy_code() {
		return ccy_code;
	}
	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	 
	 
}



	
