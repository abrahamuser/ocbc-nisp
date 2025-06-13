package com.silverlake.mleb.mcb.bean.common;

import java.io.Serializable;
import java.util.List;

public class ObUserDataBean implements Serializable{
	private String recordID;
	private String userID;
	private String userCode;
	private String userName;
	private String prtOrgCode;
	private String orgCode;
	private String email;
	private String userProfileCode;
	private String userProfileName;
	private String userGroup;
	private String orgName;
	private String userStatusCode;
	private String userStateCode;
	private String authStatusCode;
	private String authStatus;
	private String expDate;
	private String isChangePass;
	private String isDelete;
	private String isLogged;
	private String isEmail;
	private String lastSentEmail;
	private Integer pinBlockFlag;
	private List<ObRoleGroupBean> listRole;
	private String maintenanceType;
	private String actionCode;
	private String makerAuthOwn;
	
	public String getRecordID() {
		return recordID;
	}
	public String getUserID() {
		return userID;
	}
	public String getUserCode() {
		return userCode;
	}
	public String getUserName() {
		return userName;
	}
	public String getPrtOrgCode() {
		return prtOrgCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public String getEmail() {
		return email;
	}
	public String getUserProfileCode() {
		return userProfileCode;
	}
	public String getUserProfileName() {
		return userProfileName;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public String getOrgName() {
		return orgName;
	}
	public String getUserStatusCode() {
		return userStatusCode;
	}
	public String getUserStateCode() {
		return userStateCode;
	}
	public String getAuthStatusCode() {
		return authStatusCode;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public List<ObRoleGroupBean> getListRole() {
		return listRole;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPrtOrgCode(String prtOrgCode) {
		this.prtOrgCode = prtOrgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserProfileCode(String userProfileCode) {
		this.userProfileCode = userProfileCode;
	}
	public void setUserProfileName(String userProfileName) {
		this.userProfileName = userProfileName;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public void setUserStatusCode(String userStatusCode) {
		this.userStatusCode = userStatusCode;
	}
	public void setUserStateCode(String userStateCode) {
		this.userStateCode = userStateCode;
	}
	public void setAuthStatusCode(String authStatusCode) {
		this.authStatusCode = authStatusCode;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getIsChangePass() {
		return isChangePass;
	}
	public void setIsChangePass(String isChangePass) {
		this.isChangePass = isChangePass;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getIsLogged() {
		return isLogged;
	}
	public void setIsLogged(String isLogged) {
		this.isLogged = isLogged;
	}
	public String getIsEmail() {
		return isEmail;
	}
	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}
	public String getLastSentEmail() {
		return lastSentEmail;
	}
	public void setLastSentEmail(String lastSentEmail) {
		this.lastSentEmail = lastSentEmail;
	}

	public Integer getPinBlockFlag() {
		return pinBlockFlag;
	}
	public void setPinBlockFlag(Integer pinBlockFlag) {
		this.pinBlockFlag = pinBlockFlag;
	}
	public void setListRole(List<ObRoleGroupBean> listRole) {
		this.listRole = listRole;
	}
	public String getMaintenanceType() {
		return maintenanceType;
	}
	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getMakerAuthOwn() {
		return makerAuthOwn;
	}
	public void setMakerAuthOwn(String makerAuthOwn) {
		this.makerAuthOwn = makerAuthOwn;
	}
	
	
}
