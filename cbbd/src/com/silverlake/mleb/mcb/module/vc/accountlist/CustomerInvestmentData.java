package com.silverlake.mleb.mcb.module.vc.accountlist;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerInvestmentData implements Serializable{
	 
	private String customer_number;
	private String client_code;
	private String product_name;
	private String product_code;
	private BigDecimal unit_balance;
	private String currency_code;
	private BigDecimal nav_amount;
	private BigDecimal amount_balance;
	private String nav_date;
	private String invest_type;
	private String maturity_date;
	private BigDecimal coupon_pct;
	
	
	public String getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}
	public String getClient_code() {
		return client_code;
	}
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public BigDecimal getUnit_balance() {
		return unit_balance;
	}
	public void setUnit_balance(BigDecimal unit_balance) {
		this.unit_balance = unit_balance;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public BigDecimal getNav_amount() {
		return nav_amount;
	}
	public void setNav_amount(BigDecimal nav_amount) {
		this.nav_amount = nav_amount;
	}
	public BigDecimal getAmount_balance() {
		return amount_balance;
	}
	public void setAmount_balance(BigDecimal amount_balance) {
		this.amount_balance = amount_balance;
	}
	public String getNav_date() {
		return nav_date;
	}
	public void setNav_date(String nav_date) {
		this.nav_date = nav_date;
	}
	public String getInvest_type() {
		return invest_type;
	}
	public void setInvest_type(String invest_type) {
		this.invest_type = invest_type;
	}
	public String getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}
	public BigDecimal getCoupon_pct() {
		return coupon_pct;
	}
	public void setCoupon_pct(BigDecimal coupon_pct) {
		this.coupon_pct = coupon_pct;
	}
		    
}

