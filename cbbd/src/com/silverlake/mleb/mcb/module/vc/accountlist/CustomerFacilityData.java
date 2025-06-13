package com.silverlake.mleb.mcb.module.vc.accountlist;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerFacilityData implements Serializable{
	 
	private String id;
	private String parent_id;
	private String description;
	private String product_code_str;
	private String group_id;
	private String group_name;
	private String source_application;
	private String debtor_cif;
	private String debtor_name;
	private String currency_code;
	private BigDecimal amount_limit;
	private BigDecimal outstanding_amt;
	private BigDecimal available_amt;
	private String value_date;
	private String due_date;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProduct_code_str() {
		return product_code_str;
	}
	public void setProduct_code_str(String product_code_str) {
		this.product_code_str = product_code_str;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getSource_application() {
		return source_application;
	}
	public void setSource_application(String source_application) {
		this.source_application = source_application;
	}
	public String getDebtor_cif() {
		return debtor_cif;
	}
	public void setDebtor_cif(String debtor_cif) {
		this.debtor_cif = debtor_cif;
	}
	public String getDebtor_name() {
		return debtor_name;
	}
	public void setDebtor_name(String debtor_name) {
		this.debtor_name = debtor_name;
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
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
	
    	    
}

