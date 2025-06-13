package com.silverlake.mleb.mcb.entity;

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
@Table(name = "maintenance_notification")
public class MaintenanceNotification implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer maintenanceId;
	private Date effectiveStartDt;
	private Date effectiveEndDt;
	private String description;
	private String status;
	private String attachmentFileName;
	private String attachmentFilePath;
	private Date createDt;
	private String createBy;
	private Date modifyDt;
	private String modifyBy;
	private String frequency;
	private String dayInWeek;
	private String startTime;
	private String endTime;
	private byte[] maintenanceImage;
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "MaintenanceNotification_id")
	@Column(name = "maintenance_id", nullable=false)
	public Integer getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_start_dt", length = 23)
	public Date getEffectiveStartDt() {
		return effectiveStartDt;
	}

	public void setEffectiveStartDt(Date effectiveStartDt) {
		this.effectiveStartDt = effectiveStartDt;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_end_dt", length = 23)
	public Date getEffectiveEndDt() {
		return effectiveEndDt;
	}
	public void setEffectiveEndDt(Date effectiveEndDt) {
		this.effectiveEndDt = effectiveEndDt;
	}
	
	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "status", length = 45)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "attachment_file_name", length = 100)
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	@Column(name = "attachment_file_path")
	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}
	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt", length = 19)
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	@Column(name = "create_by", length = 50)
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_dt", length = 19)
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	
	@Column(name = "modify_by", length = 50)
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	@Column(name = "frequency", length = 50)
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	@Column(name = "day_in_week", length = 50)
	public String getDayInWeek() {
		return dayInWeek;
	}
	
	public void setDayInWeek(String dayInWeek) {
		this.dayInWeek = dayInWeek;
	}
	
	@Column(name = "start_time", length = 10)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "end_time", length = 10)
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "maintenance_Image", columnDefinition="BLOB")
	public byte[] getMaintenanceImage() {
		return maintenanceImage;
	}
	
	public void setMaintenanceImage(byte[] maintenanceImage) {
		this.maintenanceImage = maintenanceImage;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	
}
