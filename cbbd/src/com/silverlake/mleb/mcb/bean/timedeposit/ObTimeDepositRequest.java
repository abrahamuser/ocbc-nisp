package com.silverlake.mleb.mcb.bean.timedeposit;

import java.io.Serializable;
import java.math.BigDecimal;

import com.silverlake.mleb.mcb.bean.ObRequestCache;

public class ObTimeDepositRequest extends ObRequestCache<ObTimeDepositSessionCache> implements Serializable {

    private Boolean isCheckCutOff;
    private String productCode;
    private String currencyCode;
    
    //Confirmation
    private String debitAccountUUID;
    private String purposeCode;
//    private String productCode;
    private String sourceOfFundCode;
    private String rolloverTypeCode;
    private BigDecimal amount;
    private String amountCcy;
    private Integer tenor;
    private String tenorType;
    private Integer interestTerm;
    private String interestTermCode;
    private String promoCode;
    
    //Acknowledgement
    private String transactionId;
    
    //Account maintenance
    private String accountNo;
    
    private String ip;
    
    public Boolean getIsCheckCutOff() {
        return isCheckCutOff;
    }

    public void setIsCheckCutOff(Boolean checkCutOff) {
        isCheckCutOff = checkCutOff;
    }

	public String getPurposeCode() {
		return purposeCode;
	}

	public String getSourceOfFundCode() {
		return sourceOfFundCode;
	}

	public String getRolloverTypeCode() {
		return rolloverTypeCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getAmountCcy() {
		return amountCcy;
	}

	public Integer getTenor() {
		return tenor;
	}

	public String getTenorType() {
		return tenorType;
	}

	public Integer getInterestTerm() {
		return interestTerm;
	}

	public String getInterestTermCode() {
		return interestTermCode;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public void setSourceOfFundCode(String sourceOfFundCode) {
		this.sourceOfFundCode = sourceOfFundCode;
	}

	public void setRolloverTypeCode(String rolloverTypeCode) {
		this.rolloverTypeCode = rolloverTypeCode;
	}

	public String getDebitAccountUUID() {
		return debitAccountUUID;
	}

	public void setDebitAccountUUID(String debitAccountUUID) {
		this.debitAccountUUID = debitAccountUUID;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}

	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}

	public void setTenorType(String tenorType) {
		this.tenorType = tenorType;
	}

	public void setInterestTerm(Integer interestTerm) {
		this.interestTerm = interestTerm;
	}

	public void setInterestTermCode(String interestTermCode) {
		this.interestTermCode = interestTermCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
