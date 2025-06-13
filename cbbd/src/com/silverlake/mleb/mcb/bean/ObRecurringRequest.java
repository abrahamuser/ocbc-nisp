package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObRecurringRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -3822270038236396582L;

	private String page_no;
	    
	private String page_size;

	private String recurringType; 
	
	
	private String list_prod_cd;
    private String debit_acct_no;
    private String bene_acct_no;
    private String show_newest;
    private String amount;
    
    private String billerCode;
    private String billingId;
    
    private String prod_cd;
    private String recurring_id;
    private String delete_type;
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	public String getPage_size() {
		return page_size;
	}
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	 
	public String getList_prod_cd() {
		return list_prod_cd;
	}
	public void setList_prod_cd(String list_prod_cd) {
		this.list_prod_cd = list_prod_cd;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getRecurring_id() {
		return recurring_id;
	}
	public void setRecurring_id(String recurring_id) {
		this.recurring_id = recurring_id;
	}
	public String getDelete_type() {
		return delete_type;
	}
	public void setDelete_type(String delete_type) {
		this.delete_type = delete_type;
	}
	public String getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
	}
	public String getShow_newest() {
		return show_newest;
	}
	public void setShow_newest(String show_newest) {
		this.show_newest = show_newest;
	}
	public String getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(String billerCode) {
		this.billerCode = billerCode;
	}
	public String getBillingId() {
		return billingId;
	}
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}
	 
    
    
    
    
	
	
}