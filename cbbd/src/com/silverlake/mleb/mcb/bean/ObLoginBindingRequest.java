package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObLoginBindingRequest extends ObRequest implements Serializable{
	private static final long serialVersionUID = 557456619771020947L;
	private String action;
	private String cifId;
	private String deviceId;
	private Boolean deviceIsRooted;
	private String deviceModel;
	private String deviceType;
	private String deviceOs;
	private String userId;
	private String refNo;
	private String passCode;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getPassCode() {
		return passCode;
	}
	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Boolean getDeviceIsRooted() {
		return deviceIsRooted;
	}
	public void setDeviceIsRooted(Boolean deviceIsRooted) {
		this.deviceIsRooted = deviceIsRooted;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceOs() {
		return deviceOs;
	}
	public void setDeviceOs(String deviceOs) {
		this.deviceOs = deviceOs;
	}
	public String getCifId() {
		return cifId;
	}
	public void setCifId(String cifId) {
		this.cifId = cifId;
	}

	

}