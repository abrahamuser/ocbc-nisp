package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObEstatementCreditCardPDFRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 6356574869468005390L;
	private String ccNumber;
	private String year;
	private String month;

	public String getCcNumber()
	{
		return ccNumber;
	}

	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

}