package comx.silverlake.mleb.mcb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "PAL_General_Info_Cache")
public class PALGeneralInfoCache implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer rowId;
	private String userId;
	private String accountNumber;
	private String accountType;
	private String businessIndustry;
	private String businessName;
	private String email;
	private String employeeId;
	private String fullName;
	private Boolean insuranceFlag;
	private String mscCode;
	private String nik;
	private String phoneNumber;
	private String productType;
	private String referenceNumber;
	private String referralCode;
	private String imageUuidListString;
	private String status;
	private String pageCache;
	private String pageCacheJson;
	private Date createdTime;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "PAL_GENERAL_INFO_CACHE_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "account_number")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Column(name = "account_type")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Column(name = "business_industry")
	public String getBusinessIndustry() {
		return businessIndustry;
	}
	public void setBusinessIndustry(String businessIndustry) {
		this.businessIndustry = businessIndustry;
	}
	
	@Column(name = "business_name")
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "employee_id")
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Column(name = "insurance_flag")
	public Boolean getInsuranceFlag() {
		return insuranceFlag;
	}
	public void setInsuranceFlag(Boolean insuranceFlag) {
		this.insuranceFlag = insuranceFlag;
	}
	
	@Column(name = "msc_code")
	public String getMscCode() {
		return mscCode;
	}
	public void setMscCode(String mscCode) {
		this.mscCode = mscCode;
	}
	
	@Column(name = "nik")
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "product_type")
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Column(name = "reference_number")
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	@Column(name = "referral_code")
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "image_uuid_list_string")
	public String getImageUuidListString() {
		return imageUuidListString;
	}
	public void setImageUuidListString(String imageUuidListString) {
		this.imageUuidListString = imageUuidListString;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "page_cache")
	public String getPageCache() {
		return pageCache;
	}
	public void setPageCache(String pageCache) {
		this.pageCache = pageCache;
	}
	
	@Column(name = "page_cache_json")
	public String getPageCacheJson() {
		return pageCacheJson;
	}
	public void setPageCacheJson(String pageCacheJson) {
		this.pageCacheJson = pageCacheJson;
	}
	
	@Column(name = "create_timestamp")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
