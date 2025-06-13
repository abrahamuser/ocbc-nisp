package com.silverlake.mleb.mcb.module.vc.accountManagement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ListAccount implements Serializable{

	 
	private String uuid;
	private String id;
    private String org_cd;
    private String acct_no;
    private String acct_ccy;
    private String acct_name;
    private String acct_alias;
    private String cif_no;
    private String prod_cd_sibs;
    private String acct_type_sibs;
    private String mcbit;
    private String branch_cd_sibs;
    private String balance_available;
    private String balance_ledger;
    private String balance_hold;
    private String balance_overdraft;
    private String principal_amout;
    private String bilyet_no ;
    private String effective_date ;
    private int term;
    private String term_cd;
    private String maturity_date;
    private String interest_rate;
    private String last_int_paid_date;
    private int renewal_counter;
    private String auto_renewal;
    private Integer acct_status;
    private List<String> fav_prod_map;
    private Integer interest_term;
    private String interest_term_code;
    private BigDecimal interest_amount;
    private String acct_prod_name;
    private int version;
    private String maintenance_type;
    private String auth_status_code;
    private String auth_status;
    private String pending_record_id;

    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
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
	public String getBalance_hold() {
		return balance_hold;
	}
	public void setBalance_hold(String balance_hold) {
		this.balance_hold = balance_hold;
	}
	public String getBalance_overdraft() {
		return balance_overdraft;
	}
	public void setBalance_overdraft(String balance_overdraft) {
		this.balance_overdraft = balance_overdraft;
	}
	public String getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getBilyet_no() {
		return bilyet_no;
	}
	public void setBilyet_no(String bilyet_no) {
		this.bilyet_no = bilyet_no;
	}
	public String getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public String getTerm_cd() {
		return term_cd;
	}
	public void setTerm_cd(String term_cd) {
		this.term_cd = term_cd;
	}
	public String getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}
	public String getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}
	public String getLast_int_paid_date() {
		return last_int_paid_date;
	}
	public void setLast_int_paid_date(String last_int_paid_date) {
		this.last_int_paid_date = last_int_paid_date;
	}
	public int getRenewal_counter() {
		return renewal_counter;
	}
	public void setRenewal_counter(int renewal_counter) {
		this.renewal_counter = renewal_counter;
	}
	public String getAuto_renewal() {
		return auto_renewal;
	}
	public void setAuto_renewal(String auto_renewal) {
		this.auto_renewal = auto_renewal;
	}
	public Integer getAcct_status() {
		return acct_status;
	}
	public void setAcct_status(Integer acct_status) {
		this.acct_status = acct_status;
	}
	public List<String> getFav_prod_map() {
		return fav_prod_map;
	}
	public void setFav_prod_map(List<String> fav_prod_map) {
		this.fav_prod_map = fav_prod_map;
	}
	public Integer getInterest_term() {
		return interest_term;
	}
	public String getInterest_term_code() {
		return interest_term_code;
	}
	public BigDecimal getInterest_amount() {
		return interest_amount;
	}
	public void setInterest_term(Integer interest_term) {
		this.interest_term = interest_term;
	}
	public void setInterest_term_code(String interest_term_code) {
		this.interest_term_code = interest_term_code;
	}
	public void setInterest_amount(BigDecimal interest_amount) {
		this.interest_amount = interest_amount;
	}
	public String getPrincipal_amout() {
		return principal_amout;
	}
	public void setPrincipal_amout(String principal_amout) {
		this.principal_amout = principal_amout;
	}
	public String getAcct_prod_name() {
		return acct_prod_name;
	}
	public void setAcct_prod_name(String acct_prod_name) {
		this.acct_prod_name = acct_prod_name;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getMaintenance_type() {
		return maintenance_type;
	}
	public void setMaintenance_type(String maintenance_type) {
		this.maintenance_type = maintenance_type;
	}
	public String getAuth_status_code() {
		return auth_status_code;
	}
	public void setAuth_status_code(String auth_status_code) {
		this.auth_status_code = auth_status_code;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public String getPending_record_id() {
		return pending_record_id;
	}
	public void setPending_record_id(String pending_record_id) {
		this.pending_record_id = pending_record_id;
	}
	
}

