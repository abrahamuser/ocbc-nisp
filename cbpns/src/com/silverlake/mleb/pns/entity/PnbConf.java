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
//@Table(name = "mleb_pnb_conf")
public class PnbConf implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private Integer androidMaxTokens;
	private Integer iosMaxTokens;
	private Integer ipadMaxTokens;
	private Date broadcastTime;
	private Integer msgLenght;
	private Integer maxRetryNum;
	private String status;
	private String isPushEnabled;
	private Integer dailyLimit;
	private Integer broadcastHours;
	private String specialChar;
	private Integer pnbBatchhours;
	
	
	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@Column(name = "row_id", unique = true, nullable = false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "android_max_tokens", nullable = false)
	public Integer getAndroidMaxTokens() {
		return androidMaxTokens;
	}
	public void setAndroidMaxTokens(Integer androidMaxTokens) {
		this.androidMaxTokens = androidMaxTokens;
	}
	
	@Column(name = "ios_max_tokens", nullable = false)
	public Integer getIosMaxTokens() {
		return iosMaxTokens;
	}
	public void setIosMaxTokens(Integer iosMaxTokens) {
		this.iosMaxTokens = iosMaxTokens;
	}
	
	@Column(name = "ipad_max_tokens", nullable = false)
	public Integer getIpadMaxTokens() {
		return ipadMaxTokens;
	}
	public void setIpadMaxTokens(Integer ipadMaxTokens) {
		this.ipadMaxTokens = ipadMaxTokens;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "broadcast_time", nullable = false, length = 19)
	public Date getBroadcastTime() {
		return broadcastTime;
	}
	public void setBroadcastTime(Date broadcastTime) {
		this.broadcastTime = broadcastTime;
	}
	
	@Column(name = "msg_lenght", nullable = false)
	public Integer getMsgLenght() {
		return msgLenght;
	}
	public void setMsgLenght(Integer msgLenght) {
		this.msgLenght = msgLenght;
	}
	
	@Column(name = "max_retry_num", nullable = false)
	public Integer getMaxRetryNum() {
		return maxRetryNum;
	}
	public void setMaxRetryNum(Integer maxRetryNum) {
		this.maxRetryNum = maxRetryNum;
	}
	
	@Column(name = "status", nullable = false, length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "is_push_enabled", nullable = false, length = 5)
	public String getIsPushEnabled() {
		return isPushEnabled;
	}
	public void setIsPushEnabled(String isPushEnabled) {
		this.isPushEnabled = isPushEnabled;
	}
	
	@Column(name = "daily_limit", nullable = false)
	public Integer getDailyLimit() {
		return dailyLimit;
	}
	public void setDailyLimit(Integer dailyLimit) {
		this.dailyLimit = dailyLimit;
	}
	
	@Column(name = "broadcast_hours", nullable = false)
	public Integer getBroadcastHours() {
		return broadcastHours;
	}
	public void setBroadcastHours(Integer broadcastHours) {
		this.broadcastHours = broadcastHours;
	}
	
	@Column(name = "special_char",  nullable = false, length = 100)
	public String getSpecialChar() {
		return specialChar;
	}
	public void setSpecialChar(String specialChar) {
		this.specialChar = specialChar;
	}
	
	@Column(name = "pnb_batch_hours", nullable = false)
	public Integer getPnbBatchhours() {
		return pnbBatchhours;
	}
	public void setPnbBatchhours(Integer pnbBatchhours) {
		this.pnbBatchhours = pnbBatchhours;
	}
	
	
}
