package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObAccountOnBoardingProductTitleDescBean implements Serializable {

	
	private static final long serialVersionUID = 9084817547350833093L;
	private String productTitle;
	private String productSubtitle;
	private String productDesc;
	private String productImage;
	
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductSubtitle() {
		return productSubtitle;
	}
	public void setProductSubtitle(String productSubtitle) {
		this.productSubtitle = productSubtitle;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
}