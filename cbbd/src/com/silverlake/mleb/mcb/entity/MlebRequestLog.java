package com.silverlake.mleb.mcb.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mleb_request_log", schema="mleb_core")
public class MlebRequestLog implements java.io.Serializable {

	private String transactionId;
	private String serviceId;
	private String bdDispatchUrl;
	private String bdVmUrl;
	private String username;
	private String msisdn;
	private String userAgent;
	private String deviceId;
	private String locationCoordinates;
	private String sourceIp;
	private String commcIp;
	private Date dateTime;
	private String timeStamp;
	
	private Set<MlebResponseLog> mlebResponseLogs = new HashSet<MlebResponseLog>(
			0);

	public MlebRequestLog() {
	}

	public MlebRequestLog(String transactionId, Date dateTime) {
		this.transactionId = transactionId;
		this.dateTime = dateTime;
	}

	public MlebRequestLog(String transactionId, String serviceId,
			String bdDispatchUrl, String bdVmUrl, String username,
			String msisdn, String userAgent, String deviceId,
			String locationCoordinates, String sourceIp, String commcIp,
			Date dateTime, String timeStamp, Set<MlebResponseLog> mlebResponseLogs) {
		this.transactionId = transactionId;
		this.serviceId = serviceId;
		this.bdDispatchUrl = bdDispatchUrl;
		this.bdVmUrl = bdVmUrl;
		this.username = username;
		this.msisdn = msisdn;
		this.userAgent = userAgent;
		this.deviceId = deviceId;
		this.locationCoordinates = locationCoordinates;
		this.sourceIp = sourceIp;
		this.commcIp = commcIp;
		this.dateTime = dateTime;
		this.timeStamp = timeStamp;
		this.mlebResponseLogs = mlebResponseLogs;
	}

	@Id
	@Column(name = "transaction_id", nullable = false, length = 100)
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "service_id")
	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name = "bd_dispatch_url")
	public String getBdDispatchUrl() {
		return this.bdDispatchUrl;
	}

	public void setBdDispatchUrl(String bdDispatchUrl) {
		this.bdDispatchUrl = bdDispatchUrl;
	}

	@Column(name = "bd_vm_url")
	public String getBdVmUrl() {
		return this.bdVmUrl;
	}

	public void setBdVmUrl(String bdVmUrl) {
		this.bdVmUrl = bdVmUrl;
	}

	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "msisdn", length = 100)
	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	//@Column(name = "user_agent", length = 65535)
	@Column(name = "user_agent", length = 512)
	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	//@Column(name = "device_id", length = 65535)
	@Column(name = "device_id", length = 512)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "location_coordinates", length = 512)
	public String getLocationCoordinates() {
		return this.locationCoordinates;
	}

	public void setLocationCoordinates(String locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
	}

	@Column(name = "source_ip")
	public String getSourceIp() {
		return this.sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	@Column(name = "commc_ip")
	public String getCommcIp() {
		return this.commcIp;
	}

	public void setCommcIp(String commcIp) {
		this.commcIp = commcIp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", nullable = false, length = 23)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	
	@Column(name = "timestamp")
	public String getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mlebRequestLog", cascade = {CascadeType.REMOVE})
	public Set<MlebResponseLog> getMlebResponseLogs() {
		return this.mlebResponseLogs;
	}

	public void setMlebResponseLogs(
			Set<MlebResponseLog> mlebResponseLogs) {
		this.mlebResponseLogs = mlebResponseLogs;
	}

}
