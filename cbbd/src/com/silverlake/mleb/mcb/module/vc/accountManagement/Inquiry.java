package com.silverlake.mleb.mcb.module.vc.accountManagement;

import java.io.Serializable;
import java.math.BigDecimal;

public class Inquiry implements Serializable {

	 
	
	private String org_cd;
    private String acct_no;
    private String acct_ccy;
    private String acct_name;
    private String acct_alias;
    private String balance_available;
    private String balance_ledger;
    private String cif_no;
    private String prod_cd_sibs;
    private String acct_type_sibs;
    private String mcbit;
    private String branch_cd_sibs;
    
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public String getAcct_ccy() {
		return acct_ccy;
	}
	public void setAcct_ccy(String acct_ccy) {
		this.acct_ccy = acct_ccy;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public String getAcct_alias() {
		return acct_alias;
	}
	public void setAcct_alias(String acct_alias) {
		this.acct_alias = acct_alias;
	}
	
	public String getBalance_available() {
		return balance_available;
	}
	public void setBalance_available(String balance_available) {
		this.balance_available = balance_available;
	}
	public String getBalance_ledger() {
		return balance_ledger;
	}
	public void setBalance_ledger(String balance_ledger) {
		this.balance_ledger = balance_ledger;
	}
	public String getCif_no() {
		return cif_no;
	}
	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}
	public String getProd_cd_sibs() {
		return prod_cd_sibs;
	}
	public void setProd_cd_sibs(String prod_cd_sibs) {
		this.prod_cd_sibs = prod_cd_sibs;
	}
	public String getAcct_type_sibs() {
		return acct_type_sibs;
	}
	public void setAcct_type_sibs(String acct_type_sibs) {
		this.acct_type_sibs = acct_type_sibs;
	}
	public String getMcbit() {
		return mcbit;
	}
	public void setMcbit(String mcbit) {
		this.mcbit = mcbit;
	}
	public String getBranch_cd_sibs() {
		return branch_cd_sibs;
	}
	public void setBranch_cd_sibs(String branch_cd_sibs) {
		this.branch_cd_sibs = branch_cd_sibs;
	}
    

}

