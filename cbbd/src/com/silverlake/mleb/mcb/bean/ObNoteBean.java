package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ObNoteBean implements Serializable {
	private String id;
	private String createdBy;
	private String createdByName;
	private String dateCreated;
	private String pymtMasterId;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getPymtMasterId() {
		return pymtMasterId;
	}
	public void setPymtMasterId(String pymtMasterId) {
		this.pymtMasterId = pymtMasterId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
