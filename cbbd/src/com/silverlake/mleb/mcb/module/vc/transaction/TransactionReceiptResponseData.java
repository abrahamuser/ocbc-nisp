package com.silverlake.mleb.mcb.module.vc.transaction;


import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TransactionReceiptResponseData extends VCResponseData{
	private String doc_path;

	public String getDoc_path() {
		return doc_path;
	}

	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}
}
