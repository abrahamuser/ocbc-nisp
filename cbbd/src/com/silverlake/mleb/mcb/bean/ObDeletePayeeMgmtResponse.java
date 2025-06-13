package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;

public class ObDeletePayeeMgmtResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;
	private String successMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
}