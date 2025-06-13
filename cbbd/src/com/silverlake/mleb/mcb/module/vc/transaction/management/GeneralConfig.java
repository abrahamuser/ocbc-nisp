package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.io.Serializable;
import java.util.List;

public class GeneralConfig implements Serializable{
	private List<String> available_currency;

	public List<String> getAvailable_currency() {
		return available_currency;
	}

	public void setAvailable_currency(List<String> available_currency) {
		this.available_currency = available_currency;
	}
}

