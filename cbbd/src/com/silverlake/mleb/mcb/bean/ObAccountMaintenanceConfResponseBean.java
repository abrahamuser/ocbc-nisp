package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceConfResponseBean implements Serializable
{
	private static final long serialVersionUID = 3010578439632508127L;

	private String index;
	private String maturityLabelNew;
	private String maturityCodeNew;
	private String maturityNameNew;
	private String maturityWSCodeNew;
	private ObDepositAccountBean obDepositAccountBean;

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getMaturityLabelNew()
	{
		return maturityLabelNew;
	}

	public void setMaturityLabelNew(String maturityLabelNew)
	{
		this.maturityLabelNew = maturityLabelNew;
	}

	public String getMaturityCodeNew()
	{
		return maturityCodeNew;
	}

	public void setMaturityCodeNew(String maturityCodeNew)
	{
		this.maturityCodeNew = maturityCodeNew;
	}

	public String getMaturityNameNew()
	{
		return maturityNameNew;
	}

	public void setMaturityNameNew(String maturityNameNew)
	{
		this.maturityNameNew = maturityNameNew;
	}

	public String getMaturityWSCodeNew()
	{
		return maturityWSCodeNew;
	}

	public void setMaturityWSCodeNew(String maturityWSCodeNew)
	{
		this.maturityWSCodeNew = maturityWSCodeNew;
	}

	public ObDepositAccountBean getObDepositAccountBean()
	{
		return obDepositAccountBean;
	}

	public void setObDepositAccountBean(ObDepositAccountBean obDepositAccountBean)
	{
		this.obDepositAccountBean = obDepositAccountBean;
	}

}
