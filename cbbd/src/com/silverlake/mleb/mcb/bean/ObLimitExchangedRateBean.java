package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObLimitExchangedRateBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private ObTrxLimitDetailsBean obIFTCLimit;
	private ObTrxLimitDetailsBean obIFTSLimit;
	private ObTrxLimitDetailsBean obLLGSLimit;
	private ObTrxLimitDetailsBean obOATCLimit;
	private ObTrxLimitDetailsBean obOATSLimit;
	private ObTrxLimitDetailsBean obOLTSLimit;
	private ObTrxLimitDetailsBean obRTGSSLimit;
	
	private String exchangeRateCcy;
	private BigDecimal exchangeRate;

	
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
	public ObTrxLimitDetailsBean getObIFTCLimit() {
		return obIFTCLimit;
	}
	public void setObIFTCLimit(ObTrxLimitDetailsBean obIFTCLimit) {
		this.obIFTCLimit = obIFTCLimit;
	}
	public ObTrxLimitDetailsBean getObIFTSLimit() {
		return obIFTSLimit;
	}
	public void setObIFTSLimit(ObTrxLimitDetailsBean obIFTSLimit) {
		this.obIFTSLimit = obIFTSLimit;
	}
	public ObTrxLimitDetailsBean getObLLGSLimit() {
		return obLLGSLimit;
	}
	public void setObLLGSLimit(ObTrxLimitDetailsBean obLLGSLimit) {
		this.obLLGSLimit = obLLGSLimit;
	}
	public ObTrxLimitDetailsBean getObOATCLimit() {
		return obOATCLimit;
	}
	public void setObOATCLimit(ObTrxLimitDetailsBean obOATCLimit) {
		this.obOATCLimit = obOATCLimit;
	}
	public ObTrxLimitDetailsBean getObOATSLimit() {
		return obOATSLimit;
	}
	public void setObOATSLimit(ObTrxLimitDetailsBean obOATSLimit) {
		this.obOATSLimit = obOATSLimit;
	}
	
	public ObTrxLimitDetailsBean getObOLTSLimit() {
		return obOLTSLimit;
	}
	public void setObOLTSLimit(ObTrxLimitDetailsBean obOLTSLimit) {
		this.obOLTSLimit = obOLTSLimit;
	}
	public ObTrxLimitDetailsBean getObRTGSSLimit() {
		return obRTGSSLimit;
	}
	public void setObRTGSSLimit(ObTrxLimitDetailsBean obRTGSSLimit) {
		this.obRTGSSLimit = obRTGSSLimit;
	}
	
	
	
	
	
	
	

}
