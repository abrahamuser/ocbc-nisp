package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObDeviceCifBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dateBind;
	private String dateUnBind;
	private String bindBy;
	private String unBindBy;
	private String unBindType;
	private String deviceStatus;
	private String cif;
	private String biometricStatus;
	private String pnsFlagStatus;
	private String devOs;
	private String devModel;
	private String devType;
	private String devIsRooted;
	private String devId;
	private String transId;
	private String index;
	private String aliasName;
	private String deviceLevel;
	private String lastLoginDate;
	private String approvalCountdown;
	
	public String getBiometricStatus() {
		return biometricStatus;
	}
	public void setBiometricStatus(String biometricStatus) {
		this.biometricStatus = biometricStatus;
	}
	
	
	public String getPnsFlagStatus() {
		return pnsFlagStatus;
	}
	public void setPnsFlagStatus(String pnsFlagStatus) {
		this.pnsFlagStatus = pnsFlagStatus;
	}
	
	public String getDateBind() {
		return dateBind;
	}
	public void setDateBind(String dateBind) {
		this.dateBind = dateBind;
	}
	public String getDateUnBind() {
		return dateUnBind;
	}
	public void setDateUnBind(String dateUnBind) {
		this.dateUnBind = dateUnBind;
	}
	public String getUnBindBy() {
		return unBindBy;
	}
	public void setUnBindBy(String unBindBy) {
		this.unBindBy = unBindBy;
	}
	public String getUnBindType() {
		return unBindType;
	}
	public void setUnBindType(String unBindType) {
		this.unBindType = unBindType;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getDevOs() {
		return devOs;
	}
	public void setDevOs(String devOs) {
		this.devOs = devOs;
	}
	public String getDevModel() {
		return devModel;
	}
	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	public String getDevIsRooted() {
		return devIsRooted;
	}
	public void setDevIsRooted(String devIsRooted) {
		this.devIsRooted = devIsRooted;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getBindBy() {
		return bindBy;
	}
	public void setBindBy(String bindBy) {
		this.bindBy = bindBy;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getDeviceLevel() {
		return deviceLevel;
	}
	public void setDeviceLevel(String deviceLevel) {
		this.deviceLevel = deviceLevel;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getApprovalCountdown() {
		return approvalCountdown;
	}
	public void setApprovalCountdown(String approvalCountdown) {
		this.approvalCountdown = approvalCountdown;
	}
	
	
}

