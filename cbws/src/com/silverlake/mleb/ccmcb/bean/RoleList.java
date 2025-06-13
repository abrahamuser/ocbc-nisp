package com.silverlake.mleb.ccmcb.bean;

import java.io.Serializable;
import java.util.List;

public class RoleList implements Serializable {
	private String roleGroupCode;
	private String roleCode;
	
	
	public String getRoleGroupCode() {
		return roleGroupCode;
	}
	public void setRoleGroupCode(String roleGroupCode) {
		this.roleGroupCode = roleGroupCode;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
}
