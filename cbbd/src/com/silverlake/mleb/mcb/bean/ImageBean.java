package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ImageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String imageDocType;
	private String imageAdditionalInfo;
	private byte[] imageData;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
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

	public String getImageAdditionalInfo() {
		return imageAdditionalInfo;
	}

	public void setImageAdditionalInfo(String imageAdditionalInfo) {
		this.imageAdditionalInfo = imageAdditionalInfo;
	}
}
