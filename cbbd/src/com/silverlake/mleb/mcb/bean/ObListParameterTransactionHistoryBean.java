package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Map;

public class ObListParameterTransactionHistoryBean implements Serializable
{
	private static final long serialVersionUID = 2519461690514573599L;

	private String paramCode;

	private String paramDesc;

	private Boolean isEnable;

	private Map<String, String> parameterSub;

	public String getParamCode()
	{
		return paramCode;
	}

	public void setParamCode(String paramCode)
	{
		this.paramCode = paramCode;
	}

	public String getParamDesc()
	{
		return paramDesc;
	}

	public void setParamDesc(String paramDesc)
	{
		this.paramDesc = paramDesc;
	}

	public Boolean getIsEnable()
	{
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable)
	{
		this.isEnable = isEnable;
	}

	public Map<String, String> getParameterSub()
	{
		return parameterSub;
	}

	public void setParameterSub(Map<String, String> parameterSub)
	{
		this.parameterSub = parameterSub;
	}

}
