package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObTNCBean implements Serializable
{
	private String tncContent;
	private String tncTypeCode;
	private String tncName;
	
	
	public String getTncContent() {
		return tncContent;
	}
	public void setTncContent(String tncContent) {
		this.tncContent = tncContent;
	}
	public String getTncTypeCode() {
		return tncTypeCode;
	}
	public void setTncTypeCode(String tncTypeCode) {
		this.tncTypeCode = tncTypeCode;
	}
	public String getTncName() {
		return tncName;
	}
	public void setTncName(String tncName) {
		this.tncName = tncName;
	}
	
	
}