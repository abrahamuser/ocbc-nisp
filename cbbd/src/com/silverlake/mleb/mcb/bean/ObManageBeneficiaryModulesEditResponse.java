package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryModulesEditResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -6182297684883104941L;
	
	String successMessage;

	public String getSuccessMessage()
	{
		return successMessage;
	}

	public void setSuccessMessage(String successMessage)
	{
		this.successMessage = successMessage;
	}

}
