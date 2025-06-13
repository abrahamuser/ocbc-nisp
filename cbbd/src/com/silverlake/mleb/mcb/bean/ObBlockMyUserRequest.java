package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


import com.silverlake.mleb.mcb.bean.ObRequestCache;



public class ObBlockMyUserRequest extends ObRequestCache<ObBlockMyUserCache>
		implements Serializable {

	private String org_cd;
	private String usr_cd;
	private String email;
	private String phone;
	
	
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
