package com.silverlake.mleb.mcb.module.vc.registration;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class RegistrationInquiryResponseData extends VCResponseData {
	private RegistrationData registration_data;
	private Boolean allow_cancel;
	private Boolean allow_edit;
	private Boolean allow_ack;
	private String role;
	private String verificationNo;
	private String revisionNo;
	private String ktpNo;
	
	
	public RegistrationData getRegistration_data() {
		return registration_data;
	}
	public void setRegistration_data(RegistrationData registration_data) {
		this.registration_data = registration_data;
	}
	public Boolean getAllow_cancel() {
		return allow_cancel;
	}
	public void setAllow_cancel(Boolean allow_cancel) {
		this.allow_cancel = allow_cancel;
	}
	public Boolean getAllow_edit() {
		return allow_edit;
	}
	public void setAllow_edit(Boolean allow_edit) {
		this.allow_edit = allow_edit;
	}
	public Boolean getAllow_ack() {
		return allow_ack;
	}
	public void setAllow_ack(Boolean allow_ack) {
		this.allow_ack = allow_ack;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getVerificationNo() {
		return verificationNo;
	}
	public void setVerificationNo(String verificationNo) {
		this.verificationNo = verificationNo;
	}
	public String getRevisionNo() {
		return revisionNo;
	}
	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}
	public String getKtpNo() {
		return ktpNo;
	}
	public void setKtpNo(String ktpNo) {
		this.ktpNo = ktpNo;
	}
	
	
}
