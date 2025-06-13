package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ImageDataBean implements Serializable{

	/**
	 * 
	 */
	protected String imageID;
	protected byte[] imageData;
	
	
	public String getImageID() {
		return imageID;
	}
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

}
