package com.silverlake.mleb.mcb.bean.common;

import java.io.Serializable;
import java.util.List;

public class ObRoleGroupBean implements Serializable {
	private String roleGroupCode;
	private String roleGroupDescription;
	private List<ObRoleBean> roles;
	
	public String getRoleGroupCode() {
		return roleGroupCode;
	}
	public String getRoleGroupDescription() {
		return roleGroupDescription;
	}
	public List<ObRoleBean> getRoles() {
		return roles;
	}
	public void setRoleGroupCode(String roleGroupCode) {
		this.roleGroupCode = roleGroupCode;
	}
	public void setRoleGroupDescription(String roleGroupDescription) {
		this.roleGroupDescription = roleGroupDescription;
	}
	public void setRoles(List<ObRoleBean> roles) {
		this.roles = roles;
	}
}
