package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class RemitterCountryResponseData extends VCResponseData {
	private List<RemitterCountryList> data;

	public List<RemitterCountryList> getData() {
		return data;
	}

	public void setData(List<RemitterCountryList> data) {
		this.data = data;
	}
    
}

