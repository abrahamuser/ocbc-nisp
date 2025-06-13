package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ObExchangeRateBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String currencyCode;
	private String period;
	private BigDecimal ttBuyRate;
	private BigDecimal ttSellRate;
	private BigDecimal bankBuyRate;
	private BigDecimal bankSellRate;
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getTtBuyRate() {
		return ttBuyRate;
	}
	public void setTtBuyRate(BigDecimal ttBuyRate) {
		this.ttBuyRate = ttBuyRate;
	}
	public BigDecimal getTtSellRate() {
		return ttSellRate;
	}
	public void setTtSellRate(BigDecimal ttSellRate) {
		this.ttSellRate = ttSellRate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getBankBuyRate() {
		return bankBuyRate;
	}
	public void setBankBuyRate(BigDecimal bankBuyRate) {
		this.bankBuyRate = bankBuyRate;
	}
	public BigDecimal getBankSellRate() {
		return bankSellRate;
	}
	public void setBankSellRate(BigDecimal bankSellRate) {
		this.bankSellRate = bankSellRate;
	}
}
