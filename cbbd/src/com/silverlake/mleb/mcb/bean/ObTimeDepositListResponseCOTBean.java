package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositListResponseCOTBean implements Serializable
{
	private static final long serialVersionUID = -7798127765573961994L;
	private String cotCode;
	private String cotFlag;
	private String cotStart;
	private String cotEnd;
	private String cotInfo;

	public String getCotCode()
	{
		return cotCode;
	}

	public void setCotCode(String cotCode)
	{
		this.cotCode = cotCode;
	}

	public String getCotFlag()
	{
		return cotFlag;
	}

	public void setCotFlag(String cotFlag)
	{
		this.cotFlag = cotFlag;
	}

	public String getCotStart()
	{
		return cotStart;
	}

	public void setCotStart(String cotStart)
	{
		this.cotStart = cotStart;
	}

	public String getCotEnd()
	{
		return cotEnd;
	}

	public void setCotEnd(String cotEnd)
	{
		this.cotEnd = cotEnd;
	}

	public String getCotInfo()
	{
		return cotInfo;
	}

	public void setCotInfo(String cotInfo)
	{
		this.cotInfo = cotInfo;
	}

}
