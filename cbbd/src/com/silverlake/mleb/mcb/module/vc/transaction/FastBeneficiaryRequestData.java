package com.silverlake.mleb.mcb.module.vc.transaction;

import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class FastBeneficiaryRequestData extends VCRequest{
	private String proxy_data;
	private String proxy_type;
	private String debit_acct_no;
	private String bene_acct_no;
	private String bene_bank_id;
	private String bene_bank_name;
	private BigDecimal amount;
	private String amount_ccy;
	private String trx_purpose;
	
	public String getProxy_data() {
		return proxy_data;
	}
	public void setProxy_data(String proxy_data) {
		this.proxy_data = proxy_data;
	}
	public String getProxy_type() {
		return proxy_type;
	}
	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	public String getDebit_acct_no() {
		return debit_acct_no;
	}
	public void setDebit_acct_no(String debit_acct_no) {
		this.debit_acct_no = debit_acct_no;
	}
	public String getBene_acct_no() {
		return bene_acct_no;
	}
	public void setBene_acct_no(String bene_acct_no) {
		this.bene_acct_no = bene_acct_no;
	}
	public String getBene_bank_id() {
		return bene_bank_id;
	}
	public void setBene_bank_id(String bene_bank_id) {
		this.bene_bank_id = bene_bank_id;
	}
	public String getBene_bank_name() {
		return bene_bank_name;
	}
	public void setBene_bank_name(String bene_bank_name) {
		this.bene_bank_name = bene_bank_name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAmount_ccy() {
		return amount_ccy;
	}
	public void setAmount_ccy(String amount_ccy) {
		this.amount_ccy = amount_ccy;
	}
	public String getTrx_purpose() {
		return trx_purpose;
	}
	public void setTrx_purpose(String trx_purpose) {
		this.trx_purpose = trx_purpose;
	}
	
		
}
