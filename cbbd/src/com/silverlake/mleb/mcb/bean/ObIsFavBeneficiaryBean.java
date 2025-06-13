package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObIsFavBeneficiaryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String beneficiaryId;
	private String isFavFlag;
	
	
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(String isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	
}
