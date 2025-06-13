package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObInternetTransactionHistoryDetailRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -8706353337262518475L;

	private String transactionHistoryId;

	private String paramCode;

	public String getTransactionHistoryId()
	{
		return transactionHistoryId;
	}

	public void setTransactionHistoryId(String transactionHistoryId)
	{
		this.transactionHistoryId = transactionHistoryId;
	}

	public String getParamCode()
	{
		return paramCode;
	}

	public void setParamCode(String paramCode)
	{
		this.paramCode = paramCode;
	}

}
