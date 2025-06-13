package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceAckgtBean implements Serializable
{
	private static final long serialVersionUID = -2015203211044927783L;

	private String newMaturityLabel;
	private String newMaturityCode;
	private String newMaturityName;
	private String newMaturityWSCode;
	private ObDepositAccountBean obDepositAccountBean;
	private String maturityName;
	private String zakat;

	public String getNewMaturityLabel()
	{
		return newMaturityLabel;
	}

	public void setNewMaturityLabel(String newMaturityLabel)
	{
		this.newMaturityLabel = newMaturityLabel;
	}

	public String getNewMaturityCode()
	{
		return newMaturityCode;
	}

	public void setNewMaturityCode(String newMaturityCode)
	{
		this.newMaturityCode = newMaturityCode;
	}

	public String getNewMaturityName()
	{
		return newMaturityName;
	}

	public void setNewMaturityName(String newMaturityName)
	{
		this.newMaturityName = newMaturityName;
	}

	public String getNewMaturityWSCode()
	{
		return newMaturityWSCode;
	}

	public void setNewMaturityWSCode(String newMaturityWSCode)
	{
		this.newMaturityWSCode = newMaturityWSCode;
	}

	public ObDepositAccountBean getObDepositAccountBean()
	{
		return obDepositAccountBean;
	}

	public void setObDepositAccountBean(ObDepositAccountBean obDepositAccountBean)
	{
		this.obDepositAccountBean = obDepositAccountBean;
	}

	public String getMaturityName() {
		return maturityName;
	}

	public String getZakat() {
		return zakat;
	}

	public void setMaturityName(String maturityName) {
		this.maturityName = maturityName;
	}

	public void setZakat(String zakat) {
		this.zakat = zakat;
	}
}