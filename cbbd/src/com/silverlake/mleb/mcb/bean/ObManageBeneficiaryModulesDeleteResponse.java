package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryModulesDeleteResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1810848001820026303L;
	
	String successMessage;
	String errorCode;
	String responseBeneficiaryCode;
	String errorMessage;

	public String getSuccessMessage()
	{
		return successMessage;
	}

	public void setSuccessMessage(String successMessage)
	{
		this.successMessage = successMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getResponseBeneficiaryCode() {
		return responseBeneficiaryCode;
	}

	public void setResponseBeneficiaryCode(String responseBeneficiaryCode) {
		this.responseBeneficiaryCode = responseBeneficiaryCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
