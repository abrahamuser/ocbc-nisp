package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObInternetTransactionHistorySearchResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -18149330443225251L;

	private List<ObInternetTransactionHistorySearchBean> obInternetTransactionHistorySearchBeanList;

	public List<ObInternetTransactionHistorySearchBean> getObInternetTransactionHistorySearchBeanList()
	{
		return obInternetTransactionHistorySearchBeanList;
	}

	public void setObInternetTransactionHistorySearchBeanList(
			List<ObInternetTransactionHistorySearchBean> obInternetTransactionHistorySearchBeanList)
	{
		this.obInternetTransactionHistorySearchBeanList = obInternetTransactionHistorySearchBeanList;
	}

}
