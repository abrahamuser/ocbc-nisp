package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObDocumentDownloadRequest extends ObRequest implements Serializable {

	private String documentId;
	private String documentType;
	
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
