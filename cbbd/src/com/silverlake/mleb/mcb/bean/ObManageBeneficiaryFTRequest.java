package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObManageBeneficiaryFTRequest extends ObRequest implements Serializable {

	private static final long serialVersionUID = 5863605069060657840L;
	
	private String payeeId;
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
	private String beneficiaryBankBranchCode;
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
	
	private boolean isInterbank;
	private boolean isOnline;
	private String bankCode;
	
	private ObManageBeneficiaryBean beneficiaryBean;
	
	private List<ObPaymentBillerGroupBean> listPaymentBillerGroup;
	
	private List<ObBankDetailBean> listBankDetail;

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
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

	public boolean isInterbank() {
		return isInterbank;
	}

	public void setInterbank(boolean isInterbank) {
		this.isInterbank = isInterbank;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public ObManageBeneficiaryBean getBeneficiaryBean() {
		return beneficiaryBean;
	}

	public void setBeneficiaryBean(ObManageBeneficiaryBean beneficiaryBean) {
		this.beneficiaryBean = beneficiaryBean;
	}

	public List<ObPaymentBillerGroupBean> getListPaymentBillerGroup() {
		return listPaymentBillerGroup;
	}

	public void setListPaymentBillerGroup(List<ObPaymentBillerGroupBean> listPaymentBillerGroup) {
		this.listPaymentBillerGroup = listPaymentBillerGroup;
	}

	public List<ObBankDetailBean> getListBankDetail() {
		return listBankDetail;
	}

	public void setListBankDetail(List<ObBankDetailBean> listBankDetail) {
		this.listBankDetail = listBankDetail;
	}

	public String getBeneResidentStatusDesc() {
		return beneResidentStatusDesc;
	}

	public void setBeneResidentStatusDesc(String beneResidentStatusDesc) {
		this.beneResidentStatusDesc = beneResidentStatusDesc;
	}
	
}
