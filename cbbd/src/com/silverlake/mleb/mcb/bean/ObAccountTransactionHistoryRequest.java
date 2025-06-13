package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountTransactionHistoryRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1434658768674979996L;

	private String userCif;
	private String userName;
	private String accountNo;
	private String accountCcy;
	private String ccNumber;
	private String index;
	private String accountType;
	private String startDate;
	private String endDate;
	private String page;
	private String productCode;
	private String productName;
	private String countLastTransactions;
	private Boolean isSyariahProduct;

	public String getUserCif()
	{
		return userCif;
	}

	public void setUserCif(String userCif)
	{
		this.userCif = userCif;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
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

	public String getCcNumber()
	{
		return ccNumber;
	}

	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
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

	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

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

	public String getCountLastTransactions()
	{
		return countLastTransactions;
	}

	public void setCountLastTransactions(String countLastTransactions)
	{
		this.countLastTransactions = countLastTransactions;
	}

	public Boolean getIsSyariahProduct() {
		return isSyariahProduct;
	}

	public void setIsSyariahProduct(Boolean isSyariahProduct) {
		this.isSyariahProduct = isSyariahProduct;
	}

}