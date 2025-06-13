package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class InquiryTransTimeBatchRequestData extends VCRequest {
		
	private String lang;
	private List<String> prod_cd_list;
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public List<String> getProd_cd_list() {
		return prod_cd_list;
	}
	public void setProd_cd_list(List<String> prod_cd_list) {
		this.prod_cd_list = prod_cd_list;
	}
	
	
	
}