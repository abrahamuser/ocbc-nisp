package com.silverlake.mleb.mcb.module.vc.accountlist;

import java.io.Serializable;
import java.math.BigDecimal;

public class BancassuranceData implements Serializable{
	 
	private String spaj_no;
	private String policy_no;
	private String product_code;
	private String product_name;
	private String currency_code;
	private BigDecimal total_sum_insured;
	private BigDecimal tenor;
	private String bank_status;
	private BigDecimal payment_method;
	private BigDecimal cash_value;
	private String last_payment;
	private BigDecimal outstanding_premi;
	
	
	public String getSpaj_no() {
		return spaj_no;
	}
	public void setSpaj_no(String spaj_no) {
		this.spaj_no = spaj_no;
	}
	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public BigDecimal getTotal_sum_insured() {
		return total_sum_insured;
	}
	public void setTotal_sum_insured(BigDecimal total_sum_insured) {
		this.total_sum_insured = total_sum_insured;
	}
	public BigDecimal getTenor() {
		return tenor;
	}
	public void setTenor(BigDecimal tenor) {
		this.tenor = tenor;
	}
	public String getBank_status() {
		return bank_status;
	}
	public void setBank_status(String bank_status) {
		this.bank_status = bank_status;
	}
	public BigDecimal getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(BigDecimal payment_method) {
		this.payment_method = payment_method;
	}
	public BigDecimal getCash_value() {
		return cash_value;
	}
	public void setCash_value(BigDecimal cash_value) {
		this.cash_value = cash_value;
	}
	public String getLast_payment() {
		return last_payment;
	}
	public void setLast_payment(String last_payment) {
		this.last_payment = last_payment;
	}
	public BigDecimal getOutstanding_premi() {
		return outstanding_premi;
	}
	public void setOutstanding_premi(BigDecimal outstanding_premi) {
		this.outstanding_premi = outstanding_premi;
	}
	
								    
}

