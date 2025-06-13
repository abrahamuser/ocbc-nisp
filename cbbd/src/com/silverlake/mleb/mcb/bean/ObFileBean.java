package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObFileBean implements Serializable
{
	private String tncContent;
	private String tncType;
	private String tncName;
	private String tncFileType;
	private String ccyCode;
	
	public String getTncContent() {
		return tncContent;
	}
	public void setTncContent(String tncContent) {
		this.tncContent = tncContent;
	}
	public String getTncType() {
		return tncType;
	}
	public void setTncType(String tncType) {
		this.tncType = tncType;
	}
	public String getTncName() {
		return tncName;
	}
	public void setTncName(String tncName) {
		this.tncName = tncName;
	}
	public String getTncFileType() {
		return tncFileType;
	}
	public void setTncFileType(String tncFileType) {
		this.tncFileType = tncFileType;
	}
	public String getCcyCode() {
		return ccyCode;
	}
	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}
	
		
}