package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObRefreshMultiCurrencyBean implements Serializable
{
	private static final long serialVersionUID = 909581756853797121L;

	private String productCode;
	private String productName;
	private String accountCcy;
	private String accountNo;
	private String accountType;
	private BigDecimal balance;
	private BigDecimal availableBalance;
	private BigDecimal holdBalance;
	private BigDecimal overdraftBalance;
	private BigDecimal outstandingUnit;
	private String accountId;
	private String cif;

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

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}

	public BigDecimal getAvailableBalance()
	{
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance)
	{
		this.availableBalance = availableBalance;
	}

	public BigDecimal getHoldBalance()
	{
		return holdBalance;
	}

	public void setHoldBalance(BigDecimal holdBalance)
	{
		this.holdBalance = holdBalance;
	}

	public BigDecimal getOverdraftBalance()
	{
		return overdraftBalance;
	}

	public void setOverdraftBalance(BigDecimal overdraftBalance)
	{
		this.overdraftBalance = overdraftBalance;
	}

	public BigDecimal getOutstandingUnit()
	{
		return outstandingUnit;
	}

	public void setOutstandingUnit(BigDecimal outstandingUnit)
	{
		this.outstandingUnit = outstandingUnit;
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

}
