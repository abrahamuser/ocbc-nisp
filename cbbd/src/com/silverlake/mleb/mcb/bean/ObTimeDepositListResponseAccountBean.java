package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositListResponseAccountBean implements Serializable
{
	private static final long serialVersionUID = 3576179725085656815L;
	private String accountKey;
	private String accountName;
	private String accountNo;
	private String accountCcy;
	private String balance;

	public String getAccountKey()
	{
		return accountKey;
	}

	public void setAccountKey(String accountKey)
	{
		this.accountKey = accountKey;
	}

	public String getAccountName()
	{
		return accountName;
	}

	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}

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

	public String getBalance()
	{
		return balance;
	}

	public void setBalance(String balance)
	{
		this.balance = balance;
	}

}
