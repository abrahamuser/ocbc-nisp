package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class TransactionDataListResponseData extends VCResponseData{
	private List<Transaction> list_trx;
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
	
}



	
