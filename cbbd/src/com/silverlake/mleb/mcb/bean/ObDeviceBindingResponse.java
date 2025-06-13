package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObDeviceBindingResponse extends ObResponse implements Serializable
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
	private List<ObDeviceCifBean> deviceListing;
	private String allowDevBinding;
	private String maxDevBinding;
	private String lastLoginDateTime;
	private String lastLogoutDateTime;
	private String logoutDuration;
	private String faqURL; 
	private String insuranceURL;
	private String promotionURL; 
	private String exponent;
	private String modulus;
	private String rootedMessage;
	private String pnsFlag;
	private String loginType;
	private String biometricStatus;
	private String biometricSupported;
	private String enrollNonce;

	
	private String moduleMaintenance; 
	
	private String hideMPosQPay;
	private String hideMPosMDir;
	private String hideMposAndroid;
	private String hideMposIos;
	
	private String androidMethodRootedFlags;
	private String androidRootedFlags;
	
	private String loginID;
	private String nonce;
	
	private String successCode;
	private String moduleTypeFlag;
	
	
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
	
	public List<ObDeviceCifBean> getDeviceListing() {
		return deviceListing;
	}
	public void setDeviceListing(List<ObDeviceCifBean> deviceListing) {
		this.deviceListing = deviceListing;
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
	
	public String getPromotionURL() {
		return promotionURL;
	}
	public void setPromotionURL(String promotionURL) {
		this.promotionURL = promotionURL;
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
	public String getAllowDevBinding() {
		return allowDevBinding;
	}
	public void setAllowDevBinding(String allowDevBinding) {
		this.allowDevBinding = allowDevBinding;
	}
	public String getMaxDevBinding() {
		return maxDevBinding;
	}
	public void setMaxDevBinding(String maxDevBinding) {
		this.maxDevBinding = maxDevBinding;
	}
	
	
	
}

