package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class ValidateValueDateRequestData extends VCRequest {
	
	private String prod_cd;
	private String value_date;
	private String bene_acct_ccy;
	private String debit_acct_ccy;
	private String bene_bank_code;
	private String bene_bank_network_id;
	private String bene_bank_branch;
	private String bene_bank_country;
	
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
	public String getBene_acct_ccy() {
		return bene_acct_ccy;
	}
	public void setBene_acct_ccy(String bene_acct_ccy) {
		this.bene_acct_ccy = bene_acct_ccy;
	}
	public String getDebit_acct_ccy() {
		return debit_acct_ccy;
	}
	public void setDebit_acct_ccy(String debit_acct_ccy) {
		this.debit_acct_ccy = debit_acct_ccy;
	}
	public String getBene_bank_code() {
		return bene_bank_code;
	}
	public void setBene_bank_code(String bene_bank_code) {
		this.bene_bank_code = bene_bank_code;
	}
	public String getBene_bank_network_id() {
		return bene_bank_network_id;
	}
	public void setBene_bank_network_id(String bene_bank_network_id) {
		this.bene_bank_network_id = bene_bank_network_id;
	}
	public String getBene_bank_branch() {
		return bene_bank_branch;
	}
	public void setBene_bank_branch(String bene_bank_branch) {
		this.bene_bank_branch = bene_bank_branch;
	}
	public String getBene_bank_country() {
		return bene_bank_country;
	}
	public void setBene_bank_country(String bene_bank_country) {
		this.bene_bank_country = bene_bank_country;
	}
	
}