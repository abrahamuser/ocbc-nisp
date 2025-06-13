package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountMaintenanceAckgtRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -7208990592617310522L;

	private String smsToken;
	private String productTypeCode;
	private String productTypeName;
	private List<ObAccountMaintenanceAckgtBean> obAccountMaintenanceAckgtBeanList;
	private Boolean isSyariahProduct;

	public String getSmsToken()
	{
		return smsToken;
	}

	public void setSmsToken(String smsToken)
	{
		this.smsToken = smsToken;
	}

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

	public List<ObAccountMaintenanceAckgtBean> getObAccountMaintenanceAckgtBeanList()
	{
		return obAccountMaintenanceAckgtBeanList;
	}

	public void setObAccountMaintenanceAckgtBeanList(
			List<ObAccountMaintenanceAckgtBean> obAccountMaintenanceAckgtBeanList)
	{
		this.obAccountMaintenanceAckgtBeanList = obAccountMaintenanceAckgtBeanList;
	}

	public Boolean getIsSyariahProduct() {
		return isSyariahProduct;
	}

	public void setIsSyariahProduct(Boolean isSyariahProduct) {
		this.isSyariahProduct = isSyariahProduct;
	}

}