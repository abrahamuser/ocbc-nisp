package com.silverlake.mleb.mcb.module.vc.accountlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CustomerLoanDatav2 implements Serializable{
	 
	private String agreement_id;
	private String document_id;
	private String currency_code;
	private BigDecimal amount_limit;
	private BigDecimal outstanding_amt;
	private BigDecimal available_amt;
	private String period_from;
	private String period_to;
	private List<CustomerFacilityDatav2> facility_list;

	public String getAgreement_id() {
		return agreement_id;
	}

	public void setAgreement_id(String agreement_id) {
		this.agreement_id = agreement_id;
	}

	public String getDocument_id() {
		return document_id;
	}

	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public BigDecimal getAmount_limit() {
		return amount_limit;
	}

	public void setAmount_limit(BigDecimal amount_limit) {
		this.amount_limit = amount_limit;
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

	public List<CustomerFacilityDatav2> getFacility_list() {
		return facility_list;
	}

	public void setFacility_list(List<CustomerFacilityDatav2> facility_list) {
		this.facility_list = facility_list;
	}

	
	    
       
}

