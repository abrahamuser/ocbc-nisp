package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class ResidentStatusList implements Serializable {
	private String res_code;
    private String res_name_en;
    private String res_name_id;
    private String res_name_cn;
    
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	public String getRes_name_en() {
		return res_name_en;
	}
	public void setRes_name_en(String res_name_en) {
		this.res_name_en = res_name_en;
	}
	public String getRes_name_id() {
		return res_name_id;
	}
	public void setRes_name_id(String res_name_id) {
		this.res_name_id = res_name_id;
	}
	public String getRes_name_cn() {
		return res_name_cn;
	}
	public void setRes_name_cn(String res_name_cn) {
		this.res_name_cn = res_name_cn;
	}
}

