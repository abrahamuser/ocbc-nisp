package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class PaymentPurposeList implements Serializable {
	private String purpose_code;
    private String purpose_name_en;
    private String purpose_name_id;
	private String purpose_name_cn;
    private String description;
    
	public String getPurpose_code() {
		return purpose_code;
	}
	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}
	public String getPurpose_name_en() {
		return purpose_name_en;
	}
	public void setPurpose_name_en(String purpose_name_en) {
		this.purpose_name_en = purpose_name_en;
	}
	public String getPurpose_name_id() {
		return purpose_name_id;
	}
	public void setPurpose_name_id(String purpose_name_id) {
		this.purpose_name_id = purpose_name_id;
	}
	public String getPurpose_name_cn() {
		return purpose_name_cn;
	}
	public void setPurpose_name_cn(String purpose_name_cn) {
		this.purpose_name_cn = purpose_name_cn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

