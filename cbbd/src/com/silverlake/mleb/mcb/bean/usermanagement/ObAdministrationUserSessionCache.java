package com.silverlake.mleb.mcb.bean.usermanagement;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.module.vc.administration.UserManagementListResponseData;
import com.silverlake.mleb.mcb.module.vc.administration.UserRoleListResponseData;


public class ObAdministrationUserSessionCache extends ObSessionCache implements Serializable {
		
	private UserManagementListResponseData userManagementListResponseData;
	private UserRoleListResponseData userRoleListResponseData;

	
	public UserManagementListResponseData getUserManagementListResponseData() {
		return userManagementListResponseData;
	}

	public void setUserManagementListResponseData(UserManagementListResponseData userManagementListResponseData) {
		this.userManagementListResponseData = userManagementListResponseData;
	}

	public UserRoleListResponseData getUserRoleListResponseData() {
		return userRoleListResponseData;
	}

	public void setUserRoleListResponseData(UserRoleListResponseData userRoleListResponseData) {
		this.userRoleListResponseData = userRoleListResponseData;
	}
	

	
}
