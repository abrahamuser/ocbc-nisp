package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class ObASSavingBean extends ObAccountBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//----- new AS savings category --------------	
	private BigDecimal transactionAmount;
	private String transactionDescription; 
	private String transactonRef;
	private int transactionPeriod; 
	private String tranFromDate;
	private String tranToDate;
	private String tranActualDate; 
	private int transactionID;
	private String chequeNumber; 
	private String otherInfo; 
	private String senderName; 
	private String description;
	
	//---------------------------------------------
	
	
	
	private String accountType; 
	private int indexAccountSelected; 
	private String localCheque; 
	private String atmCash;	
//	private String transactionDate; 	 
//	private String currencyCode; 	 
	private String references;
	private String references3;
	private String referencesCombine; 
	private Double availableBal; 
	private Double currentBal; 	
	private int loadMore;
	
	//money box (CASA)
	private String currentFeature;
	private String nextFeature; 
	private String nextFeatureEffDate;
	private String myPalAvailability;
	
	//------new Dream Jar (CASA)---------------------
	private String goalCategoryCode;
	private String goalCategory; 
	private String goalName; 
	private BigDecimal goalAmount; 
	private BigDecimal initialAmount; 
	private int tenure; 
	private String maturityDate; 
	private BigDecimal siAmount;
	private String siDate; 
	private String fundingSource;
	//------------------------------------------------
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getIndexAccountSelected() {
		return indexAccountSelected;
	}
	public void setIndexAccountSelected(int indexAccountSelected) {
		this.indexAccountSelected = indexAccountSelected;
	}
	public int getTransactionPeriod() {
		return transactionPeriod;
	}
	public void setTransactionPeriod(int transactionPeriod) {
		this.transactionPeriod = transactionPeriod;
	}
	public String getLocalCheque() {
		return localCheque;
	}
	public void setLocalCheque(String localCheque) {
		this.localCheque = localCheque;
	}
	public String getAtmCash() {
		return atmCash;
	}
	public void setAtmCash(String atmCash) {
		this.atmCash = atmCash;
	}
	/*public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}*/
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public String getTransactonRef() {
		return transactonRef;
	}
	public void setTransactonRef(String transactonRef) {
		this.transactonRef = transactonRef;
	}
	/*public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}*/
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public Double getAvailableBal() {
		return availableBal;
	}
	public void setAvailableBal(Double availableBal) {
		this.availableBal = availableBal;
	}
	public Double getCurrentBal() {
		return currentBal;
	}
	public void setCurrentBal(Double currentBal) {
		this.currentBal = currentBal;
	}
	public int getLoadMore() {
		return loadMore;
	}
	public void setLoadMore(int loadMore) {
		this.loadMore = loadMore;
	}
	public String getCurrentFeature() {
		return currentFeature;
	}
	public void setCurrentFeature(String currentFeature) {
		this.currentFeature = currentFeature;
	}
	public String getNextFeature() {
		return nextFeature;
	}
	public void setNextFeature(String nextFeature) {
		this.nextFeature = nextFeature;
	}
	public String getNextFeatureEffDate() {
		return nextFeatureEffDate;
	}
	public void setNextFeatureEffDate(String nextFeatureEffDate) {
		this.nextFeatureEffDate = nextFeatureEffDate;
	}
	public String isMyPalAvailability() {
		return myPalAvailability;
	}
	public void setMyPalAvailability(String myPalAvailability) {
		this.myPalAvailability = myPalAvailability;
	}
	public String getGoalCategoryCode() {
		return goalCategoryCode;
	}
	public void setGoalCategoryCode(String goalCategoryCode) {
		this.goalCategoryCode = goalCategoryCode;
	}
	public String getGoalCategory() {
		return goalCategory;
	}
	public void setGoalCategory(String goalCategory) {
		this.goalCategory = goalCategory;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	public BigDecimal getGoalAmount() {
		return goalAmount;
	}
	public void setGoalAmount(BigDecimal goalAmount) {
		this.goalAmount = goalAmount;
	}
	public BigDecimal getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public BigDecimal getSiAmount() {
		return siAmount;
	}
	public void setSiAmount(BigDecimal siAmount) {
		this.siAmount = siAmount;
	}
	public String getSiDate() {
		return siDate;
	}
	public void setSiDate(String siDate) {
		this.siDate = siDate;
	}
	public String getFundingSource() {
		return fundingSource;
	}
	public void setFundingSource(String fundingSource) {
		this.fundingSource = fundingSource;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTranActualDate() {
		return tranActualDate;
	}
	public void setTranActualDate(String tranActualDate) {
		this.tranActualDate = tranActualDate;
	}
	public String getTranFromDate() {
		return tranFromDate;
	}
	public void setTranFromDate(String tranFromDate) {
		this.tranFromDate = tranFromDate;
	}
	public String getTranToDate() {
		return tranToDate;
	}
	public void setTranToDate(String tranToDate) {
		this.tranToDate = tranToDate;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMyPalAvailability() {
		return myPalAvailability;
	}
	public String getReferences3() {
		return references3;
	}
	public void setReferences3(String references3) {
		this.references3 = references3;
	}
	public String getReferencesCombine() {
		return referencesCombine;
	}
	public void setReferencesCombine(String referencesCombine) {
		this.referencesCombine = referencesCombine;
	}
	
	
}
