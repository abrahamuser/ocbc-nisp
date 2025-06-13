package com.silverlake.mleb.mcb.bean;

import java.util.Set;

public class ObPromotionRequestBean extends ObRequest implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String promotionName;
	private Set<String> statusSearch;
	private String statusSearchString;
	private String promotionType;
	
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public Set<String> getStatusSearch() {
		return statusSearch;
	}
	public void setStatusSearch(Set<String> statusSearch) {
		this.statusSearch = statusSearch;
	}
	public String getStatusSearchString() {
		return statusSearchString;
	}
	public void setStatusSearchString(String statusSearchString) {
		this.statusSearchString = statusSearchString;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
