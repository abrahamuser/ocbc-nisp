package com.silverlake.mleb.mcb.module.vc.accountManagement;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ResponseData extends VCResponseData{

    private List<ListAccount> list_account = new ArrayList<ListAccount>();
    private List<AccountStatement> list_statement ;
    private int total_rows;
    
    //inquiry
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
    
    private String balance_hold;
    private String balance_overdraft;
   
    
    
    private String opening_balance;
    private String closing_balance;
    
    private String doc_path;
    private Integer acct_status;
    private BigDecimal principal_amout;
    private Integer interest_term;
    private String interest_term_code;
    private Integer term;
    private String term_cd;
    private String maturity_date;
    private String effective_date;
    private BigDecimal interest_rate;
    private BigDecimal interest_amount;
    private String tdam_flag;
    private String auto_renewal;
    private String accrued_interest;

	public List<ListAccount> getList_account() {
		return list_account;
	}

	public void setList_account(List<ListAccount> list_account) {
		this.list_account = list_account;
	}

	public int getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
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

	public List<AccountStatement> getList_statement() {
		return list_statement;
	}

	public void setList_statement(List<AccountStatement> list_statement) {
		this.list_statement = list_statement;
	}

	public String getOpening_balance() {
		return opening_balance;
	}

	public void setOpening_balance(String opening_balance) {
		this.opening_balance = opening_balance;
	}

	public String getClosing_balance() {
		return closing_balance;
	}

	public void setClosing_balance(String closing_balance) {
		this.closing_balance = closing_balance;
	}

	public String getDoc_path() {
		return doc_path;
	}

	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}

	public String getAcct_no() {
		return acct_no;
	}

	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}

	public Integer getAcct_status() {
		return acct_status;
	}

	public void setAcct_status(Integer acct_status) {
		this.acct_status = acct_status;
	}

	public Integer getInterest_term() {
		return interest_term;
	}

	public String getInterest_term_code() {
		return interest_term_code;
	}

	public Integer getTerm() {
		return term;
	}

	public String getTerm_cd() {
		return term_cd;
	}

	public String getMaturity_date() {
		return maturity_date;
	}

	public String getEffective_date() {
		return effective_date;
	}

	public BigDecimal getInterest_rate() {
		return interest_rate;
	}

	public BigDecimal getInterest_amount() {
		return interest_amount;
	}

	public String getTdam_flag() {
		return tdam_flag;
	}

	public String getAuto_renewal() {
		return auto_renewal;
	}

	public void setInterest_term(Integer interest_term) {
		this.interest_term = interest_term;
	}

	public void setInterest_term_code(String interest_term_code) {
		this.interest_term_code = interest_term_code;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public void setTerm_cd(String term_cd) {
		this.term_cd = term_cd;
	}

	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}

	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}

	public void setInterest_rate(BigDecimal interest_rate) {
		this.interest_rate = interest_rate;
	}

	public void setInterest_amount(BigDecimal interest_amount) {
		this.interest_amount = interest_amount;
	}

	public void setTdam_flag(String tdam_flag) {
		this.tdam_flag = tdam_flag;
	}

	public void setAuto_renewal(String auto_renewal) {
		this.auto_renewal = auto_renewal;
	}

	public BigDecimal getPrincipal_amout() {
		return principal_amout;
	}

	public void setPrincipal_amout(BigDecimal principal_amout) {
		this.principal_amout = principal_amout;
	}

	public String getAccrued_interest() {
		return accrued_interest;
	}

	public void setAccrued_interest(String accrued_interest) {
		this.accrued_interest = accrued_interest;
	}

 
	
	
	 
}
