package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.activation.DataHandler;

public class ObAccountOnBoardSubmitImageBean implements Serializable{

	private static final long serialVersionUID = 1L;


	//tanda360, giro individual
	private DataHandler imageData;
	private String imageDocType;
	public DataHandler getImageData() {
		return imageData;
	}
	public void setImageData(DataHandler imageData) {
		this.imageData = imageData;
	}
	public String getImageDocType() {
		return imageDocType;
	}
	public void setImageDocType(String imageDocType) {
		this.imageDocType = imageDocType;
	}
	
	
	
}
