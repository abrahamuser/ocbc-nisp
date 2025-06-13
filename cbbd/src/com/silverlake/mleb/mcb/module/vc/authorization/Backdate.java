package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class Backdate  implements Serializable
{
	  
	
	private String prod_cd;
	private String count_backdated;
	private String count_cot;
	private String value_date;
	
	private String prod_desc;
	private List<RateChangesInfo> rate_changes_info;
	
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getCount_backdated() {
		return count_backdated;
	}
	public void setCount_backdated(String count_backdated) {
		this.count_backdated = count_backdated;
	}
	public String getCount_cot() {
		return count_cot;
	}
	public void setCount_cot(String count_cot) {
		this.count_cot = count_cot;
	}
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}
	public List<RateChangesInfo> getRate_changes_info() {
		return rate_changes_info;
	}
	public void setRate_changes_info(List<RateChangesInfo> rate_changes_info) {
		this.rate_changes_info = rate_changes_info;
	}
		 
	 
}



	
