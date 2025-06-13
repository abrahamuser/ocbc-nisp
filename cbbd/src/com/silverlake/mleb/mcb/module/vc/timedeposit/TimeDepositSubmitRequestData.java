package com.silverlake.mleb.mcb.module.vc.timedeposit;

import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TimeDepositSubmitRequestData extends VCRequest{
	//Inquiry rate / submit
    private String purpose_cd;
    private String prod_cd;
    private String source_fund_cd;
    private String rollover_type_cd;
    private String customer_ref;
    private String bank_ref;
    private String debit_acct_no;
    private String debit_acct_ccy;
    private BigDecimal amount;
    private String amount_ccy;
    private Integer tenor;
    private String tenor_type;
    private Integer interest_term;
    private String interest_term_code;
    private String special_cd;
    
    //Submit
    private BigDecimal base_interest_rate;
    private BigDecimal variant_rate;
    private String variant_rate_op;
    private String deal_type;
    private Integer version_variant_rate;
    private String lang;
    
    //Additional field for maintenance submit
    private String rollover_type_cd_new;
    private String acct_no;
    private String acct_ccy;
    
    private String device_id;
	private String device_type;
	private String device_os;
    
	public String getPurpose_cd() {
		return purpose_cd;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public String getSource_fund_cd() {
		return source_fund_cd;
	}
	public String getRollover_type_cd() {
		return rollover_type_cd;
	}
	public String getCustomer_ref() {
		return customer_ref;
	}
	public String getBank_ref() {
		return bank_ref;
	}
	public String getDebit_acct_no() {
		return debit_acct_no;
	}
	public String getDebit_acct_ccy() {
		return debit_acct_ccy;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getAmount_ccy() {
		return amount_ccy;
	}
	public Integer getTenor() {
		return tenor;
	}
	public String getTenor_type() {
		return tenor_type;
	}
	public Integer getInterest_term() {
		return interest_term;
	}
	public String getInterest_term_code() {
		return interest_term_code;
	}
	public String getSpecial_cd() {
		return special_cd;
	}
	public void setPurpose_cd(String purpose_cd) {
		this.purpose_cd = purpose_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public void setSource_fund_cd(String source_fund_cd) {
		this.source_fund_cd = source_fund_cd;
	}
	public void setRollover_type_cd(String rollover_type_cd) {
		this.rollover_type_cd = rollover_type_cd;
	}
	public void setCustomer_ref(String customer_ref) {
		this.customer_ref = customer_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}
	public void setDebit_acct_no(String debit_acct_no) {
		this.debit_acct_no = debit_acct_no;
	}
	public void setDebit_acct_ccy(String debit_acct_ccy) {
		this.debit_acct_ccy = debit_acct_ccy;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setAmount_ccy(String amount_ccy) {
		this.amount_ccy = amount_ccy;
	}
	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}
	public void setTenor_type(String tenor_type) {
		this.tenor_type = tenor_type;
	}
	public void setInterest_term(Integer interest_term) {
		this.interest_term = interest_term;
	}
	public void setInterest_term_code(String interest_term_code) {
		this.interest_term_code = interest_term_code;
	}
	public BigDecimal getBase_interest_rate() {
		return base_interest_rate;
	}
	public BigDecimal getVariant_rate() {
		return variant_rate;
	}
	public String getVariant_rate_op() {
		return variant_rate_op;
	}
	public String getDeal_type() {
		return deal_type;
	}
	public Integer getVersion_variant_rate() {
		return version_variant_rate;
	}
	public String getLang() {
		return lang;
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
	public void setDeal_type(String deal_type) {
		this.deal_type = deal_type;
	}
	public void setVersion_variant_rate(Integer version_variant_rate) {
		this.version_variant_rate = version_variant_rate;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setSpecial_cd(String special_cd) {
		this.special_cd = special_cd;
	}
	public String getRollover_type_cd_new() {
		return rollover_type_cd_new;
	}
	public void setRollover_type_cd_new(String rollover_type_cd_new) {
		this.rollover_type_cd_new = rollover_type_cd_new;
	}
	public String getAcct_no() {
		return acct_no;
	}
	public String getAcct_ccy() {
		return acct_ccy;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public void setAcct_ccy(String acct_ccy) {
		this.acct_ccy = acct_ccy;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_os() {
		return device_os;
	}
	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}
}
