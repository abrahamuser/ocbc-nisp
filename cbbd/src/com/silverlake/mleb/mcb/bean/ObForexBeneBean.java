package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ObForexBeneBean implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private List<String> listCcyCode;
	private Boolean beneAccountMcBit;
	private Boolean registerFlag;
	private String bankId;
	private String bankName;
	private String branchName;
	private String beneAccountCcy;
	private String beneAccountName;
	private String beneAccountNo;
	private String beneAccountType;
	private String beneBankCityId;
	private String beneBankClearingId;
	private String beneBankRTGSCode;
	private String beneficiaryBankBranchCode;
	private String beneficiaryId;
	private String beneficiaryName;
	private String beneNickName;
	private String benePicture;
	private String cif;
	private String citizenFlag;
	private String debitAccountCcy;
	private String debitCif;
	private String email;
	private BigDecimal exchangeRate;
	private String exchangeRateCcy;
	private String isFavFlag;
	private String nickName;
	private String paymentId;
	private String phoneNumber;
	private String productCode;
	private String productName;
	private String residentFlag;
	private String transferServiceCode;
	private String transferServiceGrp;
	private String transferServiceGrpDesc;
	private String updateDate;
	private String version;
	
	public List<String> getListCcyCode() {
		return listCcyCode;
	}
	public void setListCcyCode(List<String> listCcyCode) {
		this.listCcyCode = listCcyCode;
	}
	public Boolean getBeneAccountMcBit() {
		return beneAccountMcBit;
	}
	public void setBeneAccountMcBit(Boolean beneAccountMcBit) {
		this.beneAccountMcBit = beneAccountMcBit;
	}
	public Boolean getRegisterFlag() {
		return registerFlag;
	}
	public void setRegisterFlag(Boolean registerFlag) {
		this.registerFlag = registerFlag;
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBeneAccountCcy() {
		return beneAccountCcy;
	}
	public void setBeneAccountCcy(String beneAccountCcy) {
		this.beneAccountCcy = beneAccountCcy;
	}
	public String getBeneAccountName() {
		return beneAccountName;
	}
	public void setBeneAccountName(String beneAccountName) {
		this.beneAccountName = beneAccountName;
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
	public String getBeneficiaryBankBranchCode() {
		return beneficiaryBankBranchCode;
	}
	public void setBeneficiaryBankBranchCode(String beneficiaryBankBranchCode) {
		this.beneficiaryBankBranchCode = beneficiaryBankBranchCode;
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
	public String getBeneNickName() {
		return beneNickName;
	}
	public void setBeneNickName(String beneNickName) {
		this.beneNickName = beneNickName;
	}
	public String getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(String benePicture) {
		this.benePicture = benePicture;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getCitizenFlag() {
		return citizenFlag;
	}
	public void setCitizenFlag(String citizenFlag) {
		this.citizenFlag = citizenFlag;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getExchangeRateCcy() {
		return exchangeRateCcy;
	}
	public void setExchangeRateCcy(String exchangeRateCcy) {
		this.exchangeRateCcy = exchangeRateCcy;
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
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getResidentFlag() {
		return residentFlag;
	}
	public void setResidentFlag(String residentFlag) {
		this.residentFlag = residentFlag;
	}
	public String getTransferServiceCode() {
		return transferServiceCode;
	}
	public void setTransferServiceCode(String transferServiceCode) {
		this.transferServiceCode = transferServiceCode;
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

}
