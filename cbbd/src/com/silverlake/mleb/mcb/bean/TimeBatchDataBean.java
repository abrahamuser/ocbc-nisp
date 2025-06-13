package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class TimeBatchDataBean implements Serializable{

	private String prod_cd;
	private List<TimeListDataBean> time_list;
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public List<TimeListDataBean> getTime_list() {
		return time_list;
	}
	public void setTime_list(List<TimeListDataBean> time_list) {
		this.time_list = time_list;
	}
	
				
}

