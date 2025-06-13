package com.silverlake.mleb.mcb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "message_properties_i18n")
public class MessagePropertiesI18n implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private Integer messageId;
	private String languageCode;
	private String errMessage;
	private String errMessageCode;
	private String createBy;
	private Date createDt;
	private String modifyBy;
	private Date modifyDt;
	
	private MessageProperties messageProperties;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "MessagePropertiesI18n_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "message_id", nullable = true)
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	
	@Column(name = "language_code", nullable = true, length = 30)
	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	@Column(name = "err_message", length = 1000)
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
	@Column(name = "err_message_code", length = 100)
	public String getErrMessageCode() {
		return errMessageCode;
	}
	public void setErrMessageCode(String errMessageCode) {
		this.errMessageCode = errMessageCode;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_dt", length = 19)
	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	
	@ManyToOne
	@ForeignKey(name = "none") 
	@JoinColumn(name="message_id",insertable=false,updatable=false)
	public MessageProperties getMessageProperties() {
		return messageProperties;
	}

	public void setMessageProperties(MessageProperties messageProperties) {
		this.messageProperties = messageProperties;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
