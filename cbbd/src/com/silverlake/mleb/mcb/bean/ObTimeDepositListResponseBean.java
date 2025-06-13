package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ObTimeDepositListResponseBean extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -5395555096748714687L;

	private String productCode;
	private List<String> listCurrency;
	private Map<String, String> listMaturity;
	private Map<String, String> listPurpose;
	private Map<String, String> listSourceofFund;
	private List<ObTimeDepositListResponseCurrencyBean> obTimeDepositListResponseCurrencyBeanList;
	private List<ObTimeDepositListResponseCOTBean> obTimeDepositListResponseCOTBeanList;

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public List<String> getListCurrency()
	{
		return listCurrency;
	}

	public void setListCurrency(List<String> listCurrency)
	{
		this.listCurrency = listCurrency;
	}

	public Map<String, String> getListMaturity()
	{
		return listMaturity;
	}

	public void setListMaturity(Map<String, String> listMaturity)
	{
		this.listMaturity = listMaturity;
	}

	public Map<String, String> getListPurpose()
	{
		return listPurpose;
	}

	public void setListPurpose(Map<String, String> listPurpose)
	{
		this.listPurpose = listPurpose;
	}

	public Map<String, String> getListSourceofFund()
	{
		return listSourceofFund;
	}

	public void setListSourceofFund(Map<String, String> listSourceofFund)
	{
		this.listSourceofFund = listSourceofFund;
	}

	public List<ObTimeDepositListResponseCurrencyBean> getObTimeDepositListResponseCurrencyBeanList()
	{
		return obTimeDepositListResponseCurrencyBeanList;
	}

	public void setObTimeDepositListResponseCurrencyBeanList(
			List<ObTimeDepositListResponseCurrencyBean> obTimeDepositListResponseCurrencyBeanList)
	{
		this.obTimeDepositListResponseCurrencyBeanList = obTimeDepositListResponseCurrencyBeanList;
	}

	public List<ObTimeDepositListResponseCOTBean> getObTimeDepositListResponseCOTBeanList()
	{
		return obTimeDepositListResponseCOTBeanList;
	}

	public void setObTimeDepositListResponseCOTBeanList(
			List<ObTimeDepositListResponseCOTBean> obTimeDepositListResponseCOTBeanList)
	{
		this.obTimeDepositListResponseCOTBeanList = obTimeDepositListResponseCOTBeanList;
	}

}
