package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObValidatePaymentInfoRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 7709279929407878829L;
	
	private String amount;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
