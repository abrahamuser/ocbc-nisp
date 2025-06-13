package com.silverlake.mleb.mcb.bean.usermanagement;

import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.bean.common.ObUserDataBean;
import com.silverlake.mleb.mcb.bean.common.ObUserProfile;

import java.io.Serializable;
import java.util.List;

public class ObAdministrationUserResponse extends ObResponseCache implements Serializable {
	
	private List<ObUserDataBean> userDataList;
	private Integer totalRecords;
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
	private List<ObUserProfile> profileList;
	
	
		
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<ObUserDataBean> getUserDataList() {
		return userDataList;
	}
	public void setUserDataList(List<ObUserDataBean> userDataList) {
		this.userDataList = userDataList;
	}
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
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
	public String getPrtOrgCode() {
		return prtOrgCode;
	}
	public void setPrtOrgCode(String prtOrgCode) {
		this.prtOrgCode = prtOrgCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserProfileCode() {
		return userProfileCode;
	}
	public void setUserProfileCode(String userProfileCode) {
		this.userProfileCode = userProfileCode;
	}
	public String getUserProfileName() {
		return userProfileName;
	}
	public void setUserProfileName(String userProfileName) {
		this.userProfileName = userProfileName;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserStatusCode() {
		return userStatusCode;
	}
	public void setUserStatusCode(String userStatusCode) {
		this.userStatusCode = userStatusCode;
	}
	public String getUserStateCode() {
		return userStateCode;
	}
	public void setUserStateCode(String userStateCode) {
		this.userStateCode = userStateCode;
	}
	public String getAuthStatusCode() {
		return authStatusCode;
	}
	public void setAuthStatusCode(String authStatusCode) {
		this.authStatusCode = authStatusCode;
	}
	public String getAuthStatus() {
		return authStatus;
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
	public List<ObRoleGroupBean> getListRole() {
		return listRole;
	}
	public void setListRole(List<ObRoleGroupBean> listRole) {
		this.listRole = listRole;
	}
	public List<ObUserProfile> getProfileList() {
		return profileList;
	}
	public void setProfileList(List<ObUserProfile> profileList) {
		this.profileList = profileList;
	}
	
	  
}
