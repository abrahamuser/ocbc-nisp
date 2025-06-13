package comx.silverlake.mleb.mcb.entity;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Table(name = "NTI_Cache")
public class NTICache implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer rowId;
	private String userId;
	private String status;
	private String pageCache;
	private String pageCacheJson;
	private byte[] questionsData;
	private byte[] riskProfileData;
	private String accountNo;
	private String imageUuidListString;
	private String actionCode;
	private String email;
	private String phoneNumber;
	private String marriageStatus;
	private String spouseName;
	private String investmentPurpose;
	private String sourceOfFund;
	private Boolean checkNotHaveNPWP;
	private String npwpNumber;
	private Date createdTime;
	private String addressSeq;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "NTI_CACHE_ID")

	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Column(name = "questions_data")
	public byte[] getQuestionsData() {
		return questionsData;
	}

	public void setQuestionsData(byte[] questionsData) {
		this.questionsData = questionsData;
	}

	@Column(name = "risk_profile_data")
	public byte[] getRiskProfileData() {
		return riskProfileData;
	}

	public void setRiskProfileData(byte[] riskProfileData) {
		this.riskProfileData = riskProfileData;
	}

	@Column(name = "account_no")
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "image_uuid_list_string")
	public String getImageUuidListString() {
		return imageUuidListString;
	}

	public void setImageUuidListString(String imageUuidListString) {
		this.imageUuidListString = imageUuidListString;
	}

	@Column(name = "action_code")
	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "marriage_status")
	public String getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	@Column(name = "spouse_name")
	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	@Column(name = "investment_purpose")
	public String getInvestmentPurpose() {
		return investmentPurpose;
	}

	public void setInvestmentPurpose(String investmentPurpose) {
		this.investmentPurpose = investmentPurpose;
	}

	@Column(name = "source_of_fund")
	public String getSourceOfFund() {
		return sourceOfFund;
	}

	public void setSourceOfFund(String sourceOfFund) {
		this.sourceOfFund = sourceOfFund;
	}

	@Column(name = "check_not_have_npwp")
	public Boolean getCheckNotHaveNPWP() {
		return checkNotHaveNPWP;
	}

	public void setCheckNotHaveNPWP(Boolean checkNotHaveNPWP) {
		this.checkNotHaveNPWP = checkNotHaveNPWP;
	}

	@Column(name = "npwp_number")
	public String getNpwpNumber() {
		return npwpNumber;
	}

	public void setNpwpNumber(String npwpNumber) {
		this.npwpNumber = npwpNumber;
	}

	@Column(name = "create_timestamp")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "address_seq")
	public String getAddressSeq() {
		return addressSeq;
	}

	public void setAddressSeq(String addressSeq) {
		this.addressSeq = addressSeq;
	}
}
