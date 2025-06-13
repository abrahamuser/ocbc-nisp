package com.silverlake.mleb.pex.bean;

import java.io.Serializable;




public class ObEAIHeader implements Serializable
{

	String responseCode;
	String errorResponseCode;
	String errorMsg;
	String ocmRefNo;
	String reasonCode;
	String beneName;
	
	
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorResponseCode() {
		return errorResponseCode;
	}
	public void setErrorResponseCode(String errorResponseCode) {
		this.errorResponseCode = errorResponseCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getOcmRefNo() {
		return ocmRefNo;
	}
	public void setOcmRefNo(String ocmRefNo) {
		this.ocmRefNo = ocmRefNo;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
	
	
	
}
