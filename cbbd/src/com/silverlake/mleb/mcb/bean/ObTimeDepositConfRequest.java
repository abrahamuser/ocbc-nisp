package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositConfRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 6676013332188649351L;

	private String productCode;
	private String productName;
	private String currency;
	private String tenor;
	private String amount;
	private String debitAcctLabel;
	private String maturityLabel;
	private String interestDate;
	private String custPhone;
	private String custEmail;
	private String purposeAcctOpeningLabel;
	private String sourceFundLabel;

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getTenor()
	{
		return tenor;
	}

	public void setTenor(String tenor)
	{
		this.tenor = tenor;
	}

	public String getAmount()
	{
		return amount;
	}

	public void setAmount(String amount)
	{
		this.amount = amount;
	}

	public String getDebitAcctLabel()
	{
		return debitAcctLabel;
	}

	public void setDebitAcctLabel(String debitAcctLabel)
	{
		this.debitAcctLabel = debitAcctLabel;
	}

	public String getMaturityLabel()
	{
		return maturityLabel;
	}

	public void setMaturityLabel(String maturityLabel)
	{
		this.maturityLabel = maturityLabel;
	}

	public String getInterestDate()
	{
		return interestDate;
	}

	public void setInterestDate(String interestDate)
	{
		this.interestDate = interestDate;
	}

	public String getCustPhone()
	{
		return custPhone;
	}

	public void setCustPhone(String custPhone)
	{
		this.custPhone = custPhone;
	}

	public String getCustEmail()
	{
		return custEmail;
	}

	public void setCustEmail(String custEmail)
	{
		this.custEmail = custEmail;
	}

	public String getPurposeAcctOpeningLabel()
	{
		return purposeAcctOpeningLabel;
	}

	public void setPurposeAcctOpeningLabel(String purposeAcctOpeningLabel)
	{
		this.purposeAcctOpeningLabel = purposeAcctOpeningLabel;
	}

	public String getSourceFundLabel()
	{
		return sourceFundLabel;
	}

	public void setSourceFundLabel(String sourceFundLabel)
	{
		this.sourceFundLabel = sourceFundLabel;
	}

}