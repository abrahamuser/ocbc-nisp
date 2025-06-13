package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class BeneficiaryCountryList implements Serializable {
	private String bene_country_code;
    private String description;
    
	public String getBene_country_code() {
		return bene_country_code;
	}
	public void setBene_country_code(String bene_country_code) {
		this.bene_country_code = bene_country_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

