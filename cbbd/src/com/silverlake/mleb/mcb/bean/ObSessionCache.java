package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSessionCache implements Serializable  {
	private String transactionId;
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
