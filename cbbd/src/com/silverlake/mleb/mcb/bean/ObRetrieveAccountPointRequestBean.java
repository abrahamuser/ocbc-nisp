package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObRetrieveAccountPointRequestBean extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 4004098985341735170L;

	private String page;
	private String countLastTransactions;
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getCountLastTransactions() {
		return countLastTransactions;
	}
	public void setCountLastTransactions(String countLastTransactions) {
		this.countLastTransactions = countLastTransactions;
	}

}