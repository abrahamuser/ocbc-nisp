package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObProductTypeBean implements Serializable
{
	private static final long serialVersionUID = -960345164656536234L;

	private String productTypeCode;
	private String productTypeName;

	public String getProductTypeCode()
	{
		return productTypeCode;
	}

	public void setProductTypeCode(String productTypeCode)
	{
		this.productTypeCode = productTypeCode;
	}

	public String getProductTypeName()
	{
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName)
	{
		this.productTypeName = productTypeName;
	}

}
