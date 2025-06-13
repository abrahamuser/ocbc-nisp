package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.onfx.ResponseData;


public class ObForexSessionCache extends ObSessionCache implements Serializable  {
	private ResponseData omniResponse;
	
	private ObForexRequest requestBean;
	
	private BigDecimal rate;
	private ResponseData onfxRateResponseData;

	public ObForexRequest getRequestBean() {
		return requestBean;
	}

	public void setRequestBean(ObForexRequest requestBean) {
		this.requestBean = requestBean;
	}

	public ResponseData getOmniResponse() {
		return omniResponse;
	}

	public void setOmniResponse(ResponseData omniResponse) {
		this.omniResponse = omniResponse;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public ResponseData getOnfxRateResponseData() {
		return onfxRateResponseData;
	}

	public void setOnfxRateResponseData(ResponseData onfxRateResponseData) {
		this.onfxRateResponseData = onfxRateResponseData;
	}
}
