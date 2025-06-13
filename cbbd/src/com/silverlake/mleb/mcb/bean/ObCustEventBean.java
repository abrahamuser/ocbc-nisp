package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//import com.fuzion.ws.account.endpoint.PackageVO;

public class ObCustEventBean implements Serializable {

	private String recordID;
	private String cif;
	private String mlebTranxId;
	private Date requestDatetime;
	private Date responseDatetime;
	private String service;
	private String statusCode;
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getMlebTranxId() {
		return mlebTranxId;
	}
	public void setMlebTranxId(String mlebTranxId) {
		this.mlebTranxId = mlebTranxId;
	}
	public Date getRequestDatetime() {
		return requestDatetime;
	}
	public void setRequestDatetime(Date requestDatetime) {
		this.requestDatetime = requestDatetime;
	}
	public Date getResponseDatetime() {
		return responseDatetime;
	}
	public void setResponseDatetime(Date responseDatetime) {
		this.responseDatetime = responseDatetime;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
