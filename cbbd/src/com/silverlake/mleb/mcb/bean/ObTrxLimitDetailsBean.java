package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObTrxLimitDetailsBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal singleTransAmount;
	private BigDecimal dailyAcctTransAmount;
	private BigDecimal dailyCIFTransAmount;
	private BigDecimal usedTxnAmount;
	private BigDecimal usedTxnCIFAmount;
	private BigDecimal availableTransAccount;
	private BigDecimal availableTransCIF;
	private BigDecimal minAmount;
	
	
	private String exchangeRateCcy;
	private BigDecimal exchangeRate;
	private BigDecimal minAmountExchanged;
	private BigDecimal singleTransactionAmountExchanged;
	private BigDecimal dailyAcctAmountExchanged;
	
	private BigDecimal exchangeBuyRate;
	private BigDecimal exchangeSellRate;
	private BigDecimal minAmountBuyRate;
	private BigDecimal minAmountSellRate;
	private BigDecimal singleTransactionAmountBuyRate;
	private BigDecimal singleTransactionAmountSellRate;
	
	public BigDecimal getSingleTransAmount() {
		return singleTransAmount;
	}
	public void setSingleTransAmount(BigDecimal singleTransAmount) {
		this.singleTransAmount = singleTransAmount;
	}
	public BigDecimal getDailyAcctTransAmount() {
		return dailyAcctTransAmount;
	}
	public void setDailyAcctTransAmount(BigDecimal dailyAcctTransAmount) {
		this.dailyAcctTransAmount = dailyAcctTransAmount;
	}
	public BigDecimal getDailyCIFTransAmount() {
		return dailyCIFTransAmount;
	}
	public void setDailyCIFTransAmount(BigDecimal dailyCIFTransAmount) {
		this.dailyCIFTransAmount = dailyCIFTransAmount;
	}
	public BigDecimal getUsedTxnAmount() {
		return usedTxnAmount;
	}
	public void setUsedTxnAmount(BigDecimal usedTxnAmount) {
		this.usedTxnAmount = usedTxnAmount;
	}
	public BigDecimal getUsedTxnCIFAmount() {
		return usedTxnCIFAmount;
	}
	public void setUsedTxnCIFAmount(BigDecimal usedTxnCIFAmount) {
		this.usedTxnCIFAmount = usedTxnCIFAmount;
	}
	public BigDecimal getAvailableTransAccount() {
		return availableTransAccount;
	}
	public void setAvailableTransAccount(BigDecimal availableTransAccount) {
		this.availableTransAccount = availableTransAccount;
	}
	public BigDecimal getAvailableTransCIF() {
		return availableTransCIF;
	}
	public void setAvailableTransCIF(BigDecimal availableTransCIF) {
		this.availableTransCIF = availableTransCIF;
	}
	public BigDecimal getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}
	public String getExchangeRateCcy() {
		return exchangeRateCcy;
	}
	public void setExchangeRateCcy(String exchangeRateCcy) {
		this.exchangeRateCcy = exchangeRateCcy;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public BigDecimal getMinAmountExchanged() {
		return minAmountExchanged;
	}
	public void setMinAmountExchanged(BigDecimal minAmountExchanged) {
		this.minAmountExchanged = minAmountExchanged;
	}
	public BigDecimal getSingleTransactionAmountExchanged() {
		return singleTransactionAmountExchanged;
	}
	public void setSingleTransactionAmountExchanged(BigDecimal singleTransactionAmountExchanged) {
		this.singleTransactionAmountExchanged = singleTransactionAmountExchanged;
	}
	public BigDecimal getDailyAcctAmountExchanged() {
		return dailyAcctAmountExchanged;
	}
	public void setDailyAcctAmountExchanged(BigDecimal dailyAcctAmountExchanged) {
		this.dailyAcctAmountExchanged = dailyAcctAmountExchanged;
	}
	public BigDecimal getMinAmountBuyRate() {
		return minAmountBuyRate;
	}
	public void setMinAmountBuyRate(BigDecimal minAmountBuyRate) {
		this.minAmountBuyRate = minAmountBuyRate;
	}
	public BigDecimal getMinAmountSellRate() {
		return minAmountSellRate;
	}
	public void setMinAmountSellRate(BigDecimal minAmountSellRate) {
		this.minAmountSellRate = minAmountSellRate;
	}
	public BigDecimal getSingleTransactionAmountBuyRate() {
		return singleTransactionAmountBuyRate;
	}
	public void setSingleTransactionAmountBuyRate(
			BigDecimal singleTransactionAmountBuyRate) {
		this.singleTransactionAmountBuyRate = singleTransactionAmountBuyRate;
	}
	public BigDecimal getSingleTransactionAmountSellRate() {
		return singleTransactionAmountSellRate;
	}
	public void setSingleTransactionAmountSellRate(
			BigDecimal singleTransactionAmountSellRate) {
		this.singleTransactionAmountSellRate = singleTransactionAmountSellRate;
	}
	public BigDecimal getExchangeBuyRate() {
		return exchangeBuyRate;
	}
	public void setExchangeBuyRate(BigDecimal exchangeBuyRate) {
		this.exchangeBuyRate = exchangeBuyRate;
	}
	public BigDecimal getExchangeSellRate() {
		return exchangeSellRate;
	}
	public void setExchangeSellRate(BigDecimal exchangeSellRate) {
		this.exchangeSellRate = exchangeSellRate;
	}
}
