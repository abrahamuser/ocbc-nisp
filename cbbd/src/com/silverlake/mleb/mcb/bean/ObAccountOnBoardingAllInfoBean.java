package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountOnBoardingAllInfoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6561501165275786833L;
	private String productName;
	private String productTitle;
	private String productTitleP2;
	private String productTitleP3;
	private String productDesc;
	private String productDescP2;
	private String productDescP3;
	private String productType;
	private String accountType;
	private String subscriptionType;
	private String productImage;
	private String category;
	private String docType;
	private String docName;
	private String cardType;
	private String cardDesc;
	private String cardBenefits;
	private String cardImage;
	private String isOptional;
	private String nationalityCode;
	private String nationality;
	private String isEligible;
	private String isBonus;
	private String group;
	private List<ObAccountOnBoardingAllInfoBean> lsNationalityInfo;
	private List<ObAccountOnBoardingProductTitleDescBean> lsProductTitleDesc;
	private String productKey;
	
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardDesc() {
		return cardDesc;
	}
	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}
	public String getCardBenefits() {
		return cardBenefits;
	}
	public void setCardBenefits(String cardBenefits) {
		this.cardBenefits = cardBenefits;
	}
	public String getCardImage() {
		return cardImage;
	}
	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}
	public String getNationalityCode() {
		return nationalityCode;
	}
	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getIsEligible() {
		return isEligible;
	}
	public void setIsEligible(String isEligible) {
		this.isEligible = isEligible;
	}
	public String getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(String isBonus) {
		this.isBonus = isBonus;
	}
	public List<ObAccountOnBoardingAllInfoBean> getLsNationalityInfo() {
		return lsNationalityInfo;
	}
	public void setLsNationalityInfo(
			List<ObAccountOnBoardingAllInfoBean> lsNationalityInfo) {
		this.lsNationalityInfo = lsNationalityInfo;
	}
	public String getProductDescP2() {
		return productDescP2;
	}
	public void setProductDescP2(String productDescP2) {
		this.productDescP2 = productDescP2;
	}
	public String getProductDescP3() {
		return productDescP3;
	}
	public void setProductDescP3(String productDescP3) {
		this.productDescP3 = productDescP3;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductTitleP2() {
		return productTitleP2;
	}
	public void setProductTitleP2(String productTitleP2) {
		this.productTitleP2 = productTitleP2;
	}
	public String getProductTitleP3() {
		return productTitleP3;
	}
	public void setProductTitleP3(String productTitleP3) {
		this.productTitleP3 = productTitleP3;
	}
	public List<ObAccountOnBoardingProductTitleDescBean> getLsProductTitleDesc() {
		return lsProductTitleDesc;
	}
	public void setLsProductTitleDesc(
			List<ObAccountOnBoardingProductTitleDescBean> lsProductTitleDesc) {
		this.lsProductTitleDesc = lsProductTitleDesc;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	
}
