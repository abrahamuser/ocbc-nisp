package com.silverlake.mleb.mcb.module.vc.registration;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class RegistrationSubmitAckRequestData extends VCRequest {
	
	private String record_id;
	private Integer version_no;
	private String registration_no;
	private String verification_no;
	private InputterDetails user_details;
	private String device_id;
	private String device_type;
	private String device_os;
	
		
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public Integer getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Integer version_no) {
		this.version_no = version_no;
	}
	public String getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}
	public InputterDetails getUser_details() {
		return user_details;
	}
	public void setUser_details(InputterDetails user_details) {
		this.user_details = user_details;
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
	public String getVerification_no() {
		return verification_no;
	}
	public void setVerification_no(String verification_no) {
		this.verification_no = verification_no;
	}
	
	
		
}
