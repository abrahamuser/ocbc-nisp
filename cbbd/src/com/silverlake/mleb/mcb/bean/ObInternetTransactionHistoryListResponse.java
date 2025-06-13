package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObInternetTransactionHistoryListResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 7181851491698184578L;

	private List<ObListDebitAccountBean> ObListDebitAccountBeanList;

	private List<ObListParameterTransactionHistoryBean> obListParameterTransactionHistoryBeanList;

	public List<ObListDebitAccountBean> getObListDebitAccountBeanList()
	{
		return ObListDebitAccountBeanList;
	}

	public void setObListDebitAccountBeanList(
			List<ObListDebitAccountBean> obListDebitAccountBeanList)
	{
		ObListDebitAccountBeanList = obListDebitAccountBeanList;
	}

	public List<ObListParameterTransactionHistoryBean> getObListParameterTransactionHistoryBeanList()
	{
		return obListParameterTransactionHistoryBeanList;
	}

	public void setObListParameterTransactionHistoryBeanList(
			List<ObListParameterTransactionHistoryBean> obListParameterTransactionHistoryBeanList)
	{
		this.obListParameterTransactionHistoryBeanList = obListParameterTransactionHistoryBeanList;
	}

}
