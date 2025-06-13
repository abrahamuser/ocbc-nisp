package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.silverlake.mleb.mcb.bean.common.ObCurrencyRate;

public class ObForexResponse extends ObResponseCache implements Serializable{
	private Boolean allowProcess;
	private String startTime;
	private String endTime;
	private String errorMessageCOT;
	private Boolean isPublicHoliday;
	private List<ObCurrencyRate> listCurrency;
	private List<String> availableCurrencyList;
	private Map<String, String> purposeList;
	
	private BigDecimal rate;
	private String pairCcy;
	private String sellCcy;
	private String buyCcy;
	private BigDecimal sellingAmount;
	private BigDecimal buyingAmount;
	
	private String debitAccountNo;
	private String debitAccountAliasName;
	private String debitAccountType;
	
	private String beneAccountNo;
	private String beneAccountAliasName;
	private String beneAccountType;
	
	private String purpose;
	private String remark;
	private String refId;
	
	private String transactionId;
	
	private String trxStatusCode;
	private String trxStatusDesc;
	private String errorDesc;
	
	public String getSellCcy() {
		return sellCcy;
	}
	public void setSellCcy(String sellCcy) {
		this.sellCcy = sellCcy;
	}
	public String getBuyCcy() {
		return buyCcy;
	}
	public void setBuyCcy(String buyCcy) {
		this.buyCcy = buyCcy;
	}
	public Boolean getAllowProcess() {
		return allowProcess;
	}
	public void setAllowProcess(Boolean allowProcess) {
		this.allowProcess = allowProcess;
	}
	public String getErrorMessageCOT() {
		return errorMessageCOT;
	}
	public void setErrorMessageCOT(String errorMessageCOT) {
		this.errorMessageCOT = errorMessageCOT;
	}
	public Boolean getIsPublicHoliday() {
		return isPublicHoliday;
	}
	public void setIsPublicHoliday(Boolean isPublicHoliday) {
		this.isPublicHoliday = isPublicHoliday;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<ObCurrencyRate> getListCurrency() {
		return listCurrency;
	}
	public void setListCurrency(List<ObCurrencyRate> listCurrency) {
		this.listCurrency = listCurrency;
	}
	public BigDecimal getSellingAmount() {
		return sellingAmount;
	}
	public void setSellingAmount(BigDecimal sellingAmount) {
		this.sellingAmount = sellingAmount;
	}
	public BigDecimal getBuyingAmount() {
		return buyingAmount;
	}
	public void setBuyingAmount(BigDecimal buyingAmount) {
		this.buyingAmount = buyingAmount;
	}
	public String getDebitAccountNo() {
		return debitAccountNo;
	}
	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}
	public String getDebitAccountAliasName() {
		return debitAccountAliasName;
	}
	public void setDebitAccountAliasName(String debitAccountAliasName) {
		this.debitAccountAliasName = debitAccountAliasName;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneAccountAliasName() {
		return beneAccountAliasName;
	}
	public void setBeneAccountAliasName(String beneAccountAliasName) {
		this.beneAccountAliasName = beneAccountAliasName;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getPairCcy() {
		return pairCcy;
	}
	public void setPairCcy(String pairCcy) {
		this.pairCcy = pairCcy;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTrxStatusCode() {
		return trxStatusCode;
	}
	public void setTrxStatusCode(String trxStatusCode) {
		this.trxStatusCode = trxStatusCode;
	}
	public String getTrxStatusDesc() {
		return trxStatusDesc;
	}
	public void setTrxStatusDesc(String trxStatusDesc) {
		this.trxStatusDesc = trxStatusDesc;
	}
	public Map<String, String> getPurposeList() {
		return purposeList;
	}
	public void setPurposeList(Map<String, String> purposeList) {
		this.purposeList = purposeList;
	}
	public String getDebitAccountType() {
		return debitAccountType;
	}
	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}
	public String getBeneAccountType() {
		return beneAccountType;
	}
	public void setBeneAccountType(String beneAccountType) {
		this.beneAccountType = beneAccountType;
	}
	public List<String> getAvailableCurrencyList() {
		return availableCurrencyList;
	}
	public void setAvailableCurrencyList(List<String> availableCurrencyList) {
		this.availableCurrencyList = availableCurrencyList;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	
}
