package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObONADocList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String docType;
	private String docName;
	private String isOptional;
	private String group;
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	

}
