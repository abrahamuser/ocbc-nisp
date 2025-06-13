package com.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_restriction_trx")
public class AccessRestrictionTransaction implements java.io.Serializable{
	private String sourceCode;
	private String productCode;
	private String menuId;
	private String menuDescription;
	private String status;
	private String isNew;
	private String isEdit;
	private String isCancel;
	private String isPrint;
	private String isNote;
	private String isAudit;
	private String isDelete;
	
	@Id
	@Column(name = "source_code", nullable=false)
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	@Id
	@Column(name = "prod_code", nullable=false)
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@Column(name = "menu_id", nullable=false)
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "menu_description")
	public String getMenuDescription() {
		return menuDescription;
	}
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "is_new")
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
	@Column(name = "is_edit")
	public String getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	
	@Column(name = "is_cancel")
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	
	@Column(name = "is_print")
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	
	@Column(name = "is_note")
	public String getIsNote() {
		return isNote;
	}
	public void setIsNote(String isNote) {
		this.isNote = isNote;
	}
	
	@Column(name = "is_audit")
	public String getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	
	@Column(name = "is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}
