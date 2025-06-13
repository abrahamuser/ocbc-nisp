package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObChangePasswordRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -198602330103197604L;

	private String randomID;
	private String pString;
	private String cString;

	public String getRandomID()
	{
		return randomID;
	}

	public void setRandomID(String randomID)
	{
		this.randomID = randomID;
	}

	public String getpString()
	{
		return pString;
	}

	public void setpString(String pString)
	{
		this.pString = pString;
	}

	public String getcString()
	{
		return cString;
	}

	public void setcString(String cString)
	{
		this.cString = cString;
	}

}