package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObAccountTransactionHistoryBean implements Serializable
{
	private static final long serialVersionUID = 1113933978083159815L;

	private String transactionDate;
	private String valueDate;
	private String transDescription;
	private String sign;
	private BigDecimal amount;
	private BigDecimal balance;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String tranCode;

	public String getTransactionDate()
	{
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public String getValueDate()
	{
		return valueDate;
	}

	public void setValueDate(String valueDate)
	{
		this.valueDate = valueDate;
	}

	public String getTransDescription()
	{
		return transDescription;
	}

	public void setTransDescription(String transDescription)
	{
		this.transDescription = transDescription;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}

	public String getRemark1()
	{
		return remark1;
	}

	public void setRemark1(String remark1)
	{
		this.remark1 = remark1;
	}

	public String getRemark2()
	{
		return remark2;
	}

	public void setRemark2(String remark2)
	{
		this.remark2 = remark2;
	}

	public String getRemark3()
	{
		return remark3;
	}

	public void setRemark3(String remark3)
	{
		this.remark3 = remark3;
	}

	public String getRemark4()
	{
		return remark4;
	}

	public void setRemark4(String remark4)
	{
		this.remark4 = remark4;
	}

	public String getTranCode()
	{
		return tranCode;
	}

	public void setTranCode(String tranCode)
	{
		this.tranCode = tranCode;
	}

}
