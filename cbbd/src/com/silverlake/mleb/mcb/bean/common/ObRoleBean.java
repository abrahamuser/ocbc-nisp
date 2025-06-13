package com.silverlake.mleb.mcb.bean.common;

import java.io.Serializable;

public class ObRoleBean implements Serializable {
	private String roleCode;
	private String roleBase;
	private String roleDescription;
	private Integer version;
	
	public String getRoleCode() {
		return roleCode;
	}
	public String getRoleBase() {
		return roleBase;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public Integer getVersion() {
		return version;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public void setRoleBase(String roleBase) {
		this.roleBase = roleBase;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}	
}
