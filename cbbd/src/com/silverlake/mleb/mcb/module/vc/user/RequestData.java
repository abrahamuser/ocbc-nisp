package com.silverlake.mleb.mcb.module.vc.user;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class RequestData extends VCRequest
{
	 private String auth_type;
	 private String param_p;
	 private String param_c;
	 private String random_number;
	 private String failctr;
	 
	 private String mn_itm_id;
	 
	 
	 private String device_id;
	 private String action_cd;
	 private String push_token;
	 private String push_token_type;
	 private String device_type;
	 private String device_os;
	 private String is_primary;
	 
	 private String email;
	 private String phone;
	 private String lang;
	 private String Biometric_type;
	 private List<List_device> list_active_device;
	 private String password_data_block;
 
	public String getParam_p() {
		return param_p;
	}
	public void setParam_p(String param_p) {
		this.param_p = param_p;
	}
	public String getParam_c() {
		return param_c;
	}
	public void setParam_c(String param_c) {
		this.param_c = param_c;
	}
	public String getRandom_number() {
		return random_number;
	}
	public void setRandom_number(String random_number) {
		this.random_number = random_number;
	}
	public String getMn_itm_id() {
		return mn_itm_id;
	}
	public void setMn_itm_id(String mn_itm_id) {
		this.mn_itm_id = mn_itm_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public String getPush_token() {
		return push_token;
	}
	public void setPush_token(String push_token) {
		this.push_token = push_token;
	}
	public String getPush_token_type() {
		return push_token_type;
	}
	public void setPush_token_type(String push_token_type) {
		this.push_token_type = push_token_type;
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
	public String getIs_primary() {
		return is_primary;
	}
	public void setIs_primary(String is_primary) {
		this.is_primary = is_primary;
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
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}
	public String getFailctr() {
		return failctr;
	}
	public void setFailctr(String failctr) {
		this.failctr = failctr;
	}
	public String getBiometric_type() {
		return Biometric_type;
	}
	public void setBiometric_type(String biometric_type) {
		Biometric_type = biometric_type;
	}
	public List<List_device> getList_active_device() {
		return list_active_device;
	}
	public void setList_active_device(List<List_device> list_active_device) {
		this.list_active_device = list_active_device;
	}
	public String getPassword_data_block() {
		return password_data_block;
	}
	public void setPassword_data_block(String password_data_block) {
		this.password_data_block = password_data_block;
	}
	 

 
 
}



	
