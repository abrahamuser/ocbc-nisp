package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObInsuranceURLBean implements Serializable
{
	private Integer urlId;
	private String url;
	private String status;
	
	public Integer getUrlId() {
		return urlId;
	}
	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
