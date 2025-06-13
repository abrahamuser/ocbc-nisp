package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObDebitAccountBean implements Serializable
{
	private static final long serialVersionUID = 8919271172869615179L;
	private String accountCcy;
	private String accountNo;
	private String accountType;
	private String branchCode;
	private String mcBit;

	public String getAccountCcy()
	{
		return accountCcy;
	}

	public void setAccountCcy(String accountCcy)
	{
		this.accountCcy = accountCcy;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getBranchCode()
	{
		return branchCode;
	}

	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}

	public String getMcBit()
	{
		return mcBit;
	}

	public void setMcBit(String mcBit)
	{
		this.mcBit = mcBit;
	}

}
