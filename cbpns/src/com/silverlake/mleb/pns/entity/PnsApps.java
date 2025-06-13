package com.silverlake.mleb.pns.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mleb_pns_apps")
public class PnsApps implements java.io.Serializable
{
	private String appId;
	private String appPass;
	private String status;
	private String androidApiKey;
	private String androidMsgTemplate;
	private String appleCert;
	private String appleIpadCert;
	private String applePass;
	private String appleIpadPass;
	private String appleUrl;

	
	@Column(name = "status", nullable = false, length = 10)
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	
	@Id
	@Column(name = "app_id")
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "android_api_key", nullable = false, length = 50)
	public String getAndroidApiKey() {
		return androidApiKey;
	}
	

	public void setAndroidApiKey(String androidApiKey) {
		this.androidApiKey = androidApiKey;
	}

	@Column(name = "apple_cert", nullable = false, length = 128)
	public String getAppleCert() {
		return appleCert;
	}

	public void setAppleCert(String appleCert) {
		this.appleCert = appleCert;
	}

	@Column(name = "apple_pass", nullable = false, length = 128)
	public String getApplePass() {
		return applePass;
	}

	public void setApplePass(String applePass) {
		this.applePass = applePass;
	}

	@Column(name = "apple_ipad_cert", nullable = false, length = 128)
	public String getAppleIpadCert() {
		return appleIpadCert;
	}

	public void setAppleIpadCert(String appleIpadCert) {
		this.appleIpadCert = appleIpadCert;
	}

	
	@Column(name = "apple_ipad_pass", nullable = false, length = 128)
	public String getAppleIpadPass() {
		return appleIpadPass;
	}

	public void setAppleIpadPass(String appleIpadPass) {
		this.appleIpadPass = appleIpadPass;
	}

	@Column(name = "android_msg_template", nullable = false, length = 128)
	public String getAndroidMsgTemplate() {
		return androidMsgTemplate;
	}

	public void setAndroidMsgTemplate(String androidMsgTemplate) {
		this.androidMsgTemplate = androidMsgTemplate;
	}

	
	@Column(name = "app_pass", nullable = false, length = 128)
	public String getAppPass() {
		return appPass;
	}

	public void setAppPass(String appPass) {
		this.appPass = appPass;
	}

	@Column(name = "apple_url", nullable = false, length = 50)
	public String getAppleUrl() {
		return appleUrl;
	}
	
	public void setAppleUrl(String appleUrl) {
		this.appleUrl = appleUrl;
	}
	
	
	
	
	
}
