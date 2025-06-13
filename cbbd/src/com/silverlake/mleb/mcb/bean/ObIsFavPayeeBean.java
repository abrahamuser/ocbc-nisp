package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObIsFavPayeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String payeeId;
	private String isFavFlag;
	
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public String getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(String isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	
}
