package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSendFeedbackFaqRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -3822270038236396582L;


	private String faq_id;
	private String feedback;
	//Conditional
	private String org_cd;
	private String usr_cd;
	//
	private String device_id;
	private String device_type;
	private String device_os;
	
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
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getUsr_cd() {
		return usr_cd;
	}
	public void setUsr_cd(String usr_cd) {
		this.usr_cd = usr_cd;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}