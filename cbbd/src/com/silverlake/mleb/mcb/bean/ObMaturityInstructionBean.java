package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObMaturityInstructionBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String maturityCode;
	private String maturityValue;
	private String maturityDesc;
	private String renewalCode;
	private String isDefault;
	private String isSelectable;
	
	public String getMaturityCode() {
		return maturityCode;
	}
	public void setMaturityCode(String maturityCode) {
		this.maturityCode = maturityCode;
	}
	public String getMaturityValue() {
		return maturityValue;
	}
	public void setMaturityValue(String maturityValue) {
		this.maturityValue = maturityValue;
	}
	public String getMaturityDesc() {
		return maturityDesc;
	}
	public void setMaturityDesc(String maturityDesc) {
		this.maturityDesc = maturityDesc;
	}
	public String getRenewalCode() {
		return renewalCode;
	}
	public void setRenewalCode(String renewalCode) {
		this.renewalCode = renewalCode;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getIsSelectable() {
		return isSelectable;
	}
	public void setIsSelectable(String isSelectable) {
		this.isSelectable = isSelectable;
	}

}
