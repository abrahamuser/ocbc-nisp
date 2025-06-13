package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class CountryListResponseData extends VCResponseData {
	private List<CountryList> country_list;

	public List<CountryList> getCountry_list() {
		return country_list;
	}

	public void setCountry_list(List<CountryList> country_list) {
		this.country_list = country_list;
	}
}
