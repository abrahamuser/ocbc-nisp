package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ObBeneAccountBean extends ObAccountBean implements Serializable{
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
//	private String version;
	private String benePicture;
	private String bankID;
	private String transServiceGrp;
	private String transServiceGrpDesc;
	private String updateDate;
	private String isInterbank;
	private String registerFlag;
	private String transServiceCode;
	private Map<String, String> recurringType;
	private String interval;
	private String alias;
	private String recur;
	private String phoneNum;
	private ListPurposeCode purposeType;
	private String beneName;
	private String remark;
	private String transferDt;
	private String transactionStatusCode;
	private String transactionStatusCodeDesc;
	private String crossCcy;
	private String requestTime;
	private String responseTime;
	private String numberOfRecurring;
	private String transactionId;
	private String isFavFlag;
	private String exchangeRateCcy;
	private String futureDate;
	private String OLT;
	private String LLG;
	private String RTGS;
	private String isOnline;
	private String amountCcy;
	private Object listCcyCode;
	private String saveBeneFlag;
	private String citizenFlag;
	private String residentFlag;
	private String beneBankCityId;
	private String beneBankClearingId;
	private String beneBankRTGSCode;
	private String recurringDate;
	private Map<String, String> beneCategoryList;
	private Map<String, String> beneResidentStatus;
	private String recurErrorCode;
	private String recurErrorDesc;
	private String recurringFlag;
	private List<String> lsBeneAccountCcyCode;
	private String transferServiceCode;
	private String beneCategory;
	private String residentStatus;
	private byte[] uploadBenePicture;
	
	
	
	
	private String debitAccountNo;
	private String debitAccountCcy;
	private String debitCif;
	private String debitAccountMcBit;
	private String debitAccountBranch;
	private String debitAccountType;
	private String beneAccountNo;
	private String beneAccountName;
	private String debitUserName;
	private String beneAccountCcy;
	private String beneAccountStatusCode;
	private String beneficiaryBankBranchCode;
	private String beneficiaryBankCode;
	private String beneficiaryBankName;
	private String bankCountryCode;
	private String beneAccountType;
	private String beneAccountMcBit;
	private BigDecimal beneficiaryBalance;
	private BigDecimal beneficiaryAvailableBalance;
	private BigDecimal beneficiaryHoldBalance;
	private String beneIsExisting;
	private String intervalDesc;
	private String shortName;
	private String isOpenFlag;
	private String errorCode;
	private String billingReferenceNo;
	private String onlineSessionId;
	private String xmlDetails;
	private BigDecimal billingAmount;
	private String transactionUUID;
	private String beneCategoryDesc;
	private BigDecimal exchangedAmount;
	
	private ObTransferPurposeTypeBean obTransPurposeTypeList;
	private ObRecurringTypeBean obRecurringTypeList;
	
	private String beneNickName;
	private String beneCategoryCode;
	private String beneResidentStatusCode;
	private String beneResidentStatusDesc;
	private String beneficiaryBankBranch;
	private String flagSyariah;
	private String beneErrorCode;
	private String beneErrorDesc;
	
	private String beneAddress1;
	private String beneAddress2;
	private String beneAddress3;
	private String bankCity;
	
	 private String bankAddress1;
	 private String bankAddress2;
	 private String bankAddress3;
	 
	 private String p2pFlag;
	 
	 private String proxy_type;
	 private String proxy_data;
	    
	
	public String getCitizenFlag() {
		return citizenFlag;
	}
	public void setCitizenFlag(String citizenFlag) {
		this.citizenFlag = citizenFlag;
	}
	public String getResidentFlag() {
		return residentFlag;
	}
	public void setResidentFlag(String residentFlag) {
		this.residentFlag = residentFlag;
	}
	public String getBeneBankCityId() {
		return beneBankCityId;
	}
	public void setBeneBankCityId(String beneBankCityId) {
		this.beneBankCityId = beneBankCityId;
	}
	public String getBeneBankClearingId() {
		return beneBankClearingId;
	}
	public void setBeneBankClearingId(String beneBankClearingId) {
		this.beneBankClearingId = beneBankClearingId;
	}
	public String getBeneBankRTGSCode() {
		return beneBankRTGSCode;
	}
	public void setBeneBankRTGSCode(String beneBankRTGSCode) {
		this.beneBankRTGSCode = beneBankRTGSCode;
	}
	public String getSaveBeneFlag() {
		return saveBeneFlag;
	}
	public void setSaveBeneFlag(String saveBeneFlag) {
		this.saveBeneFlag = saveBeneFlag;
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
	public String getBankNameLocale1() {
		return bankNameLocale1;
	}
	public void setBankNameLocale1(String bankNameLocale1) {
		this.bankNameLocale1 = bankNameLocale1;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceNameLocale1() {
		return provinceNameLocale1;
	}
	public void setProvinceNameLocale1(String provinceNameLocale1) {
		this.provinceNameLocale1 = provinceNameLocale1;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public BigDecimal getForexRate() {
		return forexRate;
	}
	public void setForexRate(BigDecimal forexRate) {
		this.forexRate = forexRate;
	}
	public BigDecimal getLocalTransactionAmount() {
		return localTransactionAmount;
	}
	public void setLocalTransactionAmount(BigDecimal localTransactionAmount) {
		this.localTransactionAmount = localTransactionAmount;
	}
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
	/*public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}*/
	
	public String getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(String benePicture) {
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
	
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
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
	public String getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(String isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public String getExchangeRateCcy() {
		return exchangeRateCcy;
	}
	public void setExchangeRateCcy(String exchangeRateCcy) {
		this.exchangeRateCcy = exchangeRateCcy;
	}
	public String getFutureDate() {
		return futureDate;
	}
	public void setFutureDate(String futureDate) {
		this.futureDate = futureDate;
	}
	public String getOLT() {
		return OLT;
	}
	public void setOLT(String oLT) {
		OLT = oLT;
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
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public Object getListCcyCode() {
		return listCcyCode;
	}
	public void setListCcyCode(Object listCcyCode) {
		this.listCcyCode = listCcyCode;
	}
	public String getTransactionStatusCodeDesc() {
		return transactionStatusCodeDesc;
	}
	public void setTransactionStatusCodeDesc(String transactionStatusCodeDesc) {
		this.transactionStatusCodeDesc = transactionStatusCodeDesc;
	}
	public Map<String, String> getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(Map<String, String> recurringType) {
		this.recurringType = recurringType;
	}
	public String getRecurringDate() {
		return recurringDate;
	}
	public void setRecurringDate(String recurringDate) {
		this.recurringDate = recurringDate;
	}
	
	public Map<String, String> getBeneCategoryList() {
		return beneCategoryList;
	}
	public void setBeneCategoryList(Map<String, String> beneCategoryList) {
		this.beneCategoryList = beneCategoryList;
	}
	public Map<String, String> getBeneResidentStatus() {
		return beneResidentStatus;
	}
	public void setBeneResidentStatus(Map<String, String> beneResidentStatus) {
		this.beneResidentStatus = beneResidentStatus;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getRecurErrorCode() {
		return recurErrorCode;
	}
	public void setRecurErrorCode(String recurErrorCode) {
		this.recurErrorCode = recurErrorCode;
	}
	public String getRecurErrorDesc() {
		return recurErrorDesc;
	}
	public void setRecurErrorDesc(String recurErrorDesc) {
		this.recurErrorDesc = recurErrorDesc;
	}
	public String getRecurringFlag() {
		return recurringFlag;
	}
	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}
	
	public List<String> getLsBeneAccountCcyCode() {
		return lsBeneAccountCcyCode;
	}
	public void setLsBeneAccountCcyCode(List<String> lsBeneAccountCcyCode) {
		this.lsBeneAccountCcyCode = lsBeneAccountCcyCode;
	}
	public String getTransferServiceCode() {
		return transferServiceCode;
	}
	public void setTransferServiceCode(String transferServiceCode) {
		this.transferServiceCode = transferServiceCode;
	}
	public String getBeneCategory() {
		return beneCategory;
	}
	public void setBeneCategory(String beneCategory) {
		this.beneCategory = beneCategory;
	}
	public String getResidentStatus() {
		return residentStatus;
	}
	public void setResidentStatus(String residentStatus) {
		this.residentStatus = residentStatus;
	}
	
	public String getDebitAccountNo() {
		return debitAccountNo;
	}
	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}
	public String getDebitAccountCcy() {
		return debitAccountCcy;
	}
	public void setDebitAccountCcy(String debitAccountCcy) {
		this.debitAccountCcy = debitAccountCcy;
	}
	public String getDebitCif() {
		return debitCif;
	}
	public void setDebitCif(String debitCif) {
		this.debitCif = debitCif;
	}
	public String getDebitAccountMcBit() {
		return debitAccountMcBit;
	}
	public void setDebitAccountMcBit(String debitAccountMcBit) {
		this.debitAccountMcBit = debitAccountMcBit;
	}
	public String getDebitAccountBranch() {
		return debitAccountBranch;
	}
	public void setDebitAccountBranch(String debitAccountBranch) {
		this.debitAccountBranch = debitAccountBranch;
	}
	public String getDebitAccountType() {
		return debitAccountType;
	}
	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneAccountName() {
		return beneAccountName;
	}
	public void setBeneAccountName(String beneAccountName) {
		this.beneAccountName = beneAccountName;
	}
	public String getDebitUserName() {
		return debitUserName;
	}
	public void setDebitUserName(String debitUserName) {
		this.debitUserName = debitUserName;
	}
	public String getBeneAccountCcy() {
		return beneAccountCcy;
	}
	public void setBeneAccountCcy(String beneAccountCcy) {
		this.beneAccountCcy = beneAccountCcy;
	}
	public String getBeneAccountStatusCode() {
		return beneAccountStatusCode;
	}
	public void setBeneAccountStatusCode(String beneAccountStatusCode) {
		this.beneAccountStatusCode = beneAccountStatusCode;
	}
	public String getBeneficiaryBankBranchCode() {
		return beneficiaryBankBranchCode;
	}
	public void setBeneficiaryBankBranchCode(String beneficiaryBankBranchCode) {
		this.beneficiaryBankBranchCode = beneficiaryBankBranchCode;
	}
	public String getBeneficiaryBankCode() {
		return beneficiaryBankCode;
	}
	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		this.beneficiaryBankCode = beneficiaryBankCode;
	}
	public String getBeneAccountType() {
		return beneAccountType;
	}
	public void setBeneAccountType(String beneAccountType) {
		this.beneAccountType = beneAccountType;
	}
	public String getBeneAccountMcBit() {
		return beneAccountMcBit;
	}
	public void setBeneAccountMcBit(String beneAccountMcBit) {
		this.beneAccountMcBit = beneAccountMcBit;
	}
	public BigDecimal getBeneficiaryBalance() {
		return beneficiaryBalance;
	}
	public void setBeneficiaryBalance(BigDecimal beneficiaryBalance) {
		this.beneficiaryBalance = beneficiaryBalance;
	}
	public BigDecimal getBeneficiaryAvailableBalance() {
		return beneficiaryAvailableBalance;
	}
	public void setBeneficiaryAvailableBalance(BigDecimal beneficiaryAvailableBalance) {
		this.beneficiaryAvailableBalance = beneficiaryAvailableBalance;
	}
	public BigDecimal getBeneficiaryHoldBalance() {
		return beneficiaryHoldBalance;
	}
	public void setBeneficiaryHoldBalance(BigDecimal beneficiaryHoldBalance) {
		this.beneficiaryHoldBalance = beneficiaryHoldBalance;
	}
	public String getBeneIsExisting() {
		return beneIsExisting;
	}
	public void setBeneIsExisting(String beneIsExisting) {
		this.beneIsExisting = beneIsExisting;
	}
	public String getIntervalDesc() {
		return intervalDesc;
	}
	public void setIntervalDesc(String intervalDesc) {
		this.intervalDesc = intervalDesc;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getIsOpenFlag() {
		return isOpenFlag;
	}
	public void setIsOpenFlag(String isOpenFlag) {
		this.isOpenFlag = isOpenFlag;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getBillingReferenceNo() {
		return billingReferenceNo;
	}
	public void setBillingReferenceNo(String billingReferenceNo) {
		this.billingReferenceNo = billingReferenceNo;
	}
	public String getOnlineSessionId() {
		return onlineSessionId;
	}
	public void setOnlineSessionId(String onlineSessionId) {
		this.onlineSessionId = onlineSessionId;
	}
	public String getXmlDetails() {
		return xmlDetails;
	}
	public void setXmlDetails(String xmlDetails) {
		this.xmlDetails = xmlDetails;
	}
	
	public BigDecimal getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(BigDecimal billingAmount) {
		this.billingAmount = billingAmount;
	}
	public ObTransferPurposeTypeBean getObTransPurposeTypeList() {
		return obTransPurposeTypeList;
	}
	public void setObTransPurposeTypeList(ObTransferPurposeTypeBean obTransPurposeTypeList) {
		this.obTransPurposeTypeList = obTransPurposeTypeList;
	}
	public ObRecurringTypeBean getObRecurringTypeList() {
		return obRecurringTypeList;
	}
	public void setObRecurringTypeList(ObRecurringTypeBean obRecurringTypeList) {
		this.obRecurringTypeList = obRecurringTypeList;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTransactionUUID() {
		return transactionUUID;
	}
	public void setTransactionUUID(String transactionUUID) {
		this.transactionUUID = transactionUUID;
	}
	public String getBeneCategoryDesc() {
		return beneCategoryDesc;
	}
	public void setBeneCategoryDesc(String beneCategoryDesc) {
		this.beneCategoryDesc = beneCategoryDesc;
	}
	public BigDecimal getExchangedAmount() {
		return exchangedAmount;
	}
	public void setExchangedAmount(BigDecimal exchangedAmount) {
		this.exchangedAmount = exchangedAmount;
	}
	public ListPurposeCode getPurposeType() {
		return purposeType;
	}
	public void setPurposeType(ListPurposeCode purposeType) {
		this.purposeType = purposeType;
	}
	public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}
	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
	}
	public String getBeneNickName() {
		return beneNickName;
	}
	public void setBeneNickName(String beneNickName) {
		this.beneNickName = beneNickName;
	}
	public String getBeneCategoryCode() {
		return beneCategoryCode;
	}
	public void setBeneCategoryCode(String beneCategoryCode) {
		this.beneCategoryCode = beneCategoryCode;
	}
	public String getBeneResidentStatusCode() {
		return beneResidentStatusCode;
	}
	public void setBeneResidentStatusCode(String beneResidentStatusCode) {
		this.beneResidentStatusCode = beneResidentStatusCode;
	}
	public String getBeneficiaryBankBranch() {
		return beneficiaryBankBranch;
	}
	public void setBeneficiaryBankBranch(String beneficiaryBankBranch) {
		this.beneficiaryBankBranch = beneficiaryBankBranch;
	}
	public String getFlagSyariah() {
		return flagSyariah;
	}
	public void setFlagSyariah(String flagSyariah) {
		this.flagSyariah = flagSyariah;
	}
	public byte[] getUploadBenePicture() {
		return uploadBenePicture;
	}
	public void setUploadBenePicture(byte[] uploadBenePicture) {
		this.uploadBenePicture = uploadBenePicture;
	}
	public String getBeneErrorCode() {
		return beneErrorCode;
	}
	public void setBeneErrorCode(String beneErrorCode) {
		this.beneErrorCode = beneErrorCode;
	}
	public String getBeneErrorDesc() {
		return beneErrorDesc;
	}
	public void setBeneErrorDesc(String beneErrorDesc) {
		this.beneErrorDesc = beneErrorDesc;
	}
	public String getBeneResidentStatusDesc() {
		return beneResidentStatusDesc;
	}
	public void setBeneResidentStatusDesc(String beneResidentStatusDesc) {
		this.beneResidentStatusDesc = beneResidentStatusDesc;
	}
	public String getBeneAddress1() {
		return beneAddress1;
	}
	public void setBeneAddress1(String beneAddress1) {
		this.beneAddress1 = beneAddress1;
	}
	public String getBeneAddress2() {
		return beneAddress2;
	}
	public void setBeneAddress2(String beneAddress2) {
		this.beneAddress2 = beneAddress2;
	}
	public String getBeneAddress3() {
		return beneAddress3;
	}
	public void setBeneAddress3(String beneAddress3) {
		this.beneAddress3 = beneAddress3;
	}
	public String getBankCountryCode() {
		return bankCountryCode;
	}
	public void setBankCountryCode(String bankCountryCode) {
		this.bankCountryCode = bankCountryCode;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankAddress1() {
		return bankAddress1;
	}
	public void setBankAddress1(String bankAddress1) {
		this.bankAddress1 = bankAddress1;
	}
	public String getBankAddress2() {
		return bankAddress2;
	}
	public void setBankAddress2(String bankAddress2) {
		this.bankAddress2 = bankAddress2;
	}
	public String getBankAddress3() {
		return bankAddress3;
	}
	public void setBankAddress3(String bankAddress3) {
		this.bankAddress3 = bankAddress3;
	}
	public String getP2pFlag() {
		return p2pFlag;
	}
	public void setP2pFlag(String p2pFlag) {
		this.p2pFlag = p2pFlag;
	}
	public String getProxy_type() {
		return proxy_type;
	}
	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	public String getProxy_data() {
		return proxy_data;
	}
	public void setProxy_data(String proxy_data) {
		this.proxy_data = proxy_data;
	}
	
	
}
