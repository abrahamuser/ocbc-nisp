package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.token.Token;
import com.silverlake.mleb.mcb.module.vc.user.List_role;
import com.silverlake.mleb.mcb.module.vc.user.Password_rules;


public class ObAuthenticationResponse extends ObResponse implements Serializable
{
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	private static final long serialVersionUID = 5345896828611267021L;
	private String loginImageId;
	private byte[] loginImage;
	private String descriptions;
	private List<ObDeviceProfileBean> deviceData;
	private String lastLoginDateTime;
	private String lastLogoutDateTime;
	private String logoutDuration;
	private int sessionDuration;
	private int upfrontNoticeTime;
	private List<ObGeneralCodeBean> idType;
	private List<ObGeneralCodeBean> accType;
	private List<ObGeneralCodeBean> redirectURLList;
	private List<ObTransactionLimit> dailyTransactionLimit;
	private List<ObRegistrationImageBean> regImageBean;
	private String faqURL; 
	private String insuranceURL;
	private String promotionURL; 
	private String exponent;
	private String modulus;
	private String rootedMessage;
	private int resendTagCount;
	private int resendTagMax;
	private String resendTagDateTime;
	private String tokenReqId;
	private int resendTagInterval;
	private String pnsFlag;
	private String loginType;
	private String biometricStatus;
	private String biometricSupported;
	private String enrollNonce;
	private ObRedirectURLsBean redirectUrlBeans;
	
	private String moduleMaintenance; 
	
	private String hideMPosQPay;
	private String hideMPosMDir;
	private String hideMposAndroid;
	private String hideMposIos;
	
	private String androidMethodRootedFlags;
	private String androidRootedFlags;
	
	private String loginID;
	private String OrgId;
	private String orgName;
	private String usrName;
	private String userId;
	private String mobileNo;
	private String usr_state;
	private String nonce;
	private boolean isBinded;
	private String deviceLevel;
	private String bindingStatus;
	private String dateBind;
	private boolean isLimitMaxBinding;
	private String primaryAction;
	
	private String successCode;
	private String moduleTypeFlag;
	
	private String regexCode;
	private String aoAlphaRegexCode;
	private String aoAlphaNumericRegexCode;
	private String staffDirFlag;
	private String ocrFlag;
	
	private String aoNameRegex;
	
	private String fullName;
	private String displayName;
	private String randomNumber;
	private String xString;
	private String profileImg;
	private String tncContent;
	
	private List<ObMenuListBean> menu_access_list;
	private List<Password_rules> password_rules;
	private List<List_role> role_list;
	
	private String challengeCode;
	private String usr_status;
	private String activation_link;
	
	private List<ObAccessRestrictionBean> accessRestrictionList;
	private String biometricKey;
	private String initializationVector;
	
	private List<Token> list_token;
	private List<ObDeviceCifBean> pendingDeviceList;
	private String approvalCountdown;
	private String pbkString;
	private String exponentString;
	private String pinIB;
	private String primaryDeviceModel;
	private String pinBlockFlag;
	
	private String userSecStatus;
	private String remainingCoolOff;
	private String userSecCheck;
	
	private String theAppliNo;
	
	public String getBiometricKey() {
		return biometricKey;
	}
	public void setBiometricKey(String biometricKey) {
		this.biometricKey = biometricKey;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getAndroidMethodRootedFlags() {
		return androidMethodRootedFlags;
	}
	public void setAndroidMethodRootedFlags(String androidMethodRootedFlags) {
		this.androidMethodRootedFlags = androidMethodRootedFlags;
	}
	public String getAndroidRootedFlags() {
		return androidRootedFlags;
	}
	public void setAndroidRootedFlags(String androidRootedFlags) {
		this.androidRootedFlags = androidRootedFlags;
	}
	public String getEnrollNonce() {
		return enrollNonce;
	}
	public void setEnrollNonce(String enrollNonce) {
		this.enrollNonce = enrollNonce;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	
	public String getBiometricStatus() {
		return biometricStatus;
	}
	public void setBiometricStatus(String biometricStatus) {
		this.biometricStatus = biometricStatus;
	}
	public String getBiometricSupported() {
		return biometricSupported;
	}
	public void setBiometricSupported(String biometricSupported) {
		this.biometricSupported = biometricSupported;
	}
	public String getPnsFlag() {
		return pnsFlag;
	}
	public void setPnsFlag(String pnsFlag) {
		this.pnsFlag = pnsFlag;
	}
	
	
	/*private String hideMposWindows;
	private String hideMposBB;
	private String hideMposIpad;*/	
	
	
	
	public String getLoginImageId() {
		return loginImageId;
	}
	public void setLoginImageId(String loginImageId) {
		this.loginImageId = loginImageId;
	}
	public byte[] getLoginImage() {
		return loginImage;
	}
	public void setLoginImage(byte[] loginImage) {
		this.loginImage = loginImage;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public List<ObDeviceProfileBean> getDeviceData() {
		return deviceData;
	}
	public void setDeviceData(List<ObDeviceProfileBean> deviceData) {
		this.deviceData = deviceData;
	}
	public String getLastLoginDateTime() {
		return lastLoginDateTime;
	}
	public void setLastLoginDateTime(String lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}
	public String getLastLogoutDateTime() {
		return lastLogoutDateTime;
	}
	public void setLastLogoutDateTime(String lastLogoutDateTime) {
		this.lastLogoutDateTime = lastLogoutDateTime;
	}
	public String getLogoutDuration() {
		return logoutDuration;
	}
	public void setLogoutDuration(String logoutDuration) {
		this.logoutDuration = logoutDuration;
	}
	public int getSessionDuration() {
		return sessionDuration;
	}
	public void setSessionDuration(int sessionDuration) {
		this.sessionDuration = sessionDuration;
	}
	public int getUpfrontNoticeTime() {
		return upfrontNoticeTime;
	}
	public void setUpfrontNoticeTime(int upfrontNoticeTime) {
		this.upfrontNoticeTime = upfrontNoticeTime;
	}
	public List<ObGeneralCodeBean> getIdType() {
		return idType;
	}
	public void setIdType(List<ObGeneralCodeBean> idType) {
		this.idType = idType;
	}
	public List<ObGeneralCodeBean> getAccType() {
		return accType;
	}
	public void setAccType(List<ObGeneralCodeBean> accType) {
		this.accType = accType;
	}
	public List<ObTransactionLimit> getDailyTransactionLimit() {
		return dailyTransactionLimit;
	}
	public void setDailyTransactionLimit(
			List<ObTransactionLimit> dailyTransactionLimit) {
		this.dailyTransactionLimit = dailyTransactionLimit;
	}
	public List<ObRegistrationImageBean> getRegImageBean() {
		return regImageBean;
	}
	public void setRegImageBean(List<ObRegistrationImageBean> regImageBean) {
		this.regImageBean = regImageBean;
	}
	public String getFaqURL() {
		return faqURL;
	}
	public void setFaqURL(String faqURL) {
		this.faqURL = faqURL;
	}
	public String getInsuranceURL() {
		return insuranceURL;
	}
	public void setInsuranceURL(String insuranceURL) {
		this.insuranceURL = insuranceURL;
	}
	public String getExponent() {
		return exponent;
	}
	public void setExponent(String exponent) {
		this.exponent = exponent;
	}
	public String getModulus() {
		return modulus;
	}
	public void setModulus(String modulus) {
		this.modulus = modulus;
	}
	public String getRootedMessage() {
		return rootedMessage;
	}
	public void setRootedMessage(String rootedMessage) {
		this.rootedMessage = rootedMessage;
	}
	public ObRedirectURLsBean getRedirectUrlBeans() {
		return redirectUrlBeans;
	}
	public void setRedirectUrlBeans(ObRedirectURLsBean redirectUrlBeans) {
		this.redirectUrlBeans = redirectUrlBeans;
	}
	public String getPromotionURL() {
		return promotionURL;
	}
	public void setPromotionURL(String promotionURL) {
		this.promotionURL = promotionURL;
	}
	public int getResendTagCount() {
		return resendTagCount;
	}
	public void setResendTagCount(int resendTagCount) {
		this.resendTagCount = resendTagCount;
	}
	public String getHideMPosQPay() {
		return hideMPosQPay;
	}
	public void setHideMPosQPay(String hideMPosQPay) {
		this.hideMPosQPay = hideMPosQPay;
	}
	public String getHideMPosMDir() {
		return hideMPosMDir;
	}
	public void setHideMPosMDir(String hideMPosMDir) {
		this.hideMPosMDir = hideMPosMDir;
	}
	public String getHideMposAndroid() {
		return hideMposAndroid;
	}
	public void setHideMposAndroid(String hideMposAndroid) {
		this.hideMposAndroid = hideMposAndroid;
	}
	public String getHideMposIos() {
		return hideMposIos;
	}
	public void setHideMposIos(String hideMposIos) {
		this.hideMposIos = hideMposIos;
	}
/*	public String getHideMposWindows() {
		return hideMposWindows;
	}
	public void setHideMposWindows(String hideMposWindows) {
		this.hideMposWindows = hideMposWindows;
	}
	public String getHideMposBB() {
		return hideMposBB;
	}
	public void setHideMposBB(String hideMposBB) {
		this.hideMposBB = hideMposBB;
	}
	public String getHideMposIpad() {
		return hideMposIpad;
	}
	public void setHideMposIpad(String hideMposIpad) {
		this.hideMposIpad = hideMposIpad;
	}
*/
	public String getModuleMaintenance() {
		return moduleMaintenance;
	}
	public void setModuleMaintenance(String moduleMaintenance) {
		this.moduleMaintenance = moduleMaintenance;
	}
	public String getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}
	public String getModuleTypeFlag() {
		return moduleTypeFlag;
	}
	public void setModuleTypeFlag(String moduleTypeFlag) {
		this.moduleTypeFlag = moduleTypeFlag;
	}
	public String getRegexCode() {
		return regexCode;
	}
	public void setRegexCode(String regexCode) {
		this.regexCode = regexCode;
	}
	public String getStaffDirFlag() {
		return staffDirFlag;
	}
	public void setStaffDirFlag(String staffDirFlag) {
		this.staffDirFlag = staffDirFlag;
	}
	public String getAoAlphaRegexCode() {
		return aoAlphaRegexCode;
	}
	public void setAoAlphaRegexCode(String aoAlphaRegexCode) {
		this.aoAlphaRegexCode = aoAlphaRegexCode;
	}
	public String getAoAlphaNumericRegexCode() {
		return aoAlphaNumericRegexCode;
	}
	public void setAoAlphaNumericRegexCode(String aoAlphaNumericRegexCode) {
		this.aoAlphaNumericRegexCode = aoAlphaNumericRegexCode;
	}
	public String getOcrFlag() {
		return ocrFlag;
	}
	public void setOcrFlag(String ocrFlag) {
		this.ocrFlag = ocrFlag;
	}
//	// P3OCBCSIT-40
//	public String getPhoneNumberRegex() {
//		return phoneNumberRegex;
//	}
//	public void setPhoneNumberRegex(String phoneNumberRegex) {
//		this.phoneNumberRegex = phoneNumberRegex;
//	}
	public String getAoNameRegex() {
		return aoNameRegex;
	}
	public void setAoNameRegex(String aoNameRegex) {
		this.aoNameRegex = aoNameRegex;
	}
	public List<ObMenuListBean> getMenu_access_list() {
		return menu_access_list;
	}
	public void setMenu_access_list(List<ObMenuListBean> menu_access_list) {
		this.menu_access_list = menu_access_list;
	}
	public String getOrgId() {
		return OrgId;
	}
	public void setOrgId(String orgId) {
		OrgId = orgId;
	}
	public boolean isBinded() {
		return isBinded;
	}
	public void setBinded(boolean isBinded) {
		this.isBinded = isBinded;
	}
	public String getDeviceLevel() {
		return deviceLevel;
	}
	public void setDeviceLevel(String deviceLevel) {
		this.deviceLevel = deviceLevel;
	}
	public String getBindingStatus() {
		return bindingStatus;
	}
	public void setBindingStatus(String bindingStatus) {
		this.bindingStatus = bindingStatus;
	}
	public String getDateBind() {
		return dateBind;
	}
	public void setDateBind(String dateBind) {
		this.dateBind = dateBind;
	}
	public boolean isLimitMaxBinding() {
		return isLimitMaxBinding;
	}
	public void setLimitMaxBinding(boolean isLimitMaxBinding) {
		this.isLimitMaxBinding = isLimitMaxBinding;
	}
	public String getPrimaryAction() {
		return primaryAction;
	}
	public void setPrimaryAction(String primaryAction) {
		this.primaryAction = primaryAction;
	}
	public List<ObGeneralCodeBean> getRedirectURLList() {
		return redirectURLList;
	}
	public void setRedirectURLList(List<ObGeneralCodeBean> redirectURLList) {
		this.redirectURLList = redirectURLList;
	}
	public int getResendTagMax() {
		return resendTagMax;
	}
	public void setResendTagMax(int resendTagMax) {
		this.resendTagMax = resendTagMax;
	}
	 
	public int getResendTagInterval() {
		return resendTagInterval;
	}
	public void setResendTagInterval(int resendTagInterval) {
		this.resendTagInterval = resendTagInterval;
	}
	public String getResendTagDateTime() {
		return resendTagDateTime;
	}
	public void setResendTagDateTime(String resendTagDateTime) {
		this.resendTagDateTime = resendTagDateTime;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getUsr_state() {
		return usr_state;
	}
	public void setUsr_state(String usr_state) {
		this.usr_state = usr_state;
	}
	public String getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(String randomNumber) {
		this.randomNumber = randomNumber;
	}
	public String getxString() {
		return xString;
	}
	public void setxString(String xString) {
		this.xString = xString;
	}
	public String getTokenReqId() {
		return tokenReqId;
	}
	public void setTokenReqId(String tokenReqId) {
		this.tokenReqId = tokenReqId;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTncContent() {
		return tncContent;
	}
	public void setTncContent(String tncContent) {
		this.tncContent = tncContent;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<Password_rules> getPassword_rules() {
		return password_rules;
	}
	public void setPassword_rules(List<Password_rules> password_rules) {
		this.password_rules = password_rules;
	}
	public String getChallengeCode() {
		return challengeCode;
	}
	public void setChallengeCode(String challengeCode) {
		this.challengeCode = challengeCode;
	}
	public List<List_role> getRole_list() {
		return role_list;
	}
	public void setRole_list(List<List_role> role_list) {
		this.role_list = role_list;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getUsr_status() {
		return usr_status;
	}
	public void setUsr_status(String usr_status) {
		this.usr_status = usr_status;
	}
	public String getActivation_link() {
		return activation_link;
	}
	public void setActivation_link(String activation_link) {
		this.activation_link = activation_link;
	}
	public List<ObAccessRestrictionBean> getAccessRestrictionList() {
		return accessRestrictionList;
	}
	public void setAccessRestrictionList(List<ObAccessRestrictionBean> accessRestrictionList) {
		this.accessRestrictionList = accessRestrictionList;
	}
	public String getInitializationVector() {
		return initializationVector;
	}
	public void setInitializationVector(String initializationVector) {
		this.initializationVector = initializationVector;
	}
	public List<Token> getList_token() {
		return list_token;
	}
	public void setList_token(List<Token> list_token) {
		this.list_token = list_token;
	}
	public List<ObDeviceCifBean> getPendingDeviceList() {
		return pendingDeviceList;
	}
	public void setPendingDeviceList(List<ObDeviceCifBean> pendingDeviceList) {
		this.pendingDeviceList = pendingDeviceList;
	}
	public String getApprovalCountdown() {
		return approvalCountdown;
	}
	public void setApprovalCountdown(String approvalCountdown) {
		this.approvalCountdown = approvalCountdown;
	}
	public String getPbkString() {
		return pbkString;
	}
	public void setPbkString(String pbkString) {
		this.pbkString = pbkString;
	}
	public String getExponentString() {
		return exponentString;
	}
	public void setExponentString(String exponentString) {
		this.exponentString = exponentString;
	}
	public String getPinIB() {
		return pinIB;
	}
	public void setPinIB(String pinIB) {
		this.pinIB = pinIB;
	}
	public String getPrimaryDeviceModel() {
		return primaryDeviceModel;
	}
	public void setPrimaryDeviceModel(String primaryDeviceModel) {
		this.primaryDeviceModel = primaryDeviceModel;
	}
	public String getPinBlockFlag() {
		return pinBlockFlag;
	}
	public void setPinBlockFlag(String pinBlockFlag) {
		this.pinBlockFlag = pinBlockFlag;
	}
	public String getUserSecStatus() {
		return userSecStatus;
	}
	public void setUserSecStatus(String userSecStatus) {
		this.userSecStatus = userSecStatus;
	}
	public String getUserSecCheck() {
		return userSecCheck;
	}
	public void setUserSecCheck(String userSecCheck) {
		this.userSecCheck = userSecCheck;
	}
	public String getRemainingCoolOff() {
		return remainingCoolOff;
	}
	public void setRemainingCoolOff(String remainingCoolOff) {
		this.remainingCoolOff = remainingCoolOff;
	}
	public String getTheAppliNo() {
		return theAppliNo;
	}
	public void setTheAppliNo(String theAppliNo) {
		this.theAppliNo = theAppliNo;
	}
}

