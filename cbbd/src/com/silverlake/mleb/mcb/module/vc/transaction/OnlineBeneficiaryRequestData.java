package com.silverlake.mleb.mcb.module.vc.transaction;

import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class OnlineBeneficiaryRequestData extends VCRequest{
	private String bank_ref;
	private String debit_acct_no;
	private String debit_acct_ccy;
	private String bene_acct_no;
	private String bene_bank_code;
	private String cust_ref;
	private BigDecimal amount;
	private String effective_date;
	private String lang;
	
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
	public String getBene_acct_no() {
		return bene_acct_no;
	}
	public void setBene_acct_no(String bene_acct_no) {
		this.bene_acct_no = bene_acct_no;
	}
	public String getBene_bank_code() {
		return bene_bank_code;
	}
	public void setBene_bank_code(String bene_bank_code) {
		this.bene_bank_code = bene_bank_code;
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
	
}
