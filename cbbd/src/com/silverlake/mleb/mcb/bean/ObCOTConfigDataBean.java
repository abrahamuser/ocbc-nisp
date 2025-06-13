package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObCOTConfigDataBean implements Serializable{
	private Boolean isCot;
	private String busHourStart;
	private String busHourEnd;
	private String currencyCd;
	private String productCd;
	
	
	public Boolean getIsCot() {
		return isCot;
	}
	public void setIsCot(Boolean isCot) {
		this.isCot = isCot;
	}
	public String getBusHourStart() {
		return busHourStart;
	}
	public void setBusHourStart(String busHourStart) {
		this.busHourStart = busHourStart;
	}
	public String getBusHourEnd() {
		return busHourEnd;
	}
	public void setBusHourEnd(String busHourEnd) {
		this.busHourEnd = busHourEnd;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
			
}

