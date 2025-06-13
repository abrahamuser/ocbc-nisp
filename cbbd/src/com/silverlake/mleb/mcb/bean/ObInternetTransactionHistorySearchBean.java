package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObInternetTransactionHistorySearchBean implements Serializable
{
	private static final long serialVersionUID = -7381091460932487366L;
	private String id;
	private String refNo;
	private String trxCcy;
	private String trxAmount;
	private String trxDate;
	private String trxType;
	private String statusDesc;
	private String remark;
	private String paramCode;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getRefNo()
	{
		return refNo;
	}

	public void setRefNo(String refNo)
	{
		this.refNo = refNo;
	}

	public String getTrxCcy()
	{
		return trxCcy;
	}

	public void setTrxCcy(String trxCcy)
	{
		this.trxCcy = trxCcy;
	}

	public String getTrxAmount()
	{
		return trxAmount;
	}

	public void setTrxAmount(String trxAmount)
	{
		this.trxAmount = trxAmount;
	}

	public String getTrxDate()
	{
		return trxDate;
	}

	public void setTrxDate(String trxDate)
	{
		this.trxDate = trxDate;
	}

	public String getStatusDesc()
	{
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc)
	{
		this.statusDesc = statusDesc;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getTrxType()
	{
		return trxType;
	}

	public void setTrxType(String trxType)
	{
		this.trxType = trxType;
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
