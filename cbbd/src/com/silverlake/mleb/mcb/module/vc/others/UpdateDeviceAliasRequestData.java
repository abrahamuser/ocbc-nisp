package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class UpdateDeviceAliasRequestData extends VCRequest {

	private String device_id;
	private String device_alias;
	
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_alias() {
		return device_alias;
	}
	public void setDevice_alias(String device_alias) {
		this.device_alias = device_alias;
	}

	

}
