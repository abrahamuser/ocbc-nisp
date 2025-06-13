package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTransactionCurrencyRequest extends ObRequestCache<ObTransactionBeneficiarySessionCache> implements Serializable{
	private String transferServiceType;

	public String getTransferServiceType() {
		return transferServiceType;
	}

	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
	}
}
