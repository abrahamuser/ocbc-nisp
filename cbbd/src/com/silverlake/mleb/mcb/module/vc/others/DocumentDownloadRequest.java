package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class DocumentDownloadRequest extends VCRequest {
	
	private String document_id;
	private String document_type;
	
	public String getDocument_id() {
		return document_id;
	}
	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
}