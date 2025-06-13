package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryDeleteResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = -4575206579607502866L;
	
	private String successMessage;
	private String errorCode;
	private String errorMessage;
	private String responseBeneficiaryCode;
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
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
	public String getResponseBeneficiaryCode() {
		return responseBeneficiaryCode;
	}
	public void setResponseBeneficiaryCode(String responseBeneficiaryCode) {
		this.responseBeneficiaryCode = responseBeneficiaryCode;
	}
	
}
