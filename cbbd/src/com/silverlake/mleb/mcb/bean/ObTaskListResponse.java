package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionSum;
public class ObTaskListResponse extends ObResponse implements Serializable
{
	private List<TaskInfo> taskInfoList;
	private List<Transaction> taskTransactionList;
	private List<TransactionSum> taskTransactionSum;
	private String total_rows;
	private List<Transaction> productList;
	
	public List<TaskInfo> getTaskInfoList() {
		return taskInfoList;
	}

	public void setTaskInfoList(List<TaskInfo> taskInfoList) {
		this.taskInfoList = taskInfoList;
	}

	public List<Transaction> getTaskTransactionList() {
		return taskTransactionList;
	}

	public void setTaskTransactionList(List<Transaction> taskTransactionList) {
		this.taskTransactionList = taskTransactionList;
	}

	public List<TransactionSum> getTaskTransactionSum() {
		return taskTransactionSum;
	}

	public void setTaskTransactionSum(List<TransactionSum> taskTransactionSum) {
		this.taskTransactionSum = taskTransactionSum;
	}

	public String getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(String total_rows) {
		this.total_rows = total_rows;
	}

	public List<Transaction> getProductList() {
		return productList;
	}

	public void setProductList(List<Transaction> productList) {
		this.productList = productList;
	}
}
