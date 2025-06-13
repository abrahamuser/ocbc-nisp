package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObONAImageInfoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String imageDocType;
	private byte[] imageData;
	private String referenceNumber;
	private String imageStr;
	private String uuid;
	public String getImageDocType() {
		return imageDocType;
	}
	public void setImageDocType(String imageDocType) {
		this.imageDocType = imageDocType;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getImageStr() {
		return imageStr;
	}
	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
