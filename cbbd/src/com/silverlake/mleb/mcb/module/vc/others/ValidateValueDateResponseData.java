package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ValidateValueDateResponseData extends VCResponseData {
	
	private String is_backdated;
	private String is_cot;
	private String is_holiday;
	private String next_value_date;
	
	
	public String getIs_backdated() {
		return is_backdated;
	}
	public void setIs_backdated(String is_backdated) {
		this.is_backdated = is_backdated;
	}
	public String getIs_cot() {
		return is_cot;
	}
	public void setIs_cot(String is_cot) {
		this.is_cot = is_cot;
	}
	public String getIs_holiday() {
		return is_holiday;
	}
	public void setIs_holiday(String is_holiday) {
		this.is_holiday = is_holiday;
	}
	public String getNext_value_date() {
		return next_value_date;
	}
	public void setNext_value_date(String next_value_date) {
		this.next_value_date = next_value_date;
	}
	
}
