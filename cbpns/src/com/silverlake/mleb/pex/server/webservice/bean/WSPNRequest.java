
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;
import java.util.List;



public class WSPNRequest extends WSRequest
    implements Serializable {

	private final static long serialVersionUID = 1L;

	private String apiKey;
	private String notificationType;
	private String messageType;
	private String referenceId;
	private String appId;
	private String appPass;
	private String deviceToken;
    private String message;
    private String connCode;
    private String channel;
    private String badge;
    private String timeToLive;
    private String title;
	private String type;
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getConnCode() {
		return connCode;
	}
	public void setConnCode(String connCode) {
		this.connCode = connCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public String getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(String timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppPass() {
		return appPass;
	}
	public void setAppPass(String appPass) {
		this.appPass = appPass;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	

}
