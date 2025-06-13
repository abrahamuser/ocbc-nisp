package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class RequestData extends VCRequest {
	//Faq
	private String faq_cat_cd;
	//FaqFeedback
	private String faq_id;
	private String feedback;
	private String device_id;
	private String device_type;
	private String device_os;
	
	public String getFaq_cat_cd() {
		return faq_cat_cd;
	}
	public void setFaq_cat_cd(String faq_cat_cd) {
		this.faq_cat_cd = faq_cat_cd;
	}
	public String getFaq_id() {
		return faq_id;
	}
	public void setFaq_id(String faq_id) {
		this.faq_id = faq_id;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_os() {
		return device_os;
	}
	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}

	

}