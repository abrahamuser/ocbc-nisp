package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceConfBean implements Serializable
{
	private static final long serialVersionUID = 7374443201463728876L;
	private ObMaturityBean obMaturityBean;
	private ObDepositAccountBean obDepositAccountBean;

	public ObMaturityBean getObMaturityBean()
	{
		return obMaturityBean;
	}

	public void setObMaturityBean(ObMaturityBean obMaturityBean)
	{
		this.obMaturityBean = obMaturityBean;
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