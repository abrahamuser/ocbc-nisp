package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.io.Serializable;

public class Recurring implements Serializable{
	 
	private String recurring_id;
	private String prod_cd;
	private String reference_id;
	private String debit_acct_no;
	private String debit_acct_ccy;
	private String debit_acct_name;
	private String debit_acct_alias;
	private String bene_acct_no;
	private String bene_acct_ccy;
	private String bene_acct_name;
	private String bene_bank_name;
	private String billing_id;
	
	private String biller_code;
	private String biller_group;
	private String biller_name;
	private String amount;
	private String amount_ccy;
	private String next_value_date;
	private String recurring_type;
	private String recurring_value;
	private String recurring_start;
	private String recurring_end;
	
	private String exec_time_batch_cd;

	
	
	
	public String getRecurring_id() {
		return recurring_id;
	}
	public void setRecurring_id(String recurring_id) {
		this.recurring_id = recurring_id;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
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
	public String getDebit_acct_name() {
		return debit_acct_name;
	}
	public void setDebit_acct_name(String debit_acct_name) {
		this.debit_acct_name = debit_acct_name;
	}
	public String getDebit_acct_alias() {
		return debit_acct_alias;
	}
	public void setDebit_acct_alias(String debit_acct_alias) {
		this.debit_acct_alias = debit_acct_alias;
	}
	public String getBene_acct_no() {
		return bene_acct_no;
	}
	public void setBene_acct_no(String bene_acct_no) {
		this.bene_acct_no = bene_acct_no;
	}
	public String getBene_acct_ccy() {
		return bene_acct_ccy;
	}
	public void setBene_acct_ccy(String bene_acct_ccy) {
		this.bene_acct_ccy = bene_acct_ccy;
	}
	public String getBene_acct_name() {
		return bene_acct_name;
	}
	public void setBene_acct_name(String bene_acct_name) {
		this.bene_acct_name = bene_acct_name;
	}
	public String getBene_bank_name() {
		return bene_bank_name;
	}
	public void setBene_bank_name(String bene_bank_name) {
		this.bene_bank_name = bene_bank_name;
	}
	public String getBilling_id() {
		return billing_id;
	}
	public void setBilling_id(String billing_id) {
		this.billing_id = billing_id;
	}
	public String getBiller_code() {
		return biller_code;
	}
	public void setBiller_code(String biller_code) {
		this.biller_code = biller_code;
	}
	public String getBiller_name() {
		return biller_name;
	}
	public void setBiller_name(String biller_name) {
		this.biller_name = biller_name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmount_ccy() {
		return amount_ccy;
	}
	public void setAmount_ccy(String amount_ccy) {
		this.amount_ccy = amount_ccy;
	}
	public String getNext_value_date() {
		return next_value_date;
	}
	public void setNext_value_date(String next_value_date) {
		this.next_value_date = next_value_date;
	}
	public String getRecurring_type() {
		return recurring_type;
	}
	public void setRecurring_type(String recurring_type) {
		this.recurring_type = recurring_type;
	}
	public String getRecurring_value() {
		return recurring_value;
	}
	public void setRecurring_value(String recurring_value) {
		this.recurring_value = recurring_value;
	}
	public String getRecurring_start() {
		return recurring_start;
	}
	public void setRecurring_start(String recurring_start) {
		this.recurring_start = recurring_start;
	}
	public String getRecurring_end() {
		return recurring_end;
	}
	public void setRecurring_end(String recurring_end) {
		this.recurring_end = recurring_end;
	}
	public String getBiller_group() {
		return biller_group;
	}
	public void setBiller_group(String biller_group) {
		this.biller_group = biller_group;
	}
	public String getExec_time_batch_cd() {
		return exec_time_batch_cd;
	}
	public void setExec_time_batch_cd(String exec_time_batch_cd) {
		this.exec_time_batch_cd = exec_time_batch_cd;
	}
 
	
	
	
}

