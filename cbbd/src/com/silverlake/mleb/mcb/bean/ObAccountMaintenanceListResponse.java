package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountMaintenanceListResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -5035325981525403112L;

	private List<ObProductTypeBean> productTypeList;
	private List<ObAccountMaintenanceListBean> obAccountMaintenanceListBeanList;

	public List<ObProductTypeBean> getProductTypeList()
	{
		return productTypeList;
	}

	public void setProductTypeList(List<ObProductTypeBean> productTypeList)
	{
		this.productTypeList = productTypeList;
	}

	public List<ObAccountMaintenanceListBean> getObAccountMaintenanceListBeanList()
	{
		return obAccountMaintenanceListBeanList;
	}

	public void setObAccountMaintenanceListBeanList(
			List<ObAccountMaintenanceListBean> obAccountMaintenanceListBeanList)
	{
		this.obAccountMaintenanceListBeanList = obAccountMaintenanceListBeanList;
	}

}
