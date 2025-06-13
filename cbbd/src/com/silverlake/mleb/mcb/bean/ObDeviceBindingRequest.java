 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObDeviceBindingRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String authorizationCode;
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
	protected String encryptedBiometricPasscode;
	private String deactivateBiometric;
	protected String enrollNonce;
	private int pnsFlag;
	private String cif;
	private String devId;
	private String devModel;
	private String devType;
	private String devOs;
	private String devIsRooted;
	private String loginType;
	private List<ObGeneralCodeBean> idData;
	
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
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getDevModel() {
		return devModel;
	}
	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	public String getDevOs() {
		return devOs;
	}
	public void setDevOs(String devOs) {
		this.devOs = devOs;
	}
	public String getDevIsRooted() {
		return devIsRooted;
	}
	public void setDevIsRooted(String devIsRooted) {
		this.devIsRooted = devIsRooted;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	

	
	
	
}

