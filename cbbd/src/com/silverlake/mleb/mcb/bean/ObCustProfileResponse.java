package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObCustProfileResponse extends ObResponse implements Serializable
{
	
	
	private byte[] loginImage;
	private String descriptions;
	public byte[] getLoginImage() {
		return loginImage;
	}
	public void setLoginImage(byte[] loginImage) {
		this.loginImage = loginImage;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	
	
	
}

