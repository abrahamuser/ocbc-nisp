package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObMaturityBean implements Serializable
{
	private static final long serialVersionUID = -2172217283609291469L;

	private String maturityCode;
	private String maturityValue;

	public String getMaturityCode()
	{
		return maturityCode;
	}

	public void setMaturityCode(String maturityCode)
	{
		this.maturityCode = maturityCode;
	}

	public String getMaturityValue()
	{
		return maturityValue;
	}

	public void setMaturityValue(String maturityValue)
	{
		this.maturityValue = maturityValue;
	}

}
