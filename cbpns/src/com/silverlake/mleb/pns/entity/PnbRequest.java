package com.silverlake.mleb.pns.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Table(name = "mleb_pnb_request")
public class PnbRequest implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private String cif;
	private String deviceId;
	private String deviceModel;
	private String deviceType;
	private String deviceToken;
	private String msgCode;
	private Date notifSendDate;
	private String status;
	private Integer retryCount;
	private Date deviceTaggedDate;
	private Integer responseRowId;
	
	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@Column(name = "row_id", unique = true, nullable = false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "cif", nullable = false, length = 20)
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	
	@Column(name = "device_id", unique = true, nullable = false, length = 100)
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(name = "device_model", nullable = true, length = 50)
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	
	@Column(name = "device_type", nullable = true, length = 50)
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	@Column(name = "device_token", nullable = true, length = 400)
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	@Column(name = "msg_code", nullable = false, length = 20)
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "notif_send_date", nullable = false, length = 19)
	public Date getNotifSendDate() {
		return notifSendDate;
	}
	public void setNotifSendDate(Date notifSendDate) {
		this.notifSendDate = notifSendDate;
	}
	
	@Column(name = "status", length = 20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "retry_count", nullable = false)
	public Integer getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "device_tagged_date", nullable = false, length = 19)
	public Date getDeviceTaggedDate() {
		return deviceTaggedDate;
	}
	public void setDeviceTaggedDate(Date deviceTaggedDate) {
		this.deviceTaggedDate = deviceTaggedDate;
	}
	
	@Column(name = "response_row_id", nullable = true)
	public Integer getResponseRowId() {
		return responseRowId;
	}
	public void setResponseRowId(Integer responseRowId) {
		this.responseRowId = responseRowId;
	}
	
	
}
