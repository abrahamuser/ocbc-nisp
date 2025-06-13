package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class CheckCutOffTimeResponseData extends VCResponseData{
	private Boolean is_cot;
	private String bus_hour_start;
	private String bus_hour_end;
	
	public Boolean getIs_cot() {
		return is_cot;
	}
	public void setIs_cot(Boolean is_cot) {
		this.is_cot = is_cot;
	}
	public String getBus_hour_start() {
		return bus_hour_start;
	}
	public void setBus_hour_start(String bus_hour_start) {
		this.bus_hour_start = bus_hour_start;
	}
	public String getBus_hour_end() {
		return bus_hour_end;
	}
	public void setBus_hour_end(String bus_hour_end) {
		this.bus_hour_end = bus_hour_end;
	}

	
}
