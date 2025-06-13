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
//@Table(name = "mleb_pnb_promotion")
public class PnbPromotion implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer rowId;//Auto-Generated Unique ID
	private String msgCode;//Unique Message ID
	private String notifMsg;// Push Notification Message
	private String urlLink;// Push Notification Redirect URL
	private Date broadcastDt;;//Push Notification Broadcast Date
	private String createdBy;//Created By
	private Date createdDt;//Created Date
	private String modifyBy;//Modified By
	private Date modifyDt;// Modified Date
	private String status;//COMPLETED | PENDING | CANCELLED | COMPLETED/RETRY(only when flag=R) | INPROGRESS
	private String flag;//Flag to indicate InProgress(P) / Retry(R) 
	private Integer retryCount; //Number of Retries
	private Date completedDate;//Promotion Send Out complete date.
	private String insertReqStatus;
	
	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@Column(name = "row_id", unique = true, nullable = false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "msg_code", unique = true, nullable = false, length = 20)
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
	@Column(name = "notif_msg", nullable = false, length = 320)
	public String getNotifMsg() {
		return notifMsg;
	}
	public void setNotifMsg(String notifMsg) {
		this.notifMsg = notifMsg;
	}
	
	@Column(name = "url_link", length = 200)
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "broadcast_date", nullable = false, length = 19)
    public Date getBroadcastDt() {
           return broadcastDt;
    }
    public void setBroadcastDt(Date broadcastDt) {
           this.broadcastDt = broadcastDt;
    }
	
	@Column(name = "created_by", length = 50)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_dt", nullable = false, length = 19)
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	
	@Column(name = "modify_by", length = 50)
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_dt", nullable = false, length = 19)
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	
	@Column(name = "status", length = 20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "flag", length = 10)
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "retry_count", nullable = false)
	public Integer getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "completed_date", length = 19)
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	@Column(name = "insert_req_status", length = 80)
	public String getInsertReqStatus() {
		return insertReqStatus;
	}
	public void setInsertReqStatus(String insertReqStatus) {
		this.insertReqStatus = insertReqStatus;
	}
	
}
