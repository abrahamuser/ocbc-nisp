 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObAuthenticationRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String authorizationCode;//response_code mapped to here for token/cr/verify
	protected String password;
	protected String confirmPassword;
	protected String imageID;
	protected String untagDeviceID;
	protected String cardNumber;
	protected String atmPin;
	protected String cardPin;
	protected String idType;
	protected String idNumber;
	protected String accountType;
	protected String accountNumber;
	protected String temporaryID;
	protected int regType;
	protected String nonce;
	protected int acceptTNC;
	private int resendTagCount;
	private String tokenReqId;//request_id mapped to here for token/cr/verify
	private String resendTagLastDate;
	protected String encryptedBiometricPasscode;
	private String deactivateBiometric;
	protected String enrollNonce;
	private int pnsFlag;
	private String loginType;
	private List<ObGeneralCodeBean> idData;
	private String randomNumber;
	private String pString;
	private String cString;
 
	private String pStringOld;
	private String cStringOld;
	
	private String deviceId;
	private String tncAction;
	
	private String email;
	private String phone;
	private String primaryActionFlag;
	private String approvalUnbindFlag;
	private String passwordDataBlock;
	private String ip;
	
	public String getEnrollNonce() {
		return enrollNonce;
	}
	public void setEnrollNonce(String enrollNonce) {
		this.enrollNonce = enrollNonce;
	}
	public String getDeactivateBiometric() {
		return deactivateBiometric;
	}
	public void setDeactivateBiometric(String deactivateBiometric) {
		this.deactivateBiometric = deactivateBiometric;
	}
	
	
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public int getPnsFlag() {
		return pnsFlag;
	}
	public void setPnsFlag(int pnsFlag) {
		this.pnsFlag = pnsFlag;
	}
	public String getEncryptedBiometricPasscode() {
		return encryptedBiometricPasscode;
	}
	public void setEncryptedBiometricPasscode(String encryptedBiometricPasscode) {
		this.encryptedBiometricPasscode = encryptedBiometricPasscode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getUntagDeviceID() {
		return untagDeviceID;
	}
	public void setUntagDeviceID(String untagDeviceID) {
		this.untagDeviceID = untagDeviceID;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getAtmPin() {
		return atmPin;
	}
	public void setAtmPin(String atmPin) {
		this.atmPin = atmPin;
	}
	public String getCardPin() {
		return cardPin;
	}
	public void setCardPin(String cardPin) {
		this.cardPin = cardPin;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTemporaryID() {
		return temporaryID;
	}
	public void setTemporaryID(String temporaryID) {
		this.temporaryID = temporaryID;
	}
	public int getRegType() {
		return regType;
	}
	public void setRegType(int regType) {
		this.regType = regType;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getImageID() {
		return imageID;
	}
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public int getAcceptTNC() {
		return acceptTNC;
	}
	public void setAcceptTNC(int acceptTNC) {
		this.acceptTNC = acceptTNC;
	}
	public int getResendTagCount() {
		return resendTagCount;
	}
	public void setResendTagCount(int resendTagCount) {
		this.resendTagCount = resendTagCount;
	}
	public List<ObGeneralCodeBean> getIdData() {
		return idData;
	}
	public void setIdData(List<ObGeneralCodeBean> idData) {
		this.idData = idData;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getResendTagLastDate() {
		return resendTagLastDate;
	}
	public void setResendTagLastDate(String resendTagLastDate) {
		this.resendTagLastDate = resendTagLastDate;
	}
	
	
	public String getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(String randomNumber) {
		this.randomNumber = randomNumber;
	}
	public String getpString() {
		return pString;
	}
	public void setpString(String pString) {
		this.pString = pString;
	}
	public String getcString() {
		return cString;
	}
	public void setcString(String cString) {
		this.cString = cString;
	}
	public String getpStringOld() {
		return pStringOld;
	}
	public void setpStringOld(String pStringOld) {
		this.pStringOld = pStringOld;
	}
	public String getcStringOld() {
		return cStringOld;
	}
	public void setcStringOld(String cStringOld) {
		this.cStringOld = cStringOld;
	}
	public String getTokenReqId() {
		return tokenReqId;
	}
	public void setTokenReqId(String tokenReqId) {
		this.tokenReqId = tokenReqId;
	}
	public String getTncAction() {
		return tncAction;
	}
	public void setTncAction(String tncAction) {
		this.tncAction = tncAction;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPrimaryActionFlag() {
		return primaryActionFlag;
	}
	public void setPrimaryActionFlag(String primaryActionFlag) {
		this.primaryActionFlag = primaryActionFlag;
	}
	public String getApprovalUnbindFlag() {
		return approvalUnbindFlag;
	}
	public void setApprovalUnbindFlag(String approvalUnbindFlag) {
		this.approvalUnbindFlag = approvalUnbindFlag;
	}
	public String getPasswordDataBlock() {
		return passwordDataBlock;
	}
	public void setPasswordDataBlock(String passwordDataBlock) {
		this.passwordDataBlock = passwordDataBlock;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	
	
}

