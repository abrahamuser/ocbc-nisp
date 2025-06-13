package com.silverlake.mleb.mcb.module.vc.task_list;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class TaskListResponseData extends VCResponseData
{
	  private List<TaskCount> list_task_count;
	  private int total_rows;
	  private List<Transaction> list_trx;
	  
	  
 
	 

	 
	public List<TaskCount> getList_task_count() {
		return list_task_count;
	}

	public void setList_task_count(List<TaskCount> list_task_count) {
		this.list_task_count = list_task_count;
	}

	public int getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}

	public List<Transaction> getList_trx() {
		return list_trx;
	}

	public void setList_trx(List<Transaction> list_trx) {
		this.list_trx = list_trx;
	}
	
	
	
	
	
}



	
