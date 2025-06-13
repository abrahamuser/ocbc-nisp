package com.silverlake.mleb.mcb.module.vc.timedeposit;

import java.io.Serializable;
import java.math.BigDecimal;

public class ListTimeDepositProduct implements Serializable {
    private String prod_cd;
    private String prod_name;
    private String ccy_code;
    private Integer tenor_value;
    private String tenor_type;
    private String interest_term_value;
    private String interest_term_type;
    private BigDecimal min_amount;
    private BigDecimal max_amount;
    private BigDecimal interest_rate;
	public String getProd_cd() {
		return prod_cd;
	}
	public String getProd_name() {
		return prod_name;
	}
	public String getCcy_code() {
		return ccy_code;
	}
	public Integer getTenor_value() {
		return tenor_value;
	}
	public String getTenor_type() {
		return tenor_type;
	}
	public String getInterest_term_value() {
		return interest_term_value;
	}
	public String getInterest_term_type() {
		return interest_term_type;
	}
	public BigDecimal getMin_amount() {
		return min_amount;
	}
	public BigDecimal getMax_amount() {
		return max_amount;
	}
	public BigDecimal getInterest_rate() {
		return interest_rate;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}
	public void setTenor_value(Integer tenor_value) {
		this.tenor_value = tenor_value;
	}
	public void setTenor_type(String tenor_type) {
		this.tenor_type = tenor_type;
	}
	public void setInterest_term_value(String interest_term_value) {
		this.interest_term_value = interest_term_value;
	}
	public void setInterest_term_type(String interest_term_type) {
		this.interest_term_type = interest_term_type;
	}
	public void setMin_amount(BigDecimal min_amount) {
		this.min_amount = min_amount;
	}
	public void setMax_amount(BigDecimal max_amount) {
		this.max_amount = max_amount;
	}
	public void setInterest_rate(BigDecimal interest_rate) {
		this.interest_rate = interest_rate;
	}
}
