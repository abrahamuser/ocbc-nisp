package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountMaintenanceAckgtResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -242384176834496086L;

	private String productTypeCode;
	private String productTypeName;
	List<ObAccountMaintenanceAckgtResponseBean> obAccountMaintenanceAckgtResponseBeanList;

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

	public List<ObAccountMaintenanceAckgtResponseBean> getObAccountMaintenanceAckgtResponseBeanList()
	{
		return obAccountMaintenanceAckgtResponseBeanList;
	}

	public void setObAccountMaintenanceAckgtResponseBeanList(
			List<ObAccountMaintenanceAckgtResponseBean> obAccountMaintenanceAckgtResponseBeanList)
	{
		this.obAccountMaintenanceAckgtResponseBeanList = obAccountMaintenanceAckgtResponseBeanList;
	}

}
