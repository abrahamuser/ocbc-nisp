package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountMaintenanceConfResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -1998693520603745408L;

	private String productTypeCode;
	private String productTypeName;
	List<ObAccountMaintenanceConfResponseBean> obAccountMaintenanceConfResponseBeanList;

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

	public List<ObAccountMaintenanceConfResponseBean> getObAccountMaintenanceConfResponseBeanList()
	{
		return obAccountMaintenanceConfResponseBeanList;
	}

	public void setObAccountMaintenanceConfResponseBeanList(
			List<ObAccountMaintenanceConfResponseBean> obAccountMaintenanceConfResponseBeanList)
	{
		this.obAccountMaintenanceConfResponseBeanList = obAccountMaintenanceConfResponseBeanList;
	}

}
