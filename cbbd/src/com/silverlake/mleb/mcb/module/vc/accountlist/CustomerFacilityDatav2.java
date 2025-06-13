package com.silverlake.mleb.mcb.module.vc.accountlist;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerFacilityDatav2 implements Serializable{
	 
	private String facility_code;
	private String description;
	private String cif_no;
	private String customer_name;
	private String currency_code;
	private BigDecimal outstanding_amt;
	private BigDecimal available_amt;
	private String period_from;
	private String period_to;
	
	
	public String getFacility_code() {
		return facility_code;
	}
	public void setFacility_code(String facility_code) {
		this.facility_code = facility_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCif_no() {
		return cif_no;
	}
	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public BigDecimal getOutstanding_amt() {
		return outstanding_amt;
	}
	public void setOutstanding_amt(BigDecimal outstanding_amt) {
		this.outstanding_amt = outstanding_amt;
	}
	public BigDecimal getAvailable_amt() {
		return available_amt;
	}
	public void setAvailable_amt(BigDecimal available_amt) {
		this.available_amt = available_amt;
	}
	public String getPeriod_from() {
		return period_from;
	}
	public void setPeriod_from(String period_from) {
		this.period_from = period_from;
	}
	public String getPeriod_to() {
		return period_to;
	}
	public void setPeriod_to(String period_to) {
		this.period_to = period_to;
	}
	
	
		
	
    	    
}

