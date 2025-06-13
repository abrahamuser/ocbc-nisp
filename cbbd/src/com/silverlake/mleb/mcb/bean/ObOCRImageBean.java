package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObOCRImageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private byte[] imageData;
	
	/*
	 * Type information associated with the image (e.g. NPWP, KTP, pay slip, etc)
	 */
	private String imageType;
	
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
}
