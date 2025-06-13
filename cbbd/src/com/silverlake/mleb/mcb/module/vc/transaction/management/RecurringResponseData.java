package com.silverlake.mleb.mcb.module.vc.transaction.management;


import java.util.ArrayList;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class RecurringResponseData extends VCResponseData{

    
	List<Recurring> recurring_list;
	
	private String total_rows;

	public List<Recurring> getRecurring_list() {
		return recurring_list;
	}

	public void setRecurring_list(List<Recurring> recurring_list) {
		this.recurring_list = recurring_list;
	}

	public String getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(String total_rows) {
		this.total_rows = total_rows;
	}
  
    
	
	
	
	 
}
