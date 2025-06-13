package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObLoginBindingResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String refNo;
	private Boolean isValidPasscode;
	
	private List<ObDeviceCifBean> bindedDevices;

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Boolean getIsValidPasscode() {
		return isValidPasscode;
	}

	public void setIsValidPasscode(Boolean isValidPasscode) {
		this.isValidPasscode = isValidPasscode;
	}

	public List<ObDeviceCifBean> getBindedDevices() {
		return bindedDevices;
	}

	public void setBindedDevices(List<ObDeviceCifBean> bindedDevices) {
		this.bindedDevices = bindedDevices;
	}

}