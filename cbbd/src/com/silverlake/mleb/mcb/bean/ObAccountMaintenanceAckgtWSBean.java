package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceAckgtWSBean implements Serializable
{
	private static final long serialVersionUID = -8996068725523035334L;
	private String index;

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}
}