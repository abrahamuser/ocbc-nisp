package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.AuthPayment;

public class ObActionListRequest extends ObRequest implements Serializable
{
	 private String param_p;
	 private String param_c;
	 private String random_number;
	 
	 
	 private String mn_itm_id;
	 
	 
	 private String device_id;
	 private String action_cd;
	 private String push_token;
	 private String push_token_type;
	 private String device_type;
	 private String device_os;
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
	 
	 
}