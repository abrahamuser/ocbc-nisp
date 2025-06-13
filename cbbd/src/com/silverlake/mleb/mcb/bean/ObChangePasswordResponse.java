package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObChangePasswordResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -2705538431922104730L;

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
