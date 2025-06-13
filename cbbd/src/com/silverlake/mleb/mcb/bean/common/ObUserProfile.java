package com.silverlake.mleb.mcb.bean.common;

import java.io.Serializable;


public class ObUserProfile implements Serializable{
	
	private String profileCode;
	private String profileDescription;
	private Integer version;
	private String profileRecordID;
	
	
	public String getProfileCode() {
		return profileCode;
	}
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}
	public String getProfileDescription() {
		return profileDescription;
	}
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getProfileRecordID() {
		return profileRecordID;
	}
	public void setProfileRecordID(String profileRecordID) {
		this.profileRecordID = profileRecordID;
	}
	
	
	
	}
