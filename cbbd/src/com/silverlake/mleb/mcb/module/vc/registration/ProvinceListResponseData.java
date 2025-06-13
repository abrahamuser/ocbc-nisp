package com.silverlake.mleb.mcb.module.vc.registration;

import java.util.List;


import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ProvinceListResponseData extends VCResponseData {
	
	 private List<ProvinceList> province_list;

	public List<ProvinceList> getProvince_list() {
		return province_list;
	}

	public void setProvince_list(List<ProvinceList> province_list) {
		this.province_list = province_list;
	}
	
	
		
}
