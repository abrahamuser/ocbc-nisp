package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTokenTnCResponse extends ObResponse implements Serializable
{
	private String tncContent;

	public String getTncContent() {
		return tncContent;
	}

	public void setTncContent(String tncContent) {
		this.tncContent = tncContent;
	}	
}

