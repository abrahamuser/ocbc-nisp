package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountMaintenanceAckgtResponseBean implements Serializable
{
	private static final long serialVersionUID = 3010578439632508127L;

	private String transactionDate;
	private String accountNo;
	private String accountType;
	private String status;
	private String statusCd;
	private String maturityNameNew;
	private String referenceNo;

	public String getTransactionDate()
	{
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getStatusCd()
	{
		return statusCd;
	}

	public void setStatusCd(String statusCd)
	{
		this.statusCd = statusCd;
	}

	public String getMaturityNameNew()
	{
		return maturityNameNew;
	}

	public void setMaturityNameNew(String maturityNameNew)
	{
		this.maturityNameNew = maturityNameNew;
	}

	public String getReferenceNo()
	{
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo)
	{
		this.referenceNo = referenceNo;
	}

}
