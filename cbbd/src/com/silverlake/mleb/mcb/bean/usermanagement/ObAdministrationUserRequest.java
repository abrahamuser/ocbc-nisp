package com.silverlake.mleb.mcb.bean.usermanagement;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ObRequestCache;
import com.silverlake.mleb.mcb.bean.RoleList;


public class ObAdministrationUserRequest extends ObRequestCache<ObAdministrationUserSessionCache> implements Serializable {

     
	private String userCode;
    private String userName;
    private String pageNo;
    private String pageSize;
    private String userId;
    private String actionCode;
    private String userProfileCode;
    private String userEmail;
    private List<RoleList> roleList;
    private String authOwn;
    
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getUserProfileCode() {
		return userProfileCode;
	}
	public void setUserProfileCode(String userProfileCode) {
		this.userProfileCode = userProfileCode;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public List<RoleList> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleList> roleList) {
		this.roleList = roleList;
	}
	public String getAuthOwn() {
		return authOwn;
	}
	public void setAuthOwn(String authOwn) {
		this.authOwn = authOwn;
	}
	
   
}
