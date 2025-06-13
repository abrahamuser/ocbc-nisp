package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ObTransactionBean extends ObAccountBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transCode;
	private String transDesc;
	private String transDt;
	private String futureDt;
	private String postingDt;
	private BigDecimal amount;
	
	
	//transaction history
	private String channelCode;
	private String fromAccountNumber;
	private String payeeAccountTypeCode;
	private String payeeBankCode;
	private String payeeBankName;
	private String payeeName;
	private String referenceNumber;
	private BigDecimal serviceChargeAmount;
	private String toAccountNumber;
	private String transactionCurrencyCode;
	
	private String transactionStatusCode;
    private String transactionUUID;
    private String typeCode;
    private String transactionTypeCode; 
    private String transactionTypeDesc;
    
    private String payeeProvinceCode;
    private String payeeProvinceName;
    private String payeeBranchCod;
    private String payeeBranchName;
    private String otherInfo;
    private BigDecimal localTransactionAmount;
    private String swiftCode;
    private String payeeNickName;
    private BigDecimal toForexRate;
    private BigDecimal indicativeAmount;
    private String payeeBankAddress;
    private String alias;
    private String recur;
    private String phoneNum;
    private String email;
    private String purposeType;
    private String purposeTypeDesc;
    private String recurringType;
    private Integer numOfRecurring;
    private String beneName;
    
    private ObAccountBean ObAccResult;
	private ObFavoriteAccountBean ObFavAccResult;
	private ObBeneAccountBean ObBeneAccResult;
	
	private String crossCurrency;
	private String amountCcy;
	private String recurringFlag;
	private String LLG;
	private String RTGS;
	
	
    
    public String getPayeeBankAddress() {
		return payeeBankAddress;
	}
	public void setPayeeBankAddress(String payeeBankAddress) {
		this.payeeBankAddress = payeeBankAddress;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getPayeeNickName() {
		return payeeNickName;
	}
	public void setPayeeNickName(String payeeNickName) {
		this.payeeNickName = payeeNickName;
	}
	public BigDecimal getToForexRate() {
		return toForexRate;
	}
	public void setToForexRate(BigDecimal toForexRate) {
		this.toForexRate = toForexRate;
	}
	public BigDecimal getIndicativeAmount() {
		return indicativeAmount;
	}
	public void setIndicativeAmount(BigDecimal indicativeAmount) {
		this.indicativeAmount = indicativeAmount;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getTransDesc() {
		return transDesc;
	}
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	public String getTransDt() {
		return transDt;
	}
	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}
	public String getPostingDt() {
		return postingDt;
	}
	public void setPostingDt(String postingDt) {
		this.postingDt = postingDt;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getFromAccountNumber() {
		return fromAccountNumber;
	}
	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public String getPayeeAccountTypeCode() {
		return payeeAccountTypeCode;
	}
	public void setPayeeAccountTypeCode(String payeeAccountTypeCode) {
		this.payeeAccountTypeCode = payeeAccountTypeCode;
	}
	public String getPayeeBankCode() {
		return payeeBankCode;
	}
	public void setPayeeBankCode(String payeeBankCode) {
		this.payeeBankCode = payeeBankCode;
	}
	public String getPayeeBankName() {
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public String getTransactionCurrencyCode() {
		return transactionCurrencyCode;
	}
	public void setTransactionCurrencyCode(String transactionCurrencyCode) {
		this.transactionCurrencyCode = transactionCurrencyCode;
	}
	
	public String getTransactionStatusCode() {
		return transactionStatusCode;
	}
	public void setTransactionStatusCode(String transactionStatusCode) {
		this.transactionStatusCode = transactionStatusCode;
	}
	public String getTransactionUUID() {
		return transactionUUID;
	}
	public void setTransactionUUID(String transactionUUID) {
		this.transactionUUID = transactionUUID;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}
	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}
	public BigDecimal getServiceChargeAmount() {
		return serviceChargeAmount;
	}
	public void setServiceChargeAmount(BigDecimal serviceChargeAmount) {
		this.serviceChargeAmount = serviceChargeAmount;
	}
	public String getTransactionTypeDesc() {
		return transactionTypeDesc;
	}
	public void setTransactionTypeDesc(String transactionTypeDesc) {
		this.transactionTypeDesc = transactionTypeDesc;
	}
	public String getPayeeProvinceCode() {
		return payeeProvinceCode;
	}
	public void setPayeeProvinceCode(String payeeProvinceCode) {
		this.payeeProvinceCode = payeeProvinceCode;
	}
	public String getPayeeProvinceName() {
		return payeeProvinceName;
	}
	public void setPayeeProvinceName(String payeeProvinceName) {
		this.payeeProvinceName = payeeProvinceName;
	}
	public String getPayeeBranchCod() {
		return payeeBranchCod;
	}
	public void setPayeeBranchCod(String payeeBranchCod) {
		this.payeeBranchCod = payeeBranchCod;
	}
	public String getPayeeBranchName() {
		return payeeBranchName;
	}
	public void setPayeeBranchName(String payeeBranchName) {
		this.payeeBranchName = payeeBranchName;
	}
	
	public BigDecimal getLocalTransactionAmount() {
		return localTransactionAmount;
	}
	public void setLocalTransactionAmount(BigDecimal localTransactionAmount) {
		this.localTransactionAmount = localTransactionAmount;
	}
	public String getFutureDt() {
		return futureDt;
	}
	public void setFutureDt(String futureDt) {
		this.futureDt = futureDt;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getRecur() {
		return recur;
	}
	public void setRecur(String recur) {
		this.recur = recur;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public ObAccountBean getObAccResult() {
		return ObAccResult;
	}
	public void setObAccResult(ObAccountBean obAccResult) {
		ObAccResult = obAccResult;
	}
	public ObFavoriteAccountBean getObFavAccResult() {
		return ObFavAccResult;
	}
	public void setObFavAccResult(ObFavoriteAccountBean obFavAccResult) {
		ObFavAccResult = obFavAccResult;
	}

	public String getPurposeType() {
		return purposeType;
	}
	public void setPurposeType(String purposeType) {
		this.purposeType = purposeType;
	}
	public String getPurposeTypeDesc() {
		return purposeTypeDesc;
	}
	public void setPurposeTypeDesc(String purposeTypeDesc) {
		this.purposeTypeDesc = purposeTypeDesc;
	}
	public String getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
	}
	public Integer getNumOfRecurring() {
		return numOfRecurring;
	}
	public void setNumOfRecurring(Integer numOfRecurring) {
		this.numOfRecurring = numOfRecurring;
	}
	public String getCrossCurrency() {
		return crossCurrency;
	}
	public void setCrossCurrency(String crossCurrency) {
		this.crossCurrency = crossCurrency;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public String getRecurringFlag() {
		return recurringFlag;
	}
	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}
	public String getLLG() {
		return LLG;
	}
	public void setLLG(String lLG) {
		LLG = lLG;
	}
	public String getRTGS() {
		return RTGS;
	}
	public void setRTGS(String rTGS) {
		RTGS = rTGS;
	}
	public ObBeneAccountBean getObBeneAccResult() {
		return ObBeneAccResult;
	}
	public void setObBeneAccResult(ObBeneAccountBean obBeneAccResult) {
		ObBeneAccResult = obBeneAccResult;
	}

	
	
	
}

