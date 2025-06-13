package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTimeDepositListResponseCurrencyBean implements Serializable
{
	private static final long serialVersionUID = 4274324688943197336L;

	private String currency;
	private String minAmount;
	private String maxAmount;
	private List<Integer> listTenor;
	private List<ObTimeDepositListResponseAccountBean> listAccount;

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getMinAmount()
	{
		return minAmount;
	}

	public void setMinAmount(String minAmount)
	{
		this.minAmount = minAmount;
	}

	public String getMaxAmount()
	{
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount)
	{
		this.maxAmount = maxAmount;
	}

	public List<Integer> getListTenor()
	{
		return listTenor;
	}

	public void setListTenor(List<Integer> listTenor)
	{
		this.listTenor = listTenor;
	}

	public List<ObTimeDepositListResponseAccountBean> getListAccount()
	{
		return listAccount;
	}

	public void setListAccount(List<ObTimeDepositListResponseAccountBean> listAccount)
	{
		this.listAccount = listAccount;
	}

}
