
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;
import java.math.BigDecimal;



public class WSAmount 
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    private BigDecimal amount;
    private String currency;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
    
    
    

}
