package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class BeneficiaryAffiliationList implements Serializable {
	private String bene_affiliation_code;
    private String description;
    
	public String getBene_affiliation_code() {
		return bene_affiliation_code;
	}
	public void setBene_affiliation_code(String bene_affiliation_code) {
		this.bene_affiliation_code = bene_affiliation_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

