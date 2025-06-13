package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceSmsResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -2769931069596083341L;

	private String successCode;
	private int resendTagCount;

	public String getSuccessCode()
	{
		return successCode;
	}

	public void setSuccessCode(String successCode)
	{
		this.successCode = successCode;
	}

	public int getResendTagCount()
	{
		return resendTagCount;
	}

	public void setResendTagCount(int resendTagCount)
	{
		this.resendTagCount = resendTagCount;
	}

}
