package com.silverlake.mleb.mcb.bean.vctransaction;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class ObVcTranxResponse extends ObResponse implements Serializable
{
	private List<Transaction> transaction;
	private String totalTranx;

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public String getTotalTranx() {
		return totalTranx;
	}

	public void setTotalTranx(String totalTranx) {
		this.totalTranx = totalTranx;
	}

	
 
	 
	 
	
}
