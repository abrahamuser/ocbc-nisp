package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObSendFeedbackRequest extends ObRequest implements Serializable{
	private String email;
	private String phone;
	private String category;
	private String feedback;
	private Boolean isBase64;
	
	private List<ObFile> imageList;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public List<ObFile> getImageList() {
		return imageList;
	}

	public void setImageList(List<ObFile> imageList) {
		this.imageList = imageList;
	}

	public Boolean getIsBase64() {
		return isBase64;
	}

	public void setIsBase64(Boolean isBase64) {
		this.isBase64 = isBase64;
	}
	
	
}