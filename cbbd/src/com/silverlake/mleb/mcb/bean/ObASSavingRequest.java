package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObASSavingRequest extends ObRequest implements Serializable{
	
	private ObASSavingBean obASSavingBean;
	private ObAccountSpecificBean obAccountSpecificBean; 

	public ObASSavingBean getObASSavingBean() {
		return obASSavingBean;
	}

	public void setObASSavingBean(ObASSavingBean obASSavingBean) {
		this.obASSavingBean = obASSavingBean;
	}

	public ObAccountSpecificBean getObAccountSpecificBean() {
		return obAccountSpecificBean;
	}

	public void setObAccountSpecificBean(ObAccountSpecificBean obAccountSpecificBean) {
		this.obAccountSpecificBean = obAccountSpecificBean;
	}

	
	
}