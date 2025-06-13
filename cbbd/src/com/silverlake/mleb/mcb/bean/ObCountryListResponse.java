package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Map;


public class ObCountryListResponse extends ObResponse implements Serializable{

	private Map<String,String> countryList;

	public Map<String, String> getCountryList() {
		return countryList;
	}

	public void setCountryList(Map<String, String> countryList) {
		this.countryList = countryList;
	}
}
