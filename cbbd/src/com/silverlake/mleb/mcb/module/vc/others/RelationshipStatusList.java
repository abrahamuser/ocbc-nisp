package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class RelationshipStatusList implements Serializable {
	private String rel_code;
    private String rel_name_en;
    private String rel_name_id;
	private String rel_name_cn;
    
	public String getRel_code() {
		return rel_code;
	}
	public void setRel_code(String rel_code) {
		this.rel_code = rel_code;
	}
	public String getRel_name_en() {
		return rel_name_en;
	}
	public void setRel_name_en(String rel_name_en) {
		this.rel_name_en = rel_name_en;
	}
	public String getRel_name_id() {
		return rel_name_id;
	}
	public void setRel_name_id(String rel_name_id) {
		this.rel_name_id = rel_name_id;
	}
	public String getRel_name_cn() {
		return rel_name_cn;
	}
	public void setRel_name_cn(String rel_name_cn) {
		this.rel_name_cn = rel_name_cn;
	}
}

