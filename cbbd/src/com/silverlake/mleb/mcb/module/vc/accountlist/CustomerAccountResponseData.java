package com.silverlake.mleb.mcb.module.vc.accountlist;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

import java.util.List;

public class CustomerAccountResponseData extends VCResponseData {
	
	private List<CustomerAccountData> list_account ;
	private Integer total_rows;
	    
	public List<CustomerAccountData> getList_account() {
		return list_account;
	}

	public void setList_account(List<CustomerAccountData> list_account) {
		this.list_account = list_account;
	}

	public Integer getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(Integer total_rows) {
		this.total_rows = total_rows;
	}
  
  
    
    }
