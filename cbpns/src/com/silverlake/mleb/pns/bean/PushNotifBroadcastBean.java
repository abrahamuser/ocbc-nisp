package com.silverlake.mleb.pns.bean;

import java.io.Serializable;

public class PushNotifBroadcastBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String promoCode;
	private String promoUrl;
	private String creationDate;
	private String msgContent;
	private String deviceId;
	private String deviceToken;
	private String deviceType;
	private String deviceModel;
	
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getPromoUrl() {
		return promoUrl;
	}
	public void setPromoUrl(String promoUrl) {
		this.promoUrl = promoUrl;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
}
