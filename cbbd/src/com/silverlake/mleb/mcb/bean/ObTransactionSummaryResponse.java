package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTransactionSummaryResponse extends ObResponseCache implements Serializable{
	private List<ObAuditBean> auditList;
	private List<ObAuditBean> authList;
	private List<ObNoteBean> trxNotesList;
	
	private String statementFileData;
	private String errorMessage;
	
	private List<ObMT103DataBean> mt103DataList;
	
	
	public List<ObAuditBean> getAuditList() {
		return auditList;
	}
	public void setAuditList(List<ObAuditBean> auditList) {
		this.auditList = auditList;
	}
	public List<ObAuditBean> getAuthList() {
		return authList;
	}
	public void setAuthList(List<ObAuditBean> authList) {
		this.authList = authList;
	}
	public List<ObNoteBean> getTrxNotesList() {
		return trxNotesList;
	}
	public void setTrxNotesList(List<ObNoteBean> trxNotesList) {
		this.trxNotesList = trxNotesList;
	}
	public String getStatementFileData() {
		return statementFileData;
	}
	public void setStatementFileData(String statementFileData) {
		this.statementFileData = statementFileData;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<ObMT103DataBean> getMt103DataList() {
		return mt103DataList;
	}
	public void setMt103DataList(List<ObMT103DataBean> mt103DataList) {
		this.mt103DataList = mt103DataList;
	}
	
}
