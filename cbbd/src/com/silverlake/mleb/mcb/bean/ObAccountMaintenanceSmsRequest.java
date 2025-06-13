package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceSmsRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -8597370758629523328L;

	private int resendTagCount;

	public int getResendTagCount()
	{
		return resendTagCount;
	}

	public void setResendTagCount(int resendTagCount)
	{
		this.resendTagCount = resendTagCount;
	}

}