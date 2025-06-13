package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerFacilityData;

public class ObCustomerInvestmentDataBean implements Serializable{
	 
	private String customerNumber;
	private String clientCode;
	private String productName;
	private String productCode;
	private BigDecimal unitBalance;
	private String currencyCode;
	private BigDecimal navAmount;
	private BigDecimal amountBalance;
	private String navDate;
	private String investType;
	private String maturityDate;
	private BigDecimal cuponPct;	
	
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public BigDecimal getUnitBalance() {
		return unitBalance;
	}
	public void setUnitBalance(BigDecimal unitBalance) {
		this.unitBalance = unitBalance;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getNavAmount() {
		return navAmount;
	}
	public void setNavAmount(BigDecimal navAmount) {
		this.navAmount = navAmount;
	}
	public BigDecimal getAmountBalance() {
		return amountBalance;
	}
	public void setAmountBalance(BigDecimal amountBalance) {
		this.amountBalance = amountBalance;
	}
	public String getNavDate() {
		return navDate;
	}
	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public BigDecimal getCuponPct() {
		return cuponPct;
	}
	public void setCuponPct(BigDecimal cuponPct) {
		this.cuponPct = cuponPct;
	}
				
		    	    
}

