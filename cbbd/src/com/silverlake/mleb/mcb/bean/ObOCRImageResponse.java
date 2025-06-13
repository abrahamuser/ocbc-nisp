package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObOCRImageResponse extends ObResponseCache implements Serializable{

	private static final long serialVersionUID = 1L;
	private String uuid;
	private String imageType;
	private String imageBase64String;
	private String imageAdditionalInfo;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageBase64String() {
		return imageBase64String;
	}
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}

	public String getImageAdditionalInfo() {
		return imageAdditionalInfo;
	}

	public void setImageAdditionalInfo(String imageAdditionalInfo) {
		this.imageAdditionalInfo = imageAdditionalInfo;
	}
}
