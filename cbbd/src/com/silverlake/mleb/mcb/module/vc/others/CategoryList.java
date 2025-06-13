package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class CategoryList implements Serializable {
	private String category_code;
    private String category_name_en;
    private String category_name_id;
    private String category_name_cn;
    
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getCategory_name_en() {
		return category_name_en;
	}
	public void setCategory_name_en(String category_name_en) {
		this.category_name_en = category_name_en;
	}
	public String getCategory_name_id() {
		return category_name_id;
	}
	public void setCategory_name_id(String category_name_id) {
		this.category_name_id = category_name_id;
	}
	public String getCategory_name_cn() {
		return category_name_cn;
	}
	public void setCategory_name_cn(String category_name_cn) {
		this.category_name_cn = category_name_cn;
	}
}

