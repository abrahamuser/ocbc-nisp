package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObHelpInfoRequest extends ObRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String productCode;
	private String productPage;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductPage() {
		return productPage;
	}

	public void setProductPage(String productPage) {
		this.productPage = productPage;
	}
}