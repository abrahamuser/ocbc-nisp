package com.silverlake.mleb.mcb.module.vc.accountlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CustomerLoanData implements Serializable{
	 
	private String group_cd;
	private String group_name;
	private String loan_acct_name;
	private String loan_cust_number;
	private BigDecimal amount_limit;
	private BigDecimal outstanding_amt;
	private BigDecimal available_amt;
	private String currency_code;
	private String last_update;
	
    List<CustomerFacilityData> facility_list;

	public String getGroup_cd() {
		return group_cd;
	}

	public void setGroup_cd(String group_cd) {
		this.group_cd = group_cd;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getLoan_acct_name() {
		return loan_acct_name;
	}

	public void setLoan_acct_name(String loan_acct_name) {
		this.loan_acct_name = loan_acct_name;
	}

	public String getLoan_cust_number() {
		return loan_cust_number;
	}

	public void setLoan_cust_number(String loan_cust_number) {
		this.loan_cust_number = loan_cust_number;
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

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public List<CustomerFacilityData> getFacility_list() {
		return facility_list;
	}

	public void setFacility_list(List<CustomerFacilityData> facility_list) {
		this.facility_list = facility_list;
	}
    
    
       
}

