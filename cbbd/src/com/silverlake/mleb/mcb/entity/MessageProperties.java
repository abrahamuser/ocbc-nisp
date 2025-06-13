package com.silverlake.mleb.mcb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "message_properties")
public class MessageProperties implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer messageId;
	private String errMessageCode;
	private String remarks;
	private String status;
	private String createBy;
	private Date createDt;
	private String modifyBy;
	private Date modifyDt;
	private String syncRemarks;
	
	
	
	private List<MessagePropertiesI18n> messageI18nList;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "MessageProperties_id")
	@Column(name = "message_id", nullable=false)
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	
	@Column(name = "err_message_code", length = 100, unique=true)
	public String getErrMessageCode() {
		return errMessageCode;
	}
	public void setErrMessageCode(String errMessageCode) {
		this.errMessageCode = errMessageCode;
	}
		
	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "status", length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "create_by", length = 30)
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt", length = 19)
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	@Column(name = "modify_by", length = 30)
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	@Column(name = "sync_remarks", length = 100)
	public String getSyncRemarks() {
		return syncRemarks;
	}
	public void setSyncRemarks(String syncRemarks) {
		this.syncRemarks = syncRemarks;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_dt", length = 19)
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	
	@OneToMany(mappedBy="messageProperties", fetch=FetchType.LAZY)
	@ForeignKey(name = "none") 
	public List<MessagePropertiesI18n> getMessageI18nList() {
		return messageI18nList;
	}
	public void setMessageI18nList(
			List<MessagePropertiesI18n> messageI18nList) {
		this.messageI18nList = messageI18nList;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
