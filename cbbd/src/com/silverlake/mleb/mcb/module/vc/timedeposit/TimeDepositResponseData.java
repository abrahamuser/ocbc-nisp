package com.silverlake.mleb.mcb.module.vc.timedeposit;


import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class TimeDepositResponseData extends VCResponseData{
	private BigDecimal base_interest_rate;
	private BigDecimal variant_rate;
	private String variant_rate_op;
	private BigDecimal customer_rate;
	private BigDecimal min_interest_rate;
	private BigDecimal max_interest_rate;
	private String deal_type;
	private Integer version_variant_rate;
	
	private Transaction trx_data;
	private String error_message;
	
	public BigDecimal getBase_interest_rate() {
		return base_interest_rate;
	}
	public BigDecimal getVariant_rate() {
		return variant_rate;
	}
	public String getVariant_rate_op() {
		return variant_rate_op;
	}
	public BigDecimal getCustomer_rate() {
		return customer_rate;
	}
	public BigDecimal getMin_interest_rate() {
		return min_interest_rate;
	}
	public BigDecimal getMax_interest_rate() {
		return max_interest_rate;
	}
	public String getDeal_type() {
		return deal_type;
	}
	public Integer getVersion_variant_rate() {
		return version_variant_rate;
	}
	public void setBase_interest_rate(BigDecimal base_interest_rate) {
		this.base_interest_rate = base_interest_rate;
	}
	public void setVariant_rate(BigDecimal variant_rate) {
		this.variant_rate = variant_rate;
	}
	public void setVariant_rate_op(String variant_rate_op) {
		this.variant_rate_op = variant_rate_op;
	}
	public void setCustomer_rate(BigDecimal customer_rate) {
		this.customer_rate = customer_rate;
	}
	public void setMin_interest_rate(BigDecimal min_interest_rate) {
		this.min_interest_rate = min_interest_rate;
	}
	public void setMax_interest_rate(BigDecimal max_interest_rate) {
		this.max_interest_rate = max_interest_rate;
	}
	public void setDeal_type(String deal_type) {
		this.deal_type = deal_type;
	}
	public void setVersion_variant_rate(Integer version_variant_rate) {
		this.version_variant_rate = version_variant_rate;
	}
	public Transaction getTrx_data() {
		return trx_data;
	}
	public String getError_message() {
		return error_message;
	}
	public void setTrx_data(Transaction trx_data) {
		this.trx_data = trx_data;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
}
