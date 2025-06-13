package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class BeneficiaryCategoryResponseData extends VCResponseData {
	private List<BeneficiaryCategoryList> data;

	public List<BeneficiaryCategoryList> getData() {
		return data;
	}

	public void setData(List<BeneficiaryCategoryList> data) {
		this.data = data;
	}
    
}

