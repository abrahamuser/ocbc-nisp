package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ObAccountBean;
import com.silverlake.mleb.mcb.bean.ObResponse;

public class ObFundTransferDetailResponseBean extends ObResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ObTransactionBean ObTransResultConf;
	private List<ObTransactionBean> ObTransResultAck;
	private ObAccountBean ObFromAccBean;
	private String errorMessage;
	private String transServiceCode;
	private String registerFlag;
	private String validateSession;
	private String transactionID;
	private String foreignTNC;
	private List<ObBankOfInterbank> lsBankOfInterbank;
	private List<ObCurrencyListBean> lsCcyCodeBean;
	private List<ObLimitExchangedRateBean> lsLimitExchangeRateBean;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getTransServiceCode() {
		return transServiceCode;
	}
	public void setTransServiceCode(String transServiceCode) {
		this.transServiceCode = transServiceCode;
	}
	public String getRegisterFlag() {
		return registerFlag;
	}
	public void setRegisterFlag(String registerFlag) {
		this.registerFlag = registerFlag;
	}
	public String getValidateSession() {
		return validateSession;
	}
	public void setValidateSession(String validateSession) {
		this.validateSession = validateSession;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public ObTransactionBean getObTransResultConf() {
		return ObTransResultConf;
	}
	public void setObTransResultConf(ObTransactionBean obTransResultConf) {
		ObTransResultConf = obTransResultConf;
	}
	public List<ObTransactionBean> getObTransResultAck() {
		return ObTransResultAck;
	}
	public void setObTransResultAck(List<ObTransactionBean> obTransResultAck) {
		ObTransResultAck = obTransResultAck;
	}
	public ObAccountBean getObFromAccBean() {
		return ObFromAccBean;
	}
	public void setObFromAccBean(ObAccountBean obFromAccBean) {
		ObFromAccBean = obFromAccBean;
	}
	public List<ObBankOfInterbank> getLsBankOfInterbank() {
		return lsBankOfInterbank;
	}
	public void setLsBankOfInterbank(List<ObBankOfInterbank> lsBankOfInterbank) {
		this.lsBankOfInterbank = lsBankOfInterbank;
	}
	public List<ObCurrencyListBean> getLsCcyCodeBean() {
		return lsCcyCodeBean;
	}
	public void setLsCcyCodeBean(List<ObCurrencyListBean> lsCcyCodeBean) {
		this.lsCcyCodeBean = lsCcyCodeBean;
	}
	public List<ObLimitExchangedRateBean> getLsLimitExchangeRateBean() {
		return lsLimitExchangeRateBean;
	}
	public void setLsLimitExchangeRateBean(List<ObLimitExchangedRateBean> lsLimitExchangeRateBean) {
		this.lsLimitExchangeRateBean = lsLimitExchangeRateBean;
	}
	public String getForeignTNC() {
		return foreignTNC;
	}
	public void setForeignTNC(String foreignTNC) {
		this.foreignTNC = foreignTNC;
	}


	
	

}
