package com.silverlake.mleb.mcb.module.vc.accountManagement;

import java.io.Serializable;

public class AccountStatement implements Serializable{

	 
 
	
	private String record_id;
	private String acct_no ;
	private String acct_ccy; 
	private String trx_date ;
	private String value_date ;
	private String reference_no; 
	private String cheque_no ;
	private String description ;
	private String amount ;
	private String debit_credit_ind;
	private String balance;
	
	
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
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
	public String getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(String trx_date) {
		this.trx_date = trx_date;
	}
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
	public String getReference_no() {
		return reference_no;
	}
	public void setReference_no(String reference_no) {
		this.reference_no = reference_no;
	}
	public String getCheque_no() {
		return cheque_no;
	}
	public void setCheque_no(String cheque_no) {
		this.cheque_no = cheque_no;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDebit_credit_ind() {
		return debit_credit_ind;
	}
	public void setDebit_credit_ind(String debit_credit_ind) {
		this.debit_credit_ind = debit_credit_ind;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
	 
    
}

