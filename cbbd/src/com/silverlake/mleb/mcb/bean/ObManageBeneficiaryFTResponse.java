package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ObManageBeneficiaryFTResponse extends ObResponse implements Serializable {
	
	private static final long serialVersionUID = -770990664205262040L;
	
	private String beneficiaryId;
	private String cif;
	private String beneAccountNo;
	private String beneAccountCcy;
	private String beneficiaryName;
	private String email;
	private String phoneNumber;
	private byte[] benePicture;
	private Integer version;
	private String bankId;
	private String bankName;
	private String transferServiceGrp;
	private String transferServiceGrpDesc;
	private String updateDate;
	private String nickName;
	private String citizenFlag;
	private String residentFlag;
	private String beneBankCityId;
	private String beneBankClearingId;
	private String beneBankRTGSCode;
	private String successMessage;
	private String errorCode;
	private String errorMessage;
	private String responseBeneficiaryCode;
	private String transactionId;
	private String beneficiaryIsExisting;
	private String isInterbank;
	private String isOnline;
	
	private Boolean isFavFlag;
	
	private MapPojoBean transferType;
	
	private MapPojoBean category;
	
	private MapPojoBean resident;
	
	private String bankCode;
	private String beneficiaryBankBranch;
	private String beneficiaryBankBranchCode;
	
	private String beneAccountType;
	private String beneMcBit;
	private String acctBranchCode;
	private String beneficiaryBankCode;
	private Boolean beneIsExisting;
	private String transferServiceCode;
	private List<String> listCcyCode;
    private String beneAccountStatusCode;
    
    private String flagSyariah;

    public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneAccountCcy() {
		return beneAccountCcy;
	}
	public void setBeneAccountCcy(String beneAccountCcy) {
		this.beneAccountCcy = beneAccountCcy;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public byte[] getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(byte[] benePicture) {
		this.benePicture = benePicture;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
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
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getResponseBeneficiaryCode() {
		return responseBeneficiaryCode;
	}
	public void setResponseBeneficiaryCode(String responseBeneficiaryCode) {
		this.responseBeneficiaryCode = responseBeneficiaryCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getBeneficiaryIsExisting() {
		return beneficiaryIsExisting;
	}
	public void setBeneficiaryIsExisting(String beneficiaryIsExisting) {
		this.beneficiaryIsExisting = beneficiaryIsExisting;
	}
	public String getIsInterbank() {
		return isInterbank;
	}
	public void setIsInterbank(String isInterbank) {
		this.isInterbank = isInterbank;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public MapPojoBean getTransferType() {
		return transferType;
	}
	public void setTransferType(MapPojoBean transferType) {
		this.transferType = transferType;
	}
	public MapPojoBean getCategory() {
		return category;
	}
	public void setCategory(MapPojoBean category) {
		this.category = category;
	}
	public MapPojoBean getResident() {
		return resident;
	}
	public void setResident(MapPojoBean resident) {
		this.resident = resident;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBeneficiaryBankBranch() {
		return beneficiaryBankBranch;
	}
	public void setBeneficiaryBankBranch(String beneficiaryBankBranch) {
		this.beneficiaryBankBranch = beneficiaryBankBranch;
	}
	public String getBeneficiaryBankBranchCode() {
		return beneficiaryBankBranchCode;
	}
	public void setBeneficiaryBankBranchCode(String beneficiaryBankBranchCode) {
		this.beneficiaryBankBranchCode = beneficiaryBankBranchCode;
	}
	public Boolean getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(Boolean isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public String getBeneAccountType() {
		return beneAccountType;
	}
	public void setBeneAccountType(String beneAccountType) {
		this.beneAccountType = beneAccountType;
	}
	public String getBeneMcBit() {
		return beneMcBit;
	}
	public void setBeneMcBit(String beneMcBit) {
		this.beneMcBit = beneMcBit;
	}
	public String getAcctBranchCode() {
		return acctBranchCode;
	}
	public void setAcctBranchCode(String acctBranchCode) {
		this.acctBranchCode = acctBranchCode;
	}
	public String getBeneficiaryBankCode() {
		return beneficiaryBankCode;
	}
	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		this.beneficiaryBankCode = beneficiaryBankCode;
	}
	public Boolean getBeneIsExisting() {
		return beneIsExisting;
	}
	public void setBeneIsExisting(Boolean beneIsExisting) {
		this.beneIsExisting = beneIsExisting;
	}
	public String getTransferServiceCode() {
		return transferServiceCode;
	}
	public void setTransferServiceCode(String transferServiceCode) {
		this.transferServiceCode = transferServiceCode;
	}
	public List<String> getListCcyCode() {
		return listCcyCode;
	}
	public void setListCcyCode(List<String> listCcyCode) {
		this.listCcyCode = listCcyCode;
	}
	public String getBeneAccountStatusCode() {
		return beneAccountStatusCode;
	}
	public void setBeneAccountStatusCode(String beneAccountStatusCode) {
		this.beneAccountStatusCode = beneAccountStatusCode;
	}
	public String getFlagSyariah() {
		return flagSyariah;
	}
	public void setFlagSyariah(String flagSyariah) {
		this.flagSyariah = flagSyariah;
	}
	
}
