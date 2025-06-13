package com.silverlake.mleb.mcb.bean.timedeposit;

import java.io.Serializable;
import java.math.BigDecimal;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ParameterResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductDetailsResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ProductListResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositSubmitRequestData;

public class ObTimeDepositSessionCache extends ObSessionCache implements Serializable {
	private TimeDepositSubmitRequestData inquiryRateRequestData;
	private TimeDepositResponseData inquiryRateResponseData;
	private ParameterResponseData parameterResponseData;
	private ProductListResponseData productListResponseData;
	private ProductDetailsResponseData productDetailsResponseData;
	
	private BigDecimal interestRate;
	private String sourceOfFundDesc;
	private String purposeDesc;
	private String rolloverTypeDesc;
	private String debitAccountAliasName;
	private String debitAccountType;
	public TimeDepositSubmitRequestData getInquiryRateRequestData() {
		return inquiryRateRequestData;
	}
	public TimeDepositResponseData getInquiryRateResponseData() {
		return inquiryRateResponseData;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public String getSourceOfFundDesc() {
		return sourceOfFundDesc;
	}
	public String getPurposeDesc() {
		return purposeDesc;
	}
	public String getRolloverTypeDesc() {
		return rolloverTypeDesc;
	}
	public void setInquiryRateRequestData(TimeDepositSubmitRequestData inquiryRateRequestData) {
		this.inquiryRateRequestData = inquiryRateRequestData;
	}
	public void setInquiryRateResponseData(TimeDepositResponseData inquiryRateResponseData) {
		this.inquiryRateResponseData = inquiryRateResponseData;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public void setSourceOfFundDesc(String sourceOfFundDesc) {
		this.sourceOfFundDesc = sourceOfFundDesc;
	}
	public void setPurposeDesc(String purposeDesc) {
		this.purposeDesc = purposeDesc;
	}
	public void setRolloverTypeDesc(String rolloverTypeDesc) {
		this.rolloverTypeDesc = rolloverTypeDesc;
	}
	public ParameterResponseData getParameterResponseData() {
		return parameterResponseData;
	}
	public void setParameterResponseData(ParameterResponseData parameterResponseData) {
		this.parameterResponseData = parameterResponseData;
	}
	public ProductListResponseData getProductListResponseData() {
		return productListResponseData;
	}
	public void setProductListResponseData(ProductListResponseData productListResponseData) {
		this.productListResponseData = productListResponseData;
	}
	public ProductDetailsResponseData getProductDetailsResponseData() {
		return productDetailsResponseData;
	}
	public void setProductDetailsResponseData(ProductDetailsResponseData productDetailsResponseData) {
		this.productDetailsResponseData = productDetailsResponseData;
	}
	public String getDebitAccountAliasName() {
		return debitAccountAliasName;
	}
	public void setDebitAccountAliasName(String debitAccountAliasName) {
		this.debitAccountAliasName = debitAccountAliasName;
	}
	public String getDebitAccountType() {
		return debitAccountType;
	}
	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}
}
