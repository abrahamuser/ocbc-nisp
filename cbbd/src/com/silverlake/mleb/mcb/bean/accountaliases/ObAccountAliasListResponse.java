package com.silverlake.mleb.mcb.bean.accountaliases;


import com.silverlake.mleb.mcb.bean.ObAccountBean;
import com.silverlake.mleb.mcb.bean.ObResponseCache;

import java.io.Serializable;
import java.util.List;

public class ObAccountAliasListResponse extends ObResponseCache implements Serializable {
	

	private List<ObAccountBean> accountList;
	private Integer totalRecords;
	
	
	public List<ObAccountBean> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<ObAccountBean> accountList) {
		this.accountList = accountList;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	
			
	    
}
