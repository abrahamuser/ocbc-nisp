package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObFundTransferRequestBean extends ObRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObAccountBean obAccountBean;
	private List<ObBankDetailBean> obBankDetailBeanList;
	private List<ObListCutOffBean> lsCutOffTime;
	private ObTrxLimitBean obTrxLimitList;
	private List<MapPojo> transferServiceList;
	private String bankName;
	private String transferDate;
	private String amount;
	private String transferServiceType;
	private String currencyCode;

	public ObAccountBean getObAccountBean() {
		return obAccountBean;
	}

	public void setObAccountBean(ObAccountBean obAccountBean) {
		this.obAccountBean = obAccountBean;
	}

	public List<ObBankDetailBean> getObBankDetailBeanList() {
		return obBankDetailBeanList;
	}

	public void setObBankDetailBeanList(List<ObBankDetailBean> obBankDetailBeanList) {
		this.obBankDetailBeanList = obBankDetailBeanList;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<ObListCutOffBean> getLsCutOffTime() {
		return lsCutOffTime;
	}

	public void setLsCutOffTime(List<ObListCutOffBean> lsCutOffTime) {
		this.lsCutOffTime = lsCutOffTime;
	}

	public ObTrxLimitBean getObTrxLimitList() {
		return obTrxLimitList;
	}

	public void setObTrxLimitList(ObTrxLimitBean obTrxLimitList) {
		this.obTrxLimitList = obTrxLimitList;
	}

	public List<MapPojo> getTransferServiceList() {
		return transferServiceList;
	}

	public void setTransferServiceList(List<MapPojo> transferServiceList) {
		this.transferServiceList = transferServiceList;
	}

	public String getTransferServiceType() {
		return transferServiceType;
	}

	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	
	
	
}
