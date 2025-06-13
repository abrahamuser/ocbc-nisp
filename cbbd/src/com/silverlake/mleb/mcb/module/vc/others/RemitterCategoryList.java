package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class RemitterCategoryList implements Serializable {
	private String remitter_category_code;
    private String description;
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemitter_category_code() {
		return remitter_category_code;
	}
	public void setRemitter_category_code(String remitter_category_code) {
		this.remitter_category_code = remitter_category_code;
	}
}

