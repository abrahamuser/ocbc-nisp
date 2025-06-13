package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ObCrCrdDetailSummaryResponseBean extends ObResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObCreditCardBean obCreditCardResult;
	private List<ObTransactionBean> obTransactionList;
	private List<ObCreditCardBean> obCreditCardList;
	private ObTransactionBean obTransResult;
	
	public List<ObCreditCardBean> getObCreditCardList() {
		return obCreditCardList;
	}
	public void setObCreditCardList(List<ObCreditCardBean> obCreditCardList) {
		this.obCreditCardList = obCreditCardList;
	}
	public ObCreditCardBean getObCreditCardResult() {
		return obCreditCardResult;
	}
	public void setObCreditCardResult(ObCreditCardBean obCreditCardResult) {
		this.obCreditCardResult = obCreditCardResult;
	}
	public List<ObTransactionBean> getObTransactionList() {
		return obTransactionList;
	}
	public void setObTransactionList(List<ObTransactionBean> obTransactionList) {
		this.obTransactionList = obTransactionList;
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

