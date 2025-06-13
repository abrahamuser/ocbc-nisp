package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTransactionHistoryPDFRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1325730787338621432L;

	private String filterCategory;
	private String debitAccountNo;
	private String debitAccountCcy;
	private String accountType;
	private String ccNumber;
	private String filterRange;
	private String startDate;
	private String endDate;

	public String getFilterCategory()
	{
		return filterCategory;
	}

	public void setFilterCategory(String filterCategory)
	{
		this.filterCategory = filterCategory;
	}

	public String getDebitAccountNo()
	{
		return debitAccountNo;
	}

	public void setDebitAccountNo(String debitAccountNo)
	{
		this.debitAccountNo = debitAccountNo;
	}

	public String getDebitAccountCcy()
	{
		return debitAccountCcy;
	}

	public void setDebitAccountCcy(String debitAccountCcy)
	{
		this.debitAccountCcy = debitAccountCcy;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getCcNumber()
	{
		return ccNumber;
	}

	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}

	public String getFilterRange()
	{
		return filterRange;
	}

	public void setFilterRange(String filterRange)
	{
		this.filterRange = filterRange;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

}