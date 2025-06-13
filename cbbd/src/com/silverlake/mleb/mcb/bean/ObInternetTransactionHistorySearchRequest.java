package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObInternetTransactionHistorySearchRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 2705623792164495291L;

	private List<ObSelectAccountBean> obSelectAccountBeanList;

	private List<String> selectedMenuActionList;

	private String fromDate;

	private String toDate;

	public List<ObSelectAccountBean> getObSelectAccountBeanList()
	{
		return obSelectAccountBeanList;
	}

	public void setObSelectAccountBeanList(List<ObSelectAccountBean> obSelectAccountBeanList)
	{
		this.obSelectAccountBeanList = obSelectAccountBeanList;
	}

	public List<String> getSelectedMenuActionList()
	{
		return selectedMenuActionList;
	}

	public void setSelectedMenuActionList(List<String> selectedMenuActionList)
	{
		this.selectedMenuActionList = selectedMenuActionList;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

}
