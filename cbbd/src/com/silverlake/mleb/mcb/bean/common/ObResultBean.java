package com.silverlake.mleb.mcb.bean.common;

import java.io.Serializable;

public class ObResultBean implements Serializable{
	private String recordID;
	private String statusCode;
	private String statusDesc;
	
	public String getRecordID() {
		return recordID;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}
