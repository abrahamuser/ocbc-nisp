package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class UnderlyingList implements Serializable {
	private String underlying_code;
    private String underlying_name_en;
    private String underlying_name_id;
    
	public String getUnderlying_code() {
		return underlying_code;
	}
	public void setUnderlying_code(String underlying_code) {
		this.underlying_code = underlying_code;
	}
	public String getUnderlying_name_en() {
		return underlying_name_en;
	}
	public void setUnderlying_name_en(String underlying_name_en) {
		this.underlying_name_en = underlying_name_en;
	}
	public String getUnderlying_name_id() {
		return underlying_name_id;
	}
	public void setUnderlying_name_id(String underlying_name_id) {
		this.underlying_name_id = underlying_name_id;
	}
}

