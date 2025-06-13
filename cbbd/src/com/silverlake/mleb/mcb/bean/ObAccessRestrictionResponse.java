package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObAccessRestrictionResponse extends ObResponse implements Serializable{
	private Boolean isNewAllowed;
	private Boolean isEditAllowed;
	private Boolean isCancelAllowed;
	private Boolean isNotesAllowed;
	private Boolean isPrintAllowed;
	private Boolean isDeleteAllowed;
	private Boolean isAuditTrailAllowed;
	public Boolean getIsNewAllowed() {
		return isNewAllowed;
	}
	public void setIsNewAllowed(Boolean isNewAllowed) {
		this.isNewAllowed = isNewAllowed;
	}
	public Boolean getIsEditAllowed() {
		return isEditAllowed;
	}
	public void setIsEditAllowed(Boolean isEditAllowed) {
		this.isEditAllowed = isEditAllowed;
	}
	public Boolean getIsCancelAllowed() {
		return isCancelAllowed;
	}
	public void setIsCancelAllowed(Boolean isCancelAllowed) {
		this.isCancelAllowed = isCancelAllowed;
	}
	public Boolean getIsNotesAllowed() {
		return isNotesAllowed;
	}
	public void setIsNotesAllowed(Boolean isNotesAllowed) {
		this.isNotesAllowed = isNotesAllowed;
	}
	public Boolean getIsPrintAllowed() {
		return isPrintAllowed;
	}
	public void setIsPrintAllowed(Boolean isPrintAllowed) {
		this.isPrintAllowed = isPrintAllowed;
	}
	public Boolean getIsDeleteAllowed() {
		return isDeleteAllowed;
	}
	public void setIsDeleteAllowed(Boolean isDeleteAllowed) {
		this.isDeleteAllowed = isDeleteAllowed;
	}
	public Boolean getIsAuditTrailAllowed() {
		return isAuditTrailAllowed;
	}
	public void setIsAuditTrailAllowed(Boolean isAuditTrailAllowed) {
		this.isAuditTrailAllowed = isAuditTrailAllowed;
	}
	
	
}

