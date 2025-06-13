package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseDataList implements Serializable{
	 
	private String bill_reff;
	private BigDecimal bill_amount;
    private String bill_caption;
    
    private String optionId;
    
	public String getBill_reff() {
		return bill_reff;
	}
	public void setBill_reff(String bill_reff) {
		this.bill_reff = bill_reff;
	}
	public BigDecimal getBill_amount() {
		return bill_amount;
	}
	public void setBill_amount(BigDecimal bill_amount) {
		this.bill_amount = bill_amount;
	}
	public String getBill_caption() {
		return bill_caption;
	}
	public void setBill_caption(String bill_caption) {
		this.bill_caption = bill_caption;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
    
    
	    
}

