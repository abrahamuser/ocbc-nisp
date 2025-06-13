 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObCustProfileRequest extends ObRequest implements Serializable
{
	
	protected String password;
	protected String confirmPassword;
	private List<ImageDataBean> imageDataList;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public List<ImageDataBean> getImageDataList() {
		return imageDataList;
	}
	public void setImageDataList(List<ImageDataBean> imageDataList) {
		this.imageDataList = imageDataList;
	}
	
	
	
}

