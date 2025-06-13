package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class BeneficiaryAffiliationResponseData extends VCResponseData {
	private List<BeneficiaryAffiliationList> data;

	public List<BeneficiaryAffiliationList> getData() {
		return data;
	}

	public void setData(List<BeneficiaryAffiliationList> data) {
		this.data = data;
	}
    
}

