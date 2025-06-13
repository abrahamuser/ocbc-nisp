package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObFavoriteAccountBean extends ObAccountBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;    
	private String recipientRef;
	private String recipientName;
	private String recipientNickName;
	private String recipientIdNo;
	private String recipientIdTypeCode;
	private String payeeUuid;
	private String typeCode;
	private String userInputDesc; 
	private String transCurrency; 
	private String authorizationCode;
	
	private BigDecimal amount;
	private BigDecimal serviceCharge;
	private BigDecimal balAftTrans;
	
	
	private String bankNameLocale1;
	private String provinceCode;
	private String provinceName;
	private String provinceNameLocale1;
	private String branchCode;
	private String branchName;
	private String branchNameLocale1;
	
	private String smlCardBankCode;
	private String smlCASABankCode;
	private String isSupportCASATrsf;
	private String isIsSupportTrsfFromCASA;
	private String isSupportSML;
	
	private String transTypeCodeDesc;
	private String swiftCode;
	private String bankAddress;
	private BigDecimal forexRate;
	private BigDecimal localTransactionAmount;
	private String code;
	private String label;
	private int sequence;
	
	private String beneID;
	private String beneMcBit;
	private String email;
	private String version;
	private String[] benePicture;
	private String bankID;
	private String transServiceGrp;
	private String transServiceGrpDesc;
	private String updateDate;
	private String isInterbank;
	private String registerFlag;
	private String transServiceCode;
	private String recurringType;
	private String alias;
	private String recur;
	private String phoneNum;
	private String purposeType;
	private String beneName;
	private String description;
	private String transferDt;
	private String transactionStatusCode;
	private String crossCcy;
	private String requestTime;
	private String responseTime;
	private String numberOfRecurring;
	private String transactionId;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public BigDecimal getLocalTransactionAmount() {
		return localTransactionAmount;
	}
	public void setLocalTransactionAmount(BigDecimal localTransactionAmount) {
		this.localTransactionAmount = localTransactionAmount;
	}
	public BigDecimal getForexRate() {
		return forexRate;
	}
	public void setForexRate(BigDecimal forexRate) {
		this.forexRate = forexRate;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getRecipientRef() {
		return recipientRef;
	}
	public void setRecipientRef(String recipientRef) {
		this.recipientRef = recipientRef;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientNickName() {
		return recipientNickName;
	}
	public void setRecipientNickName(String recipientNickName) {
		this.recipientNickName = recipientNickName;
	}
	public String getRecipientIdNo() {
		return recipientIdNo;
	}
	public void setRecipientIdNo(String recipientIdNo) {
		this.recipientIdNo = recipientIdNo;
	}
	public String getRecipientIdTypeCode() {
		return recipientIdTypeCode;
	}
	public void setRecipientIdTypeCode(String recipientIdTypeCode) {
		this.recipientIdTypeCode = recipientIdTypeCode;
	}
	public String getPayeeUuid() {
		return payeeUuid;
	}
	public void setPayeeUuid(String payeeUuid) {
		this.payeeUuid = payeeUuid;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getUserInputDesc() {
		return userInputDesc;
	}
	public void setUserInputDesc(String userInputDesc) {
		this.userInputDesc = userInputDesc;
	}
	
	public String getTransCurrency() {
		return transCurrency;
	}
	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public BigDecimal getBalAftTrans() {
		return balAftTrans;
	}
	public void setBalAftTrans(BigDecimal balAftTrans) {
		this.balAftTrans = balAftTrans;
	}
	
	
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBankNameLocale1() {
		return bankNameLocale1;
	}
	public void setBankNameLocale1(String bankNameLocale1) {
		this.bankNameLocale1 = bankNameLocale1;
	}
	public String getProvinceNameLocale1() {
		return provinceNameLocale1;
	}
	public void setProvinceNameLocale1(String provinceNameLocale1) {
		this.provinceNameLocale1 = provinceNameLocale1;
	}
	public String getBranchNameLocale1() {
		return branchNameLocale1;
	}
	public void setBranchNameLocale1(String branchNameLocale1) {
		this.branchNameLocale1 = branchNameLocale1;
	}
	public String getSmlCardBankCode() {
		return smlCardBankCode;
	}
	public void setSmlCardBankCode(String smlCardBankCode) {
		this.smlCardBankCode = smlCardBankCode;
	}
	public String getSmlCASABankCode() {
		return smlCASABankCode;
	}
	public void setSmlCASABankCode(String smlCASABankCode) {
		this.smlCASABankCode = smlCASABankCode;
	}
	public String getIsSupportCASATrsf() {
		return isSupportCASATrsf;
	}
	public void setIsSupportCASATrsf(String isSupportCASATrsf) {
		this.isSupportCASATrsf = isSupportCASATrsf;
	}
	public String getIsIsSupportTrsfFromCASA() {
		return isIsSupportTrsfFromCASA;
	}
	public void setIsIsSupportTrsfFromCASA(String isIsSupportTrsfFromCASA) {
		this.isIsSupportTrsfFromCASA = isIsSupportTrsfFromCASA;
	}
	public String getIsSupportSML() {
		return isSupportSML;
	}
	public void setIsSupportSML(String isSupportSML) {
		this.isSupportSML = isSupportSML;
	}
	public String getTransTypeCodeDesc() {
		return transTypeCodeDesc;
	}
	public void setTransTypeCodeDesc(String transTypeCodeDesc) {
		this.transTypeCodeDesc = transTypeCodeDesc;
	}
	public String getBeneID() {
		return beneID;
	}
	public void setBeneID(String beneID) {
		this.beneID = beneID;
	}
	public String getBeneMcBit() {
		return beneMcBit;
	}
	public void setBeneMcBit(String beneMcBit) {
		this.beneMcBit = beneMcBit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String[] getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(String[] benePicture) {
		this.benePicture = benePicture;
	}
	public String getBankID() {
		return bankID;
	}
	public void setBankID(String bankID) {
		this.bankID = bankID;
	}
	public String getTransServiceGrp() {
		return transServiceGrp;
	}
	public void setTransServiceGrp(String transServiceGrp) {
		this.transServiceGrp = transServiceGrp;
	}
	public String getTransServiceGrpDesc() {
		return transServiceGrpDesc;
	}
	public void setTransServiceGrpDesc(String transServiceGrpDesc) {
		this.transServiceGrpDesc = transServiceGrpDesc;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getIsInterbank() {
		return isInterbank;
	}
	public void setIsInterbank(String isInterbank) {
		this.isInterbank = isInterbank;
	}
	public String getRegisterFlag() {
		return registerFlag;
	}
	public void setRegisterFlag(String registerFlag) {
		this.registerFlag = registerFlag;
	}
	public String getTransServiceCode() {
		return transServiceCode;
	}
	public void setTransServiceCode(String transServiceCode) {
		this.transServiceCode = transServiceCode;
	}
	public String getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
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
	public String getPurposeType() {
		return purposeType;
	}
	public void setPurposeType(String purposeType) {
		this.purposeType = purposeType;
	}
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransferDt() {
		return transferDt;
	}
	public void setTransferDt(String transferDt) {
		this.transferDt = transferDt;
	}
	public String getTransactionStatusCode() {
		return transactionStatusCode;
	}
	public void setTransactionStatusCode(String transactionStatusCode) {
		this.transactionStatusCode = transactionStatusCode;
	}
	public String getCrossCcy() {
		return crossCcy;
	}
	public void setCrossCcy(String crossCcy) {
		this.crossCcy = crossCcy;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getNumberOfRecurring() {
		return numberOfRecurring;
	}
	public void setNumberOfRecurring(String numberOfRecurring) {
		this.numberOfRecurring = numberOfRecurring;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
	
}

