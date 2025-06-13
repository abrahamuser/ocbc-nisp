package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.io.Serializable;
import java.util.List;

public class COTConfig implements Serializable{
	private Boolean is_cot;
	private String bus_hour_start;
	private String bus_hour_end;
	private String ccy_cd;
	
	public Boolean getIs_cot() {
		return is_cot;
	}
	public String getBus_hour_start() {
		return bus_hour_start;
	}
	public String getBus_hour_end() {
		return bus_hour_end;
	}
	public void setIs_cot(Boolean is_cot) {
		this.is_cot = is_cot;
	}
	public void setBus_hour_start(String bus_hour_start) {
		this.bus_hour_start = bus_hour_start;
	}
	public void setBus_hour_end(String bus_hour_end) {
		this.bus_hour_end = bus_hour_end;
	}
	public String getCcy_cd() {
		return ccy_cd;
	}
	public void setCcy_cd(String ccy_cd) {
		this.ccy_cd = ccy_cd;
	}
	
}

