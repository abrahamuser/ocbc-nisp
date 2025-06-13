package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.transaction.management.Recurring;

public class ObRecurringResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 7709279929407878829L;

	private List<Recurring> recurringList;
	private String totalSize;

	public List<Recurring> getRecurringList() {
		return recurringList;
	}

	public void setRecurringList(List<Recurring> recurringList) {
		this.recurringList = recurringList;
	}

	public String getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}

	
    
	
	
	
}
