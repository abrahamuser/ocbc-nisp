package com.silverlake.mleb.pns.bean;

import java.io.Serializable;

import com.silverlake.hlb.mib.bean.ObResponse;

public class ObPNSResponseBean extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1L;

	 private String statusCode;
	 
	 private String statusMessage;
	 
	 private String reponseTime;
	 
	 private String referenceId;
	 
	 private String multicastId;
	 
	 private String errorCode;
	 
	 private String storeMessageId;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getReponseTime() {
		return reponseTime;
	}

	public void setReponseTime(String reponseTime) {
		this.reponseTime = reponseTime;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(String multicastId) {
		this.multicastId = multicastId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStoreMessageId() {
		return storeMessageId;
	}

	public void setStoreMessageId(String storeMessageId) {
		this.storeMessageId = storeMessageId;
	}
}
