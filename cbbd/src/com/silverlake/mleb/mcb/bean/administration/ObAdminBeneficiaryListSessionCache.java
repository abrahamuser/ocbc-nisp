package com.silverlake.mleb.mcb.bean.administration;

import java.io.Serializable;


import com.silverlake.mleb.mcb.bean.ObSessionCache;

public class ObAdminBeneficiaryListSessionCache extends ObSessionCache implements Serializable {
		
	private ObAdminBeneficiaryListResponse beneficiaryListResponseData;

	public ObAdminBeneficiaryListResponse getBeneficiaryListResponseData() {
		return beneficiaryListResponseData;
	}

	public void setBeneficiaryListResponseData(ObAdminBeneficiaryListResponse beneficiaryListResponseData) {
		this.beneficiaryListResponseData = beneficiaryListResponseData;
	}
	

	
}
