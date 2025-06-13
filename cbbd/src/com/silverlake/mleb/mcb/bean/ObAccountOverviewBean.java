package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObAccountOverviewBean implements Serializable
{
	private static final long serialVersionUID = 5817599928512356551L;

	private String productCode;

	private String productName;
	
	private String accountType;

	private BigDecimal availableBalance;

	private String accountCcy;

	private String index;

	private String accountNo;

	private String lang;

	private String ccNumber;
	
	private String groupNumber;
	
	private String groupType;
	
	private Boolean isSyariahProduct;
	
	private String maturityDate;
	
	private String favSeq;
	
	
	//mcb order
	private String favOrder;

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

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public BigDecimal getAvailableBalance()
	{
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance)
	{
		this.availableBalance = availableBalance;
	}

	public String getAccountCcy()
	{
		return accountCcy;
	}

	public void setAccountCcy(String accountCcy)
	{
		this.accountCcy = accountCcy;
	}

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getLang()
	{
		return lang;
	}

	public void setLang(String lang)
	{
		this.lang = lang;
	}

	public String getCcNumber()
	{
		return ccNumber;
	}

	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Boolean getIsSyariahProduct() {
		return isSyariahProduct;
	}

	public void setIsSyariahProduct(Boolean isSyariahProduct) {
		this.isSyariahProduct = isSyariahProduct;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getFavSeq() {
		return favSeq;
	}

	public void setFavSeq(String favSeq) {
		this.favSeq = favSeq;
	}

	public String getFavOrder() {
		return favOrder;
	}

	public void setFavOrder(String favOrder) {
		this.favOrder = favOrder;
	}
	
	
	
}
