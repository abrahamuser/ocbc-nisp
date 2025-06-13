package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObCrCrdDetailSummaryRequestBean extends ObRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObCreditCardBean obCreditCard; 
	private ObTransactionBean obTransResult;
	
	public ObCreditCardBean getObCreditCard() {
		return obCreditCard;
	}
	public void setObCreditCard(ObCreditCardBean obCreditCard) {
		this.obCreditCard = obCreditCard;
	}
	public ObTransactionBean getObTransResult() {
		return obTransResult;
	}
	public void setObTransResult(ObTransactionBean obTransResult) {
		this.obTransResult = obTransResult;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
