package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObUpdateUserProfileResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -3700067707770219239L;

	private String responseCode;

	public String getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(String responseCode)
	{
		this.responseCode = responseCode;
	}

}
