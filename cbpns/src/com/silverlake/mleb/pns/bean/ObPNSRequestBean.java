package com.silverlake.mleb.pns.bean;

import java.io.Serializable;

import com.silverlake.hlb.mib.bean.ObRequest;

public class ObPNSRequestBean extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String header;
	
	private String content;
	
	private String deviceToken;
	
	private String template;
	
	private String requestId;
	
	private String cif;
	
	private String deviceId;
	private String appId;
	private String appPass;
	private String type;
	
	//P4OCBCSIT-22
	private String messageType;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	
}
