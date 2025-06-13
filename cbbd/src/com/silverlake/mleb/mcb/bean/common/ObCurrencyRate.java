package com.silverlake.mleb.mcb.bean.common;

import java.io.Serializable;
import java.math.BigDecimal;


public class ObCurrencyRate implements Serializable {

	private String bankSell;
	private String bankBuy;
	private String ccy;
	private String pairCcy;
	private BigDecimal indicativeRate;
	
	public String getBankSell() {
		return bankSell;
	}
	public void setBankSell(String bankSell) {
		this.bankSell = bankSell;
	}
	public String getBankBuy() {
		return bankBuy;
	}
	public void setBankBuy(String bankBuy) {
		this.bankBuy = bankBuy;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getPairCcy() {
		return pairCcy;
	}
	public void setPairCcy(String pairCcy) {
		this.pairCcy = pairCcy;
	}
	public BigDecimal getIndicativeRate() {
		return indicativeRate;
	}
	public void setIndicativeRate(BigDecimal indicativeRate) {
		this.indicativeRate = indicativeRate;
	}
}
