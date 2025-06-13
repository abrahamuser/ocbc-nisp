package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountMaintenanceListBean implements Serializable
{
	private static final long serialVersionUID = 169294387770273167L;

	private String index;
	private String productCode;
	private ObDepositAccountBean depositAccount;
	private List<ObMaturityBean> maturityList;
	private String zakat;
	
	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public ObDepositAccountBean getDepositAccount()
	{
		return depositAccount;
	}

	public void setDepositAccount(ObDepositAccountBean depositAccount)
	{
		this.depositAccount = depositAccount;
	}

	public List<ObMaturityBean> getMaturityList()
	{
		return maturityList;
	}

	public void setMaturityList(List<ObMaturityBean> maturityList)
	{
		this.maturityList = maturityList;
	}

	public String getZakat() {
		return zakat;
	}

	public void setZakat(String zakat) {
		this.zakat = zakat;
	}
	
}
