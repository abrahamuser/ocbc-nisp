package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class BeneficiaryCountryResponseData extends VCResponseData {
	private List<BeneficiaryCountryList> data;

	public List<BeneficiaryCountryList> getData() {
		return data;
	}

	public void setData(List<BeneficiaryCountryList> data) {
		this.data = data;
	}
    
}

