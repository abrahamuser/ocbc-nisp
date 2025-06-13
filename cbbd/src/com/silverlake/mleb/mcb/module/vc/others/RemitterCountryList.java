package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class RemitterCountryList implements Serializable {
	private String remitter_country_code;
    private String description;
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemitter_country_code() {
		return remitter_country_code;
	}
	public void setRemitter_country_code(String remitter_country_code) {
		this.remitter_country_code = remitter_country_code;
	}
}

