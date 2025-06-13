package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositAckgtRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 5559252412549218295L;

	private String smsToken;
	private String productCode;
	private String productName;
	private String currency;
	private String tenor;
	private String amount;
	private String debitAcctNo;
	private String debitAcctCcy;
	private String debitAcctProduct;
	private String debitAcctBalance;
	private String debitAcctLabel;
	private String maturityCode;
	private String maturityName;
	private String maturityWS;
	private String maturityLabel;
	private String interestDate;
	private String interestPercentage;
	private String custIDNumber;
	private String custPhone;
	private String custEmail;
	private String purposeAcctOpeningCode;
	private String purposeAcctOpeningName;
	private String purposeAcctOpeningWS;
	private String purposeAcctOpeningLabel;
	private String sourceFundCode;
	private String sourceFundName;
	private String sourceFundWS;
	private String sourceFundLabel;
	private String transactionDate;
	private String depositAccountProduct;
	private ObDebitAccountBean obDebitAccountBean;
	private String promoCode;

	public String getSmsToken()
	{
		return smsToken;
	}

	public void setSmsToken(String smsToken)
	{
		this.smsToken = smsToken;
	}

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

	public String getDebitAcctNo()
	{
		return debitAcctNo;
	}

	public void setDebitAcctNo(String debitAcctNo)
	{
		this.debitAcctNo = debitAcctNo;
	}

	public String getDebitAcctCcy()
	{
		return debitAcctCcy;
	}

	public void setDebitAcctCcy(String debitAcctCcy)
	{
		this.debitAcctCcy = debitAcctCcy;
	}

	public String getDebitAcctProduct()
	{
		return debitAcctProduct;
	}

	public void setDebitAcctProduct(String debitAcctProduct)
	{
		this.debitAcctProduct = debitAcctProduct;
	}

	public String getDebitAcctBalance()
	{
		return debitAcctBalance;
	}

	public void setDebitAcctBalance(String debitAcctBalance)
	{
		this.debitAcctBalance = debitAcctBalance;
	}

	public String getDebitAcctLabel()
	{
		return debitAcctLabel;
	}

	public void setDebitAcctLabel(String debitAcctLabel)
	{
		this.debitAcctLabel = debitAcctLabel;
	}

	public String getMaturityCode()
	{
		return maturityCode;
	}

	public void setMaturityCode(String maturityCode)
	{
		this.maturityCode = maturityCode;
	}

	public String getMaturityName()
	{
		return maturityName;
	}

	public void setMaturityName(String maturityName)
	{
		this.maturityName = maturityName;
	}

	public String getMaturityWS()
	{
		return maturityWS;
	}

	public void setMaturityWS(String maturityWS)
	{
		this.maturityWS = maturityWS;
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

	public String getInterestPercentage()
	{
		return interestPercentage;
	}

	public void setInterestPercentage(String interestPercentage)
	{
		this.interestPercentage = interestPercentage;
	}

	public String getCustIDNumber()
	{
		return custIDNumber;
	}

	public void setCustIDNumber(String custIDNumber)
	{
		this.custIDNumber = custIDNumber;
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

	public String getPurposeAcctOpeningCode()
	{
		return purposeAcctOpeningCode;
	}

	public void setPurposeAcctOpeningCode(String purposeAcctOpeningCode)
	{
		this.purposeAcctOpeningCode = purposeAcctOpeningCode;
	}

	public String getPurposeAcctOpeningName()
	{
		return purposeAcctOpeningName;
	}

	public void setPurposeAcctOpeningName(String purposeAcctOpeningName)
	{
		this.purposeAcctOpeningName = purposeAcctOpeningName;
	}

	public String getPurposeAcctOpeningWS()
	{
		return purposeAcctOpeningWS;
	}

	public void setPurposeAcctOpeningWS(String purposeAcctOpeningWS)
	{
		this.purposeAcctOpeningWS = purposeAcctOpeningWS;
	}

	public String getPurposeAcctOpeningLabel()
	{
		return purposeAcctOpeningLabel;
	}

	public void setPurposeAcctOpeningLabel(String purposeAcctOpeningLabel)
	{
		this.purposeAcctOpeningLabel = purposeAcctOpeningLabel;
	}

	public String getSourceFundCode()
	{
		return sourceFundCode;
	}

	public void setSourceFundCode(String sourceFundCode)
	{
		this.sourceFundCode = sourceFundCode;
	}

	public String getSourceFundName()
	{
		return sourceFundName;
	}

	public void setSourceFundName(String sourceFundName)
	{
		this.sourceFundName = sourceFundName;
	}

	public String getSourceFundWS()
	{
		return sourceFundWS;
	}

	public void setSourceFundWS(String sourceFundWS)
	{
		this.sourceFundWS = sourceFundWS;
	}

	public String getSourceFundLabel()
	{
		return sourceFundLabel;
	}

	public void setSourceFundLabel(String sourceFundLabel)
	{
		this.sourceFundLabel = sourceFundLabel;
	}

	public String getTransactionDate()
	{
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public String getDepositAccountProduct()
	{
		return depositAccountProduct;
	}

	public void setDepositAccountProduct(String depositAccountProduct)
	{
		this.depositAccountProduct = depositAccountProduct;
	}

	public ObDebitAccountBean getObDebitAccountBean()
	{
		return obDebitAccountBean;
	}

	public void setObDebitAccountBean(ObDebitAccountBean obDebitAccountBean)
	{
		this.obDebitAccountBean = obDebitAccountBean;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

}