package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class LLDRequestData extends VCRequest {
	private String lang;
	private String purpose_code;
	private String bene_country_code;
	private String bene_category_code;
	private String remitter_country_code;
	private String remitter_category_code;
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getPurpose_code() {
		return purpose_code;
	}

	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}

	public String getBene_country_code() {
		return bene_country_code;
	}

	public void setBene_country_code(String bene_country_code) {
		this.bene_country_code = bene_country_code;
	}

	public String getBene_category_code() {
		return bene_category_code;
	}

	public void setBene_category_code(String bene_category_code) {
		this.bene_category_code = bene_category_code;
	}

	public String getRemitter_country_code() {
		return remitter_country_code;
	}

	public void setRemitter_country_code(String remitter_country_code) {
		this.remitter_country_code = remitter_country_code;
	}

	public String getRemitter_category_code() {
		return remitter_category_code;
	}

	public void setRemitter_category_code(String remitter_category_code) {
		this.remitter_category_code = remitter_category_code;
	}
}