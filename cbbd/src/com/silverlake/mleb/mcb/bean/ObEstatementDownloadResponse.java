package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObEstatementDownloadResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = 3804824614022647373L;
	
	private String fileName;
	private String historyPDF;
	private String responseMessage;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getHistoryPDF() {
		return historyPDF;
	}
	public void setHistoryPDF(String historyPDF) {
		this.historyPDF = historyPDF;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
