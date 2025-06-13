package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceConfWSBean implements Serializable
{
	private static final long serialVersionUID = 4182227357206290671L;
	private String index;
	private String maturityCode;

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getMaturityCode()
	{
		return maturityCode;
	}

	public void setMaturityCode(String maturityCode)
	{
		this.maturityCode = maturityCode;
	}

}