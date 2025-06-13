package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String picture; //for FE use
	
	private Boolean citizenFlag;
	private Boolean deleteFlag;
	private Boolean isCheck;
	private Boolean isFavFlag;
	private Boolean residentFlag;
	private byte[] benePicture;
	private String acctBranchCode;
	private String bankId;
	private String bankName;
	private String bankNumber;
	private String beneAccountCcy;
	private String beneAccountNo;
	private String beneAccountType;
	private String beneBankCityId;
	private String beneBankClearingId;
	private String beneBankRTGSCode;
	private String beneCategoryCode;
	private String beneficiaryBankCode;
	private String beneficiaryId;
	private String beneficiaryName;
	private String beneMcBit;
	private String beneResidentStatusCode;
	private String beneResidentStatusDesc;
	private String branchName;
	private String cif;
	private String email;
	private String errorCode;
	private String errorDesc;
	private String nickName;
	private String otherRelationshipDesc;
	private String phoneNumber;
	private String relationshipCode;
	private String relationshipDesc;
	private String relationshipToCustomer;
	private String responseCodeOTP;
	private String shortName;
	private String statementDesc;
	private String transferServiceCode;
	private String transferServiceGrp;
	private String transferServiceGrpDesc;
	private String updateDate;
	private String version;
	
	
	private Integer billerAutodebit;
    private Integer billerFlowType;
    private Integer billerFuture;
    private Integer billerInputAmountBit;
    private String billerCd;
    private String billerCustId;
    private String billerCustNickName;
    private String billerGroupCd;
    private String billerGroupName;
    private String billerId;
    private String billerName;
    private String billerSoaType;
    private String billerType;
    private String createdBy;
    private Boolean isDel;
    private String payeeId;
    private String timeCreated;
    private String timeUpdated;
    private String updatedBy;
    private String userCif;
    
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    
    private String address1;
	private String cityCode;
	private String cityId;
	private String cityName;
	private String countryCode;
	private String countryName;
	private String maintenanceFlag;
	
    
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Boolean getCitizenFlag() {
		return citizenFlag;
	}
	public void setCitizenFlag(Boolean citizenFlag) {
		this.citizenFlag = citizenFlag;
	}
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Boolean getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}
	public Boolean getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(Boolean isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public Boolean getResidentFlag() {
		return residentFlag;
	}
	public void setResidentFlag(Boolean residentFlag) {
		this.residentFlag = residentFlag;
	}
	public byte[] getBenePicture() {
		return benePicture;
	}
	public void setBenePicture(byte[] benePicture) {
		this.benePicture = benePicture;
	}
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
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
	public String getBeneCategoryCode() {
		return beneCategoryCode;
	}
	public void setBeneCategoryCode(String beneCategoryCode) {
		this.beneCategoryCode = beneCategoryCode;
	}
	public String getBeneficiaryBankCode() {
		return beneficiaryBankCode;
	}
	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		this.beneficiaryBankCode = beneficiaryBankCode;
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
	public String getBeneResidentStatusCode() {
		return beneResidentStatusCode;
	}
	public void setBeneResidentStatusCode(String beneResidentStatusCode) {
		this.beneResidentStatusCode = beneResidentStatusCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getOtherRelationshipDesc() {
		return otherRelationshipDesc;
	}
	public void setOtherRelationshipDesc(String otherRelationshipDesc) {
		this.otherRelationshipDesc = otherRelationshipDesc;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRelationshipCode() {
		return relationshipCode;
	}
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}
	public String getRelationshipDesc() {
		return relationshipDesc;
	}
	public void setRelationshipDesc(String relationshipDesc) {
		this.relationshipDesc = relationshipDesc;
	}
	public String getRelationshipToCustomer() {
		return relationshipToCustomer;
	}
	public void setRelationshipToCustomer(String relationshipToCustomer) {
		this.relationshipToCustomer = relationshipToCustomer;
	}
	public String getResponseCodeOTP() {
		return responseCodeOTP;
	}
	public void setResponseCodeOTP(String responseCodeOTP) {
		this.responseCodeOTP = responseCodeOTP;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getStatementDesc() {
		return statementDesc;
	}
	public void setStatementDesc(String statementDesc) {
		this.statementDesc = statementDesc;
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
	public Integer getBillerAutodebit() {
		return billerAutodebit;
	}
	public void setBillerAutodebit(Integer billerAutodebit) {
		this.billerAutodebit = billerAutodebit;
	}
	public Integer getBillerFlowType() {
		return billerFlowType;
	}
	public void setBillerFlowType(Integer billerFlowType) {
		this.billerFlowType = billerFlowType;
	}
	public Integer getBillerFuture() {
		return billerFuture;
	}
	public void setBillerFuture(Integer billerFuture) {
		this.billerFuture = billerFuture;
	}
	public Integer getBillerInputAmountBit() {
		return billerInputAmountBit;
	}
	public void setBillerInputAmountBit(Integer billerInputAmountBit) {
		this.billerInputAmountBit = billerInputAmountBit;
	}
	public String getBillerCd() {
		return billerCd;
	}
	public void setBillerCd(String billerCd) {
		this.billerCd = billerCd;
	}
	public String getBillerCustId() {
		return billerCustId;
	}
	public void setBillerCustId(String billerCustId) {
		this.billerCustId = billerCustId;
	}
	public String getBillerCustNickName() {
		return billerCustNickName;
	}
	public void setBillerCustNickName(String billerCustNickName) {
		this.billerCustNickName = billerCustNickName;
	}
	public String getBillerGroupCd() {
		return billerGroupCd;
	}
	public void setBillerGroupCd(String billerGroupCd) {
		this.billerGroupCd = billerGroupCd;
	}
	public String getBillerGroupName() {
		return billerGroupName;
	}
	public void setBillerGroupName(String billerGroupName) {
		this.billerGroupName = billerGroupName;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getBillerSoaType() {
		return billerSoaType;
	}
	public void setBillerSoaType(String billerSoaType) {
		this.billerSoaType = billerSoaType;
	}
	public String getBillerType() {
		return billerType;
	}
	public void setBillerType(String billerType) {
		this.billerType = billerType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getIsDel() {
		return isDel;
	}
	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public String getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getTimeUpdated() {
		return timeUpdated;
	}
	public void setTimeUpdated(String timeUpdated) {
		this.timeUpdated = timeUpdated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUserCif() {
		return userCif;
	}
	public void setUserCif(String userCif) {
		this.userCif = userCif;
	}
	public String getLabel1() {
		return label1;
	}
	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public String getLabel3() {
		return label3;
	}
	public void setLabel3(String label3) {
		this.label3 = label3;
	}
	public String getLabel4() {
		return label4;
	}
	public void setLabel4(String label4) {
		this.label4 = label4;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getMaintenanceFlag() {
		return maintenanceFlag;
	}
	public void setMaintenanceFlag(String maintenanceFlag) {
		this.maintenanceFlag = maintenanceFlag;
	}
	public String getBeneResidentStatusDesc() {
		return beneResidentStatusDesc;
	}
	public void setBeneResidentStatusDesc(String beneResidentStatusDesc) {
		this.beneResidentStatusDesc = beneResidentStatusDesc;
	}

}
