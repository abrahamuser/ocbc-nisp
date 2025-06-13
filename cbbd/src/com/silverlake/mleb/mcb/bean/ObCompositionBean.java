package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObCompositionBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String totalBalance;
	private String exchangeRateDate;
	private String exchangeRateTime;
	
	private MapPojoBean pieChart;
	private MapPojoBean accountComposition;
	
	public String getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}
	public String getExchangeRateDate() {
		return exchangeRateDate;
	}
	public void setExchangeRateDate(String exchangeRateDate) {
		this.exchangeRateDate = exchangeRateDate;
	}
	public String getExchangeRateTime() {
		return exchangeRateTime;
	}
	public void setExchangeRateTime(String exchangeRateTime) {
		this.exchangeRateTime = exchangeRateTime;
	}
	public MapPojoBean getPieChart() {
		return pieChart;
	}
	public void setPieChart(MapPojoBean pieChart) {
		this.pieChart = pieChart;
	}
	public MapPojoBean getAccountComposition() {
		return accountComposition;
	}
	public void setAccountComposition(MapPojoBean accountComposition) {
		this.accountComposition = accountComposition;
	}
	
}
