package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
@Table(name = "AO_General_Info_Cache")
public class AOGeneralInfoCache implements java.io.Serializable{

	private Integer rowId;
	private String deviceId;
	private String nik;
	private String phoneNumber;
	private String email;
	private String fullName;
	private String referralCode;
	private String referenceNumber;
	private String productType;
	private String subscriptionType;
	private String nationality;
	private String callAppointmentDate;
	private String lastEducation;
	private String numberOfDependants;
	private String cardDeliveryLocation;
	private String homeCurrentStatus;
	private String workType;
	private String workTypeDetail;
	private String businessName;
	private String businessIndustry;
	private String position;
	private String workSinceMonth;
	private String workSinceYear;
	private String sourceOfFund;
	private String purpose;
	private String monthlyIncome;
	private String monthlySpending;
	private String isUSGreenCard;
	private String isBirthInUS;
	private String isUSResidence;
	private String isStandingInstructionUS;
	private String isHoldMailInUS;
	private String isUsAttorney;
	private String accountType;
	private Date createdTime;
	private String resumePage;
	private String resumePageJson;
	private String status;
	private String isFinal;
	private String productKey;
	private String isCreditCardFlag;
	private String isDPN;
	private String listType;
	private String isBlacklist;
	private String identificationRisk;
	private String isHighRisk;
	private String postalCodeCheck;
	
	//GPN Params
	private String gpnCard;
	private String gpnBankName;
	private String gpnCardNumber;
	private String gpnAnotherCard;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_GENERAL_INFO_CACHE_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "device_id")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name = "nik")
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	@Column(name = "phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "fullName")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(name = "referralCode")
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	@Column(name = "referenceNumber")
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	@Column(name = "productType")
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	@Column(name = "subscriptionType")
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	@Column(name = "nationality")
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@Column(name = "callAppointmentDate")
	public String getCallAppointmentDate() {
		return callAppointmentDate;
	}
	public void setCallAppointmentDate(String callAppointmentDate) {
		this.callAppointmentDate = callAppointmentDate;
	}
	@Column(name = "lastEducation")
	public String getLastEducation() {
		return lastEducation;
	}
	public void setLastEducation(String lastEducation) {
		this.lastEducation = lastEducation;
	}
	@Column(name = "numberOfDependants")
	public String getNumberOfDependants() {
		return numberOfDependants;
	}
	public void setNumberOfDependants(String numberOfDependants) {
		this.numberOfDependants = numberOfDependants;
	}
	@Column(name = "cardDeliveryLocation")
	public String getCardDeliveryLocation() {
		return cardDeliveryLocation;
	}
	public void setCardDeliveryLocation(String cardDeliveryLocation) {
		this.cardDeliveryLocation = cardDeliveryLocation;
	}
	@Column(name = "homeCurrentStatus")
	public String getHomeCurrentStatus() {
		return homeCurrentStatus;
	}
	public void setHomeCurrentStatus(String homeCurrentStatus) {
		this.homeCurrentStatus = homeCurrentStatus;
	}
	@Column(name = "workType")
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	@Column(name = "businessName")
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	@Column(name = "businessIndustry")
	public String getBusinessIndustry() {
		return businessIndustry;
	}
	public void setBusinessIndustry(String businessIndustry) {
		this.businessIndustry = businessIndustry;
	}
	@Column(name = "position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Column(name = "workSinceMonth")
	public String getWorkSinceMonth() {
		return workSinceMonth;
	}
	public void setWorkSinceMonth(String workSinceMonth) {
		this.workSinceMonth = workSinceMonth;
	}
	@Column(name = "workSinceYear")
	public String getWorkSinceYear() {
		return workSinceYear;
	}
	public void setWorkSinceYear(String workSinceYear) {
		this.workSinceYear = workSinceYear;
	}
	@Column(name = "sourceOfFund")
	public String getSourceOfFund() {
		return sourceOfFund;
	}
	public void setSourceOfFund(String sourceOfFund) {
		this.sourceOfFund = sourceOfFund;
	}
	@Column(name = "purpose")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(name = "monthlyIncome")
	public String getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	@Column(name = "monthlySpending")
	public String getMonthlySpending() {
		return monthlySpending;
	}
	public void setMonthlySpending(String monthlySpending) {
		this.monthlySpending = monthlySpending;
	}
	@Column(name = "isUSGreenCard")
	public String getIsUSGreenCard() {
		return isUSGreenCard;
	}
	public void setIsUSGreenCard(String isUSGreenCard) {
		this.isUSGreenCard = isUSGreenCard;
	}
	@Column(name = "isBirthInUS")
	public String getIsBirthInUS() {
		return isBirthInUS;
	}
	public void setIsBirthInUS(String isBirthInUS) {
		this.isBirthInUS = isBirthInUS;
	}
	@Column(name = "isUSResidence")
	public String getIsUSResidence() {
		return isUSResidence;
	}
	public void setIsUSResidence(String isUSResidence) {
		this.isUSResidence = isUSResidence;
	}
	@Column(name = "isStandingInstructionUS")
	public String getIsStandingInstructionUS() {
		return isStandingInstructionUS;
	}
	public void setIsStandingInstructionUS(String isStandingInstructionUS) {
		this.isStandingInstructionUS = isStandingInstructionUS;
	}
	@Column(name = "isHoldMailInUS")
	public String getIsHoldMailInUS() {
		return isHoldMailInUS;
	}
	public void setIsHoldMailInUS(String isHoldMailInUS) {
		this.isHoldMailInUS = isHoldMailInUS;
	}
	@Column(name = "isUSAttorney")
	public String getIsUSAttorney() {
		return isUsAttorney;
	}
	public void setIsUSAttorney(String isUSAttorney) {
		this.isUsAttorney = isUSAttorney;
	}
	@Column(name = "accountType")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_Time")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name = "resume_page")
	public String getResumePage() {
		return resumePage;
	}
	public void setResumePage(String resumePage) {
		this.resumePage = resumePage;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "resume_page_json")
	public String getResumePageJson() {
		return resumePageJson;
	}
	public void setResumePageJson(String resumePageJson) {
		this.resumePageJson = resumePageJson;
	}
	@Column(name = "is_final")
	public String getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(String isFinal) {
		this.isFinal = isFinal;
	}
	@Column(name = "prod_key")
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	@Column(name = "is_creditcard_flag")
	public String getIsCreditCardFlag() {
		return isCreditCardFlag;
	}
	public void setIsCreditCardFlag(String isCreditCardFlag) {
		this.isCreditCardFlag = isCreditCardFlag;
	}
	@Column(name = "is_dpn")
	public String getIsDPN() {
		return isDPN;
	}
	public void setIsDPN(String isDPN) {
		this.isDPN = isDPN;
	}
	@Column(name = "list_type")
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	@Column(name = "is_blacklist")
	public String getIsBlacklist() {
		return isBlacklist;
	}
	public void setIsBlacklist(String isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	@Column(name = "identification_risk")
	public String getIdentificationRisk() {
		return identificationRisk;
	}
	public void setIdentificationRisk(String identificationRisk) {
		this.identificationRisk = identificationRisk;
	}
	@Column(name = "is_highrisk")
	public String getIsHighRisk() {
		return isHighRisk;
	}
	public void setIsHighRisk(String isHighRisk) {
		this.isHighRisk = isHighRisk;
	}
	
	@Column(name = "postalCodeCheck")
	public String getPostalCodeCheck() {
		return postalCodeCheck;
	}
	public void setPostalCodeCheck(String postalCodeCheck) {
		this.postalCodeCheck = postalCodeCheck;
	}
	
	@Column(name = "getGpnCard")
	public String getGpnCard() {
		return gpnCard;
	}
	public void setGpnCard(String gpnCard) {
		this.gpnCard = gpnCard;
	}
	
	@Column(name = "gpnBankName")
	public String getGpnBankName() {
		return gpnBankName;
	}
	public void setGpnBankName(String gpnBankName) {
		this.gpnBankName = gpnBankName;
	}
	
	@Column(name = "gpnCardNumber")
	public String getGpnCardNumber() {
		return gpnCardNumber;
	}
	public void setGpnCardNumber(String gpnCardNumber) {
		this.gpnCardNumber = gpnCardNumber;
	}
	
	@Column(name = "gpnGetAnotherCard")
	public String getGpnAnotherCard() {
		return gpnAnotherCard;
	}
	public void setGpnAnotherCard(String gpnAnotherCard) {
		this.gpnAnotherCard = gpnAnotherCard;
	}
	
	@Column(name = "work_type_detail")
	public String getWorkTypeDetail() {
		return workTypeDetail;
	}
	public void setWorkTypeDetail(String workTypeDetail) {
		this.workTypeDetail = workTypeDetail;
	}
	
}
