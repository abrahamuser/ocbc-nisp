package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObUnbilledCreditCardStatementRequest extends ObRequest implements Serializable {

	private static final long serialVersionUID = -4936801117744752324L;

	private String ccNumber;

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
}
