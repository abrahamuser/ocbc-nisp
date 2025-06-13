package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


import com.silverlake.mleb.mcb.bean.ObRequestCache;



public class ObDeviceUnbindgRequest extends ObRequestCache<ObDeviceUnbindCache>
		implements Serializable {

	private String device_id;
	private String cif;
		
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}


	
}
