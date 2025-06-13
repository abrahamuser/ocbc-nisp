package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

import java.util.List;

public class UserManagementListResponseData extends VCResponseData {
	
	private List<UserData> user_list ;
	private Integer total_records;
	
		
	public List<UserData> getUser_list() {
		return user_list;
	}
	public void setUser_list(List<UserData> user_list) {
		this.user_list = user_list;
	}
	public Integer getTotal_records() {
		return total_records;
	}
	public void setTotal_records(Integer total_records) {
		this.total_records = total_records;
	}
	  
	
	      
    }
