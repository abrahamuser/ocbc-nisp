package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class BeneficiaryCategoryList implements Serializable {
	private String bene_category_code;
    private String description;
    
	public String getBene_category_code() {
		return bene_category_code;
	}
	public void setBene_category_code(String bene_category_code) {
		this.bene_category_code = bene_category_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

