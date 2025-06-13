package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountOnBoardingMapBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4374198387593384877L;
	private String imageKey;
	private List<String> lsImageValue;
	public String getImageKey() {
		return imageKey;
	}
	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}
	public List<String> getLsImageValue() {
		return lsImageValue;
	}
	public void setLsImageValue(List<String> lsImageValue) {
		this.lsImageValue = lsImageValue;
	}
	
	
}
