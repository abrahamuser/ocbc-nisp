package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class PendingUserListResponseData extends VCResponseData {
	private List<UserData> user_list;
	private Integer total_records;
	public List<UserData> getUser_list() {
		return user_list;
	}
	public Integer getTotal_records() {
		return total_records;
	}
	public void setUser_list(List<UserData> user_list) {
		this.user_list = user_list;
	}
	public void setTotal_records(Integer total_records) {
		this.total_records = total_records;
	}
}
