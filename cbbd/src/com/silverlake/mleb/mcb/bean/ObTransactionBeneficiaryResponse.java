package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTransactionBeneficiaryResponse extends ObResponseCache implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ObTransactionBeneficiaryBean> categoryList;
	
	private ObBeneAccountBean beneDetails; 

	public List<ObTransactionBeneficiaryBean> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<ObTransactionBeneficiaryBean> categoryList) {
		this.categoryList = categoryList;
	}

	public ObBeneAccountBean getBeneDetails() {
		return beneDetails;
	}

	public void setBeneDetails(ObBeneAccountBean beneDetails) {
		this.beneDetails = beneDetails;
	}
}
