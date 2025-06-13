package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObListDebitAccountBean implements Serializable
{
	private static final long serialVersionUID = 3246657601838960733L;
	private String productCode;
	private String productName;
	private String accountCcy;
	private String accountNo;
	private String availableBalance;
	private String accountType;
	private String branchCode;
	private String mcBit;
	private String accountId;
	private String cif;
	private String labelDebitAccount;

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

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

	public String getAvailableBalance()
	{
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance)
	{
		this.availableBalance = availableBalance;
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

	public String getAccountId()
	{
		return accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getCif()
	{
		return cif;
	}

	public void setCif(String cif)
	{
		this.cif = cif;
	}

	public String getLabelDebitAccount()
	{
		return labelDebitAccount;
	}

	public void setLabelDebitAccount(String labelDebitAccount)
	{
		this.labelDebitAccount = labelDebitAccount;
	}

}
