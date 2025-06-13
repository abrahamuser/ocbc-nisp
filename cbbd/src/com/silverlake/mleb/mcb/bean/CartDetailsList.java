package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartDetailsList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String amountCcy;
	private BigDecimal amount;
	
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
