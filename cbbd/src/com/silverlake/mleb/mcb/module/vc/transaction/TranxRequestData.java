package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TranxRequestData extends VCRequest
{
	
	 public static String method_transaction_futureDated  = "transaction/future_dated";
	 public static String method_transaction_summary  = "transaction/item_summary";
	 public static String method_transaction_status  = "transaction/view_status";
 
	 private int n_days;
	 private String trx_source;
	 private String pymt_master_id;
	 private int page_no;
	 private int page_size;
	 private String trx_status;
	 
	 
	public String getTrx_source() {
		return trx_source;
	}
	public void setTrx_source(String trx_source) {
		this.trx_source = trx_source;
	}
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
	public int getN_days() {
		return n_days;
	}
	public void setN_days(int n_days) {
		this.n_days = n_days;
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
	public String getTrx_status() {
		return trx_status;
	}
	public void setTrx_status(String trx_status) {
		this.trx_status = trx_status;
	}
	 
	 
	
 
}



	
