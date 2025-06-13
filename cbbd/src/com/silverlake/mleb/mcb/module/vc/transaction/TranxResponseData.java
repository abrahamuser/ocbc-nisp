package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TranxResponseData extends VCResponseData
{
	  
	 private List<Transaction> list_trx;
	 private List<TransactionSum> list_trx_summary;
	 private List<TransactionSum> list_account;

	 private int total_rows;
	 
	public List<Transaction> getList_trx() {
		return list_trx;
	}
	public void setList_trx(List<Transaction> list_trx) {
		this.list_trx = list_trx;
	}
	public int getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}
	public List<TransactionSum> getList_account() {
		return list_account;
	}
	public void setList_account(List<TransactionSum> list_account) {
		this.list_account = list_account;
	}
	public List<TransactionSum> getList_trx_summary() {
		return list_trx_summary;
	}
	public void setList_trx_summary(List<TransactionSum> list_trx_summary) {
		this.list_trx_summary = list_trx_summary;
	}
	
	
	 
 
}



	
