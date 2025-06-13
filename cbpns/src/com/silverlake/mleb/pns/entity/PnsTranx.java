package com.silverlake.mleb.pns.entity;

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

@Entity
@Table(name = "mleb_pns_tranx")
public class PnsTranx implements java.io.Serializable
{
	private Integer rowId;
	
	private String notificationType;
	private String appId;
	private String channel;
	private String refId;
	private String deviceId;
	
	private String deviceToken;
	
	private String pns_template;
	
	private String messageHeader;
	
	private String messageContent;
	
	private Date requestDateTime;
	
	private Date responseDateTime;
	
	private String status;
	
	private Integer resendCount;
	
	
	private String errorCode;
	
	private String storeMessageId;
	
	private String multicastId;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "PnsTranx_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}



	@Column(name = "device_id",  nullable = true, length = 100)
	public String getDeviceId() {
		return deviceId;
	}

	

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "device_token",  nullable = false, length = 200)
	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Column(name = "pns_template", length = 40)
	public String getPns_template() {
		return pns_template;
	}

	public void setPns_template(String pns_template) {
		this.pns_template = pns_template;
	}

	@Column(name = "message_header", length = 100)
	public String getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}

	@Column(name = "message_content",  length = 500)
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "request_date_time", length = 19)
	public Date getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(Date requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "response_date_time", length = 19)
	public Date getResponseDateTime() {
		return responseDateTime;
	}

	public void setResponseDateTime(Date responseDateTime) {
		this.responseDateTime = responseDateTime;
	}

	@Column(name = "status", length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "resend_count")
	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}



	@Column(name = "error_code", length = 100)
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Column(name = "multicast_id", length = 200)
	public String getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(String multicastId) {
		this.multicastId = multicastId;
	}

	@Column(name = "store_message_id", length = 200)
	public String getStoreMessageId() {
		return storeMessageId;
	}

	public void setStoreMessageId(String storeMessageId) {
		this.storeMessageId = storeMessageId;
	}
	
	
	@Column(name = "notification_type", length = 20)
	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	@Column(name = "channel", length = 20)
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "ref_id", length = 128)
	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	@Column(name = "app_id", length = 128)
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	
	
}
