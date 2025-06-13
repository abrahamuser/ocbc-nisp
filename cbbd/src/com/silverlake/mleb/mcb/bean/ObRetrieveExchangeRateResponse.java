package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.exchangeRate.ExchangeRate;

public class ObRetrieveExchangeRateResponse extends ObResponse implements Serializable
{
	private List<ExchangeRate> list_fxrate;

	private List<ObExchangeRateBean> rateList;
	private BigDecimal rate;

	public List<ExchangeRate> getList_fxrate() {
		return list_fxrate;
	}

	public void setList_fxrate(List<ExchangeRate> list_fxrate) {
		this.list_fxrate = list_fxrate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public List<ObExchangeRateBean> getRateList() {
		return rateList;
	}

	public void setRateList(List<ObExchangeRateBean> rateList) {
		this.rateList = rateList;
	}
	
}