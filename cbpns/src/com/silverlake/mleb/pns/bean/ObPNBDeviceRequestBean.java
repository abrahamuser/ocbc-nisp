package com.silverlake.mleb.pns.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.silverlake.hlb.mib.bean.ObRequest;

public class ObPNBDeviceRequestBean extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int rowId;
	
	private String cif;
	
	private String deviceId;
	
	private String deviceModel;
	
	private String deviceType;
	
	private String deviceToken;
	
	private int retryCount;
	
	private String multicastId;
	 
	private String errorCode;
	 
	private String storeMessageId;
	
	private String errorMsg;
	
	private String trxStatus;
	
	private int responseRowId;
	
	private Date deviceTaggedDate;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public String getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(String multicastId) {
		this.multicastId = multicastId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStoreMessageId() {
		return storeMessageId;
	}

	public void setStoreMessageId(String storeMessageId) {
		this.storeMessageId = storeMessageId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getTrxStatus() {
		return trxStatus;
	}

	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}

	public int getResponseRowId() {
		return responseRowId;
	}

	public void setResponseRowId(int responseRowId) {
		this.responseRowId = responseRowId;
	}

	public Date getDeviceTaggedDate() {
		return deviceTaggedDate;
	}

	public void setDeviceTaggedDate(Date deviceTaggedDate) {
		this.deviceTaggedDate = deviceTaggedDate;
	}
	
	
}
