package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSelectAccountBean implements Serializable
{
	private static final long serialVersionUID = 7518083145453531047L;
	private String accountNo;
	private String accountCcy;

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getAccountCcy()
	{
		return accountCcy;
	}

	public void setAccountCcy(String accountCcy)
	{
		this.accountCcy = accountCcy;
	}

}
