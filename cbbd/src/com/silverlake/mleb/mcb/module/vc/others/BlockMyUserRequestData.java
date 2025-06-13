package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class BlockMyUserRequestData extends VCRequest {

	private String device_type;
	private String device_os;
	private String email;
	private String phone;
	
	

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	

}
