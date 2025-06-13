package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ObTimeDepositListResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 7245942871229583708L;

	private Map<String, String> listProduct;
	private List<ObTimeDepositListResponseBean> obTimeDepositListResponseBeanList;
	private String termAndCondition;
	private String email;

	public Map<String, String> getListProduct()
	{
		return listProduct;
	}

	public void setListProduct(Map<String, String> listProduct)
	{
		this.listProduct = listProduct;
	}

	public List<ObTimeDepositListResponseBean> getObTimeDepositListResponseBeanList()
	{
		return obTimeDepositListResponseBeanList;
	}

	public void setObTimeDepositListResponseBeanList(
			List<ObTimeDepositListResponseBean> obTimeDepositListResponseBeanList)
	{
		this.obTimeDepositListResponseBeanList = obTimeDepositListResponseBeanList;
	}

	public String getTermAndCondition()
	{
		return termAndCondition;
	}

	public void setTermAndCondition(String termAndCondition)
	{
		this.termAndCondition = termAndCondition;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}