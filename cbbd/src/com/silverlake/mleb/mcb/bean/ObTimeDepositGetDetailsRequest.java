package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositGetDetailsRequest extends ObRequest implements Serializable  {

	private static final long serialVersionUID = -4644097205719725425L;
	
	private String productCode;
	
	private String accountCcy;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getAccountCcy() {
		return accountCcy;
	}

	public void setAccountCcy(String accountCcy) {
		this.accountCcy = accountCcy;
	}
	
}
