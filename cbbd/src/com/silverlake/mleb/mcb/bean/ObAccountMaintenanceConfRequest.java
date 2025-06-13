package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountMaintenanceConfRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 2929864113308250818L;

	private String productTypeCode;
	private String productTypeName;
	private List<ObAccountMaintenanceConfBean> obAccountMaintenanceConfBeanList;
	private Boolean isSyariahProduct;

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

	public List<ObAccountMaintenanceConfBean> getObAccountMaintenanceConfBeanList()
	{
		return obAccountMaintenanceConfBeanList;
	}

	public void setObAccountMaintenanceConfBeanList(
			List<ObAccountMaintenanceConfBean> obAccountMaintenanceConfBeanList)
	{
		this.obAccountMaintenanceConfBeanList = obAccountMaintenanceConfBeanList;
	}

	public Boolean getIsSyariahProduct() {
		return isSyariahProduct;
	}

	public void setIsSyariahProduct(Boolean isSyariahProduct) {
		this.isSyariahProduct = isSyariahProduct;
	}

}