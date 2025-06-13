package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSwitchTrxnBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String accountUUID;
	private String fromProductUUID;
	private String toProductUUID;
	private String unit;
	
	public String getAccountUUID() {
		return accountUUID;
	}
	public void setAccountUUID(String accountUUID) {
		this.accountUUID = accountUUID;
	}
	public String getFromProductUUID() {
		return fromProductUUID;
	}
	public void setFromProductUUID(String fromProductUUID) {
		this.fromProductUUID = fromProductUUID;
	}
	public String getToProductUUID() {
		return toProductUUID;
	}
	public void setToProductUUID(String toProductUUID) {
		this.toProductUUID = toProductUUID;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
