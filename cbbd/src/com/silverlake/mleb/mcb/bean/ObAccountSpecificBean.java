package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObAccountSpecificBean extends ObAccountBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//account summary page specific
	private BigDecimal outStandingBalance;
	private String dueDate; 
	private String cardExpDt;  
	private BigDecimal equivalentPlacement;
	private String machProductInd;
	private Boolean isDebitCard; 
	private String zEndListInd;
	private String principalSuppIndicator;
	private BigDecimal minimumPaymentAmount;
	private String savingsAccNo; 
	
	
	public BigDecimal getOutStandingBalance() {
		return outStandingBalance;
	}
	public void setOutStandingBalance(BigDecimal outStandingBalance) {
		this.outStandingBalance = outStandingBalance;
	}
	public BigDecimal getEquivalentPlacement() {
		return equivalentPlacement;
	}
	public void setEquivalentPlacement(BigDecimal equivalentPlacement) {
		this.equivalentPlacement = equivalentPlacement;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getCardExpDt() {
		return cardExpDt;
	}
	public void setCardExpDt(String cardExpDt) {
		this.cardExpDt = cardExpDt;
	}
	public String getzEndListInd() {
		return zEndListInd;
	}
	public void setzEndListInd(String zEndListInd) {
		this.zEndListInd = zEndListInd;
	}
	public String getMachProductInd() {
		return machProductInd;
	}
	public void setMachProductInd(String machProductInd) {
		this.machProductInd = machProductInd;
	}
	public Boolean getIsDebitCard() {
		return isDebitCard;
	}
	public void setIsDebitCard(Boolean isDebitCard) {
		this.isDebitCard = isDebitCard;
	}
	public String getPrincipalSuppIndicator() {
		return principalSuppIndicator;
	}
	public void setPrincipalSuppIndicator(String principalSuppIndicator) {
		this.principalSuppIndicator = principalSuppIndicator;
	}
	public BigDecimal getMinimumPaymentAmount() {
		return minimumPaymentAmount;
	}
	public void setMinimumPaymentAmount(BigDecimal minimumPaymentAmount) {
		this.minimumPaymentAmount = minimumPaymentAmount;
	}
	public String getSavingsAccNo() {
		return savingsAccNo;
	}
	public void setSavingsAccNo(String savingsAccNo) {
		this.savingsAccNo = savingsAccNo;
	}


	
	
}
