package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ObAccountBean;
import com.silverlake.mleb.mcb.bean.ObRequest;

public class ObFundTransferDetailRequestBean extends ObRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ObTransactionBean ObTransResultConf;
	private List<ObTransactionBean> ObTransResultAck;
	private List<String> obDebitCcyList;
	private List<String> obBeneCcyList;
	private ObAccountBean ObFromAccBean;
	private String validateSession;
	private String smsToken;
	private ObTrxLimitBean obTrxLimitBean;
	private String termAndConditionFlag;
	private String transferType;
	private ObFundTransferTransactionDetailsBean transactionDetailsBean;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getValidateSession() {
		return validateSession;
	}
	public void setValidateSession(String validateSession) {
		this.validateSession = validateSession;
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
	public String getSmsToken() {
		return smsToken;
	}
	public void setSmsToken(String smsToken) {
		this.smsToken = smsToken;
	}
	public List<String> getObDebitCcyList() {
		return obDebitCcyList;
	}
	public void setObDebitCcyList(List<String> obDebitCcyList) {
		this.obDebitCcyList = obDebitCcyList;
	}
	public List<String> getObBeneCcyList() {
		return obBeneCcyList;
	}
	public void setObBeneCcyList(List<String> obBeneCcyList) {
		this.obBeneCcyList = obBeneCcyList;
	}
	public ObTrxLimitBean getObTrxLimitBean() {
		return obTrxLimitBean;
	}
	public void setObTrxLimitBean(ObTrxLimitBean obTrxLimitBean) {
		this.obTrxLimitBean = obTrxLimitBean;
	}
	public String getTermAndConditionFlag() {
		return termAndConditionFlag;
	}
	public void setTermAndConditionFlag(String termAndConditionFlag) {
		this.termAndConditionFlag = termAndConditionFlag;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public ObFundTransferTransactionDetailsBean getTransactionDetailsBean() {
		return transactionDetailsBean;
	}
	public void setTransactionDetailsBean(ObFundTransferTransactionDetailsBean transactionDetailsBean) {
		this.transactionDetailsBean = transactionDetailsBean;
	}
	
}
