package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObFile implements Serializable{
	private byte[] fileData;
	private String fileName;
	private String fileType;
	
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}	
