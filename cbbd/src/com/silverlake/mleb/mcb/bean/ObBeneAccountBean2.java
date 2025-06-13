package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class ObBeneAccountBean2 extends ObAccountBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;    
	
//	private BigDecimal amount;
//	private BigDecimal balAftTrans;
//	private BigDecimal beneficiaryAvailableBalance;
//	private BigDecimal beneficiaryBalance;
//	private BigDecimal beneficiaryHoldBalance;
//	private BigDecimal billingAmount;
//	private BigDecimal exchangedAmount;
//	private BigDecimal forexRate;
//	private BigDecimal localTransactionAmount;
//	private BigDecimal serviceCharge;
//	private byte[] uploadBenePicture;
//	private int sequence;
//	private List<String> lsBeneAccountCcyCode;
//	private ListPurposeCode purposeType;
//	private Map<String, String> beneCategoryList;
//	private Map<String, String> beneResidentStatus;
//	private Map<String, String> recurringType;
//	private Object listCcyCode;
//	private ObRecurringTypeBean obRecurringTypeList;
//	private ObTransferPurposeTypeBean obTransPurposeTypeList;
//	private String alias;
//	private String amountCcy;
//	private String authorizationCode;
//	private String bankAddress;
//	private String bankID;
//	private String bankNameLocale1;
//	private String beneAccountMcBit;
//	private String beneAccountName;
//	private String beneAccountStatusCode;
//	private String beneCategory;
//	private String beneCategoryCode;
//	private String beneCategoryDesc;
//	private String beneErrorCode;
//	private String beneErrorDesc;	
//	private String beneficiaryBankBranch;
//	private String beneficiaryBankBranchCode;
//	private String beneficiaryBankCode;
//	private String beneficiaryBankName;
//	private String beneID;
//	private String beneIsExisting;
//	private String beneName;
//	private String beneNickName;
//	private String benePicture;
//	private String beneResidentStatusCode;
//	private String billingReferenceNo;
//	private String branchCode;
//	private String branchNameLocale1;
//	private String code;
//	private String crossCcy;
//	private String debitAccountBranch;
//	private String debitAccountCcy;
//	private String debitAccountMcBit;
//	private String debitAccountNo;
//	private String debitAccountType;
//	private String debitCif;
//	private String debitUserName;
//	private String errorCode;
//	private String exchangeRateCcy;
//	private String flagSyariah;
//	private String futureDate;
//	private String interval;
//	private String intervalDesc;
//	private String isInterbank;
//	private String isIsSupportTrsfFromCASA;
//	private String isOnline;
	private String isOpenFlag;
//	private String isSupportCASATrsf;
//	private String isSupportSML;
//	private String label;
//	private String LLG;
//	private String numberOfRecurring;
//	private String OLT;
//	private String onlineSessionId;
//	private String payeeUuid;
//	private String phoneNum;
//	private String provinceCode;
//	private String provinceName;
//	private String provinceNameLocale1;
//	private String recipientIdNo;
//	private String recipientIdTypeCode;
//	private String recipientName;
//	private String recipientNickName;
//	private String recipientRef;
//	private String recur;
//	private String recurErrorCode;
//	private String recurErrorDesc;
//	private String recurringDate;
//	private String recurringFlag;
//	private String registerFlag;
//	private String remark;
//	private String requestTime;
//	private String residentStatus;
//	private String responseTime;
//	private String RTGS;
//	private String saveBeneFlag;
//	private String smlCardBankCode;
//	private String smlCASABankCode;
//	private String swiftCode;
//	private String transactionId;
//	private String transactionStatusCode;
//	private String transactionStatusCodeDesc;
//	private String transactionUUID;
//	private String transCurrency;
//	private String transferDt;
//	private String transferServiceCode;
//	private String transServiceCode;
//	private String transServiceGrp;
//	private String transServiceGrpDesc;
//	private String transTypeCodeDesc;
//	private String typeCode;
//	private String userInputDesc;
//	private String xmlDetails;
	
	
	private String acctBranchCode;
	private String bankId;
	private String beneAccountCcy;
	private String beneAccountNo;
	private String beneAccountType;
	private String beneBankCityId;
	private String beneBankClearingId;
	private String beneBankRTGSCode;
	private String beneficiaryId;
	private String beneficiaryName;
	private String beneMcBit;
	private String benePicture;
	private String branchName;
	private String citizenFlag;
	private String email;
	private String isFavFlag;
	private String nickName;
	private String phoneNumber;
	private String residentFlag;
	private String shortName;
	private String transferServiceGrp;
	private String transferServiceGrpDesc;
	private String updateDate;
	private String version;
	
	//own account new
	private String exchangeRateCcy;
	private Boolean registerFlag;
	private String debitCif;
	private String paymentId;
	private List<String> listCcyCode;
	private List<String> listBeneCcyCode;
	private String debitAccountCcy;
	private String beneResidentStatusCode;
	private String beneResidentStatusDesc;
	
	//P3OCBCUAT-644
	private ObBankDetailBean bankDetailBean;
	
	public String getAcctBranchCode() {
		return acctBranchCode;
	}
	public void setAcctBranchCode(String acctBranchCode) {
		this.acctBranchCode = acctBranchCode;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBeneAccountCcy() {
		return beneAccountCcy;
	}
	public void setBeneAccountCcy(String beneAccountCcy) {
		this.beneAccountCcy = beneAccountCcy;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneAccountType() {
		return beneAccountType;
	}
	public void setBeneAccountType(String beneAccountType) {
		this.beneAccountType = beneAccountType;
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
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getBeneMcBit() {
		return beneMcBit;
	}
	public void setBeneMcBit(String beneMcBit) {
		this.beneMcBit = beneMcBit;
	}
	public String getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(String benePicture) {
		this.benePicture = benePicture;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCitizenFlag() {
		return citizenFlag;
	}
	public void setCitizenFlag(String citizenFlag) {
		this.citizenFlag = citizenFlag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(String isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getResidentFlag() {
		return residentFlag;
	}
	public void setResidentFlag(String residentFlag) {
		this.residentFlag = residentFlag;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getTransferServiceGrp() {
		return transferServiceGrp;
	}
	public void setTransferServiceGrp(String transferServiceGrp) {
		this.transferServiceGrp = transferServiceGrp;
	}
	public String getTransferServiceGrpDesc() {
		return transferServiceGrpDesc;
	}
	public void setTransferServiceGrpDesc(String transferServiceGrpDesc) {
		this.transferServiceGrpDesc = transferServiceGrpDesc;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIsOpenFlag() {
		return isOpenFlag;
	}
	public void setIsOpenFlag(String isOpenFlag) {
		this.isOpenFlag = isOpenFlag;
	}
	public String getExchangeRateCcy() {
		return exchangeRateCcy;
	}
	public void setExchangeRateCcy(String exchangeRateCcy) {
		this.exchangeRateCcy = exchangeRateCcy;
	}
	public Boolean getRegisterFlag() {
		return registerFlag;
	}
	public void setRegisterFlag(Boolean registerFlag) {
		this.registerFlag = registerFlag;
	}
	public String getDebitCif() {
		return debitCif;
	}
	public void setDebitCif(String debitCif) {
		this.debitCif = debitCif;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public List<String> getListCcyCode() {
		return listCcyCode;
	}
	public void setListCcyCode(List<String> listCcyCode) {
		this.listCcyCode = listCcyCode;
	}
	public String getDebitAccountCcy() {
		return debitAccountCcy;
	}
	public void setDebitAccountCcy(String debitAccountCcy) {
		this.debitAccountCcy = debitAccountCcy;
	}
	public List<String> getListBeneCcyCode() {
		return listBeneCcyCode;
	}
	public void setListBeneCcyCode(List<String> listBeneCcyCode) {
		this.listBeneCcyCode = listBeneCcyCode;
	}
	public String getBeneResidentStatusCode() {
		return beneResidentStatusCode;
	}
	public void setBeneResidentStatusCode(String beneResidentStatusCode) {
		this.beneResidentStatusCode = beneResidentStatusCode;
	}
	public String getBeneResidentStatusDesc() {
		return beneResidentStatusDesc;
	}
	public void setBeneResidentStatusDesc(String beneResidentStatusDesc) {
		this.beneResidentStatusDesc = beneResidentStatusDesc;
	}
	public ObBankDetailBean getBankDetailBean() {
		return bankDetailBean;
	}
	public void setBankDetailBean(ObBankDetailBean bankDetailBean) {
		this.bankDetailBean = bankDetailBean;
	}
	
}
