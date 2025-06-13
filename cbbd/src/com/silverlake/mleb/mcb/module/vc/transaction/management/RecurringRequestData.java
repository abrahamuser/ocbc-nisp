package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

 
public class RecurringRequestData extends VCRequest{

	
	 	public static String method_trx_mgmt_listRecurring_ft  = "transaction_mgmt/list_recurring/fundstransfer";
	 	public static String method_trx_mgmt_listRecurring_Pymt  = "transaction_mgmt/list_recurring/payments";
		public static String method_trx_mgmt_delRecurring = "transaction_mgmt/delete_recurring";
	 	
		
		
	    private List<String> list_prod_cd;
	    private String debit_acct_no;
	    private String bene_acct_no;
	    private String show_newest;
	    private String amount;
	    private String biller_code;
	    private String billing_id;
	    private int page_no;
	    private int page_size;
	    
	    
	    private String prod_cd;
	    private String recurring_id;
	    private String delete_type;
	    
	    
		public List<String> getList_prod_cd() {
			return list_prod_cd;
		}
		public void setList_prod_cd(List<String> list_prod_cd) {
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
		public int getPage_no() {
			return page_no;
		}
		public void setPage_no(int page_no) {
			this.page_no = page_no;
		}
		public int getPage_size() {
			return page_size;
		}
		public void setPage_size(int page_size) {
			this.page_size = page_size;
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
		public String getShow_newest() {
			return show_newest;
		}
		public void setShow_newest(String show_newest) {
			this.show_newest = show_newest;
		}
		public String getBiller_code() {
			return biller_code;
		}
		public void setBiller_code(String biller_code) {
			this.biller_code = biller_code;
		}
		public String getBilling_id() {
			return billing_id;
		}
		public void setBilling_id(String billing_id) {
			this.billing_id = billing_id;
		}
	    
	     
	     
		
	    

}
