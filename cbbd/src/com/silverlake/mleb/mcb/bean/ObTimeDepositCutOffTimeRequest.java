package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositCutOffTimeRequest extends ObRequest implements Serializable {

	private static final long serialVersionUID = 6872004893817586708L;
	
	private String productCode;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
