package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObFundTransferListResponseBean extends ObResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ObAccountBean> ObAccountList;
	private List<ObFavoriteAccountBean> ObFavoriteAccList;
	private List<ObGeneralCodeBean> obAccTypeCodeList;
	private List<ObBeneAccountBean> obBeneIntraAccList;
	private List<ObBeneAccountBean> obBeneInterAccList;
	private ObRecurringTypeBean obRecurringTypeList;
	private ObCurrencyCodeBean obCcyTypeList;
	private ObTransferPurposeTypeBean obTransPurposeTypeList;
	private List<ObOwnAccountBean> obOwnAccountList;
	private List<ObCustomerTokenDtoBean> obCustomerTokenList;
	private ObBankOfInterbank obBankOfInterbankList;
	private ObTrxLimitBean obTrxLimitList;
	private ObTransferServiceListBean obTransferServiceListBean;
	private String foreignTNC;
	private List<ObLimitExchangedRateBean> lsLimitExchangeRateBean;
	
	public List<ObGeneralCodeBean> getObAccTypeCodeList() {
		return obAccTypeCodeList;
	}
	public void setObAccTypeCodeList(List<ObGeneralCodeBean> obAccTypeCodeList) {
		this.obAccTypeCodeList = obAccTypeCodeList;
	}
	public List<ObAccountBean> getObAccountList() {
		return ObAccountList;
	}
	public void setObAccountList(List<ObAccountBean> obAccountList) {
		ObAccountList = obAccountList;
	}
	public List<ObFavoriteAccountBean> getObFavoriteAccList() {
		return ObFavoriteAccList;
	}
	public void setObFavoriteAccList(List<ObFavoriteAccountBean> obFavoriteAccList) {
		ObFavoriteAccList = obFavoriteAccList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public List<ObBeneAccountBean> getObBeneIntraAccList() {
		return obBeneIntraAccList;
	}
	public void setObBeneIntraAccList(List<ObBeneAccountBean> obBeneIntraAccList) {
		this.obBeneIntraAccList = obBeneIntraAccList;
	}
	public List<ObBeneAccountBean> getObBeneInterAccList() {
		return obBeneInterAccList;
	}
	public void setObBeneInterAccList(List<ObBeneAccountBean> obBeneInterAccList) {
		this.obBeneInterAccList = obBeneInterAccList;
	}
	public ObRecurringTypeBean getObRecurringTypeList() {
		return obRecurringTypeList;
	}
	public void setObRecurringTypeList(ObRecurringTypeBean obRecurringTypeList) {
		this.obRecurringTypeList = obRecurringTypeList;
	}
	public ObCurrencyCodeBean getObCcyTypeList() {
		return obCcyTypeList;
	}
	public void setObCcyTypeList(ObCurrencyCodeBean obCcyTypeList) {
		this.obCcyTypeList = obCcyTypeList;
	}
	public ObTransferPurposeTypeBean getObTransPurposeTypeList() {
		return obTransPurposeTypeList;
	}
	public void setObTransPurposeTypeList(ObTransferPurposeTypeBean obTransPurposeTypeList) {
		this.obTransPurposeTypeList = obTransPurposeTypeList;
	}
	public List<ObOwnAccountBean> getObOwnAccountList() {
		return obOwnAccountList;
	}
	public void setObOwnAccountList(List<ObOwnAccountBean> obOwnAccountList) {
		this.obOwnAccountList = obOwnAccountList;
	}
	public List<ObCustomerTokenDtoBean> getObCustomerTokenList() {
		return obCustomerTokenList;
	}
	public void setObCustomerTokenList(List<ObCustomerTokenDtoBean> obCustomerTokenList) {
		this.obCustomerTokenList = obCustomerTokenList;
	}
	
	public ObTrxLimitBean getObTrxLimitList() {
		return obTrxLimitList;
	}
	public void setObTrxLimitList(ObTrxLimitBean obTrxLimitList) {
		this.obTrxLimitList = obTrxLimitList;
	}
	public ObBankOfInterbank getObBankOfInterbankList() {
		return obBankOfInterbankList;
	}
	public void setObBankOfInterbankList(ObBankOfInterbank obBankOfInterbankList) {
		this.obBankOfInterbankList = obBankOfInterbankList;
	}
	public ObTransferServiceListBean getObTransferServiceListBean() {
		return obTransferServiceListBean;
	}
	public void setObTransferServiceListBean(ObTransferServiceListBean obTransferServiceListBean) {
		this.obTransferServiceListBean = obTransferServiceListBean;
	}
	public String getForeignTNC() {
		return foreignTNC;
	}
	public void setForeignTNC(String foreignTNC) {
		this.foreignTNC = foreignTNC;
	}
	public List<ObLimitExchangedRateBean> getLsLimitExchangeRateBean() {
		return lsLimitExchangeRateBean;
	}
	public void setLsLimitExchangeRateBean(List<ObLimitExchangedRateBean> lsLimitExchangeRateBean) {
		this.lsLimitExchangeRateBean = lsLimitExchangeRateBean;
	}
	 
	
	
	
	
	
	
}
