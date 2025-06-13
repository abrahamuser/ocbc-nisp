package com.silverlake.mleb.mcb.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mleb_response_log", schema="mleb_core")
public class MlebResponseLog implements java.io.Serializable {

	private Integer rowId;
	private MlebRequestLog mlebRequestLog;
	private Date dateTime;
	private String responseCode;
	private String responseMsg;
	private String timeStamp;

	public MlebResponseLog() {
	}

	public MlebResponseLog(MlebRequestLog mlebRequestLog,
			Date dateTime, String responseCode, String responseMsg, String timeStamp) {
		this.mlebRequestLog = mlebRequestLog;
		this.dateTime = dateTime;
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		this.timeStamp = timeStamp;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "MlebResponseLog_id")
	@Column(name = "row_id", nullable = false)
	public Integer getRowId() {
		return this.rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_id", nullable = false)
	public MlebRequestLog getMlebRequestLog() {
		return this.mlebRequestLog;
	}

	public void setMlebRequestLog(MlebRequestLog mlebRequestLog) {
		this.mlebRequestLog = mlebRequestLog;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", nullable = false, length = 19)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "response_code", nullable = false)
	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Column(name = "response_msg", nullable = false , length = 512)
	public String getResponseMsg() {
		return this.responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	
	@Column(name = "timestamp")
	public String getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
