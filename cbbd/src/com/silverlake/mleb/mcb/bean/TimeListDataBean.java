package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class TimeListDataBean implements Serializable{
	
	private String code;
	private String time_value;
	private String time_desc;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime_value() {
		return time_value;
	}
	public void setTime_value(String time_value) {
		this.time_value = time_value;
	}
	public String getTime_desc() {
		return time_desc;
	}
	public void setTime_desc(String time_desc) {
		this.time_desc = time_desc;
	}
	
	
				
}

