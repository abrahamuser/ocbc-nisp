package com.silverlake.mleb.mcb.module.vc.transaction;

import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class BillPaymentInquiryRequestData extends VCRequest{
	private String bank_ref;
	private String debit_acct_no;
	private String debit_acct_ccy;
	private String billing_id;
	private String billing_org_id;
	private String cust_ref;
	private BigDecimal amount;
	private String effective_date;//YYYY-MM-DD
	private String lang;
	private String ppType;
		
	public String getBank_ref() {
		return bank_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}
	public String getDebit_acct_no() {
		return debit_acct_no;
	}
	public void setDebit_acct_no(String debit_acct_no) {
		this.debit_acct_no = debit_acct_no;
	}
	public String getDebit_acct_ccy() {
		return debit_acct_ccy;
	}
	public void setDebit_acct_ccy(String debit_acct_ccy) {
		this.debit_acct_ccy = debit_acct_ccy;
	}
	public String getBilling_id() {
		return billing_id;
	}
	public void setBilling_id(String billing_id) {
		this.billing_id = billing_id;
	}
	public String getBilling_org_id() {
		return billing_org_id;
	}
	public void setBilling_org_id(String billing_org_id) {
		this.billing_org_id = billing_org_id;
	}
	public String getCust_ref() {
		return cust_ref;
	}
	public void setCust_ref(String cust_ref) {
		this.cust_ref = cust_ref;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getPpType() {
		return ppType;
	}
	public void setPpType(String ppType) {
		this.ppType = ppType;
	}
}
