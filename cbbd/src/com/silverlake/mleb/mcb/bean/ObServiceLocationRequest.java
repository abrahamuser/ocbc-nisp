package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObServiceLocationRequest extends ObRequest implements Serializable{

 
	
	private ObLocationBean obLocationBean;

	public ObLocationBean getObLocationBean() {
		return obLocationBean;
	}

	public void setObLocationBean(ObLocationBean obLocationBean) {
		this.obLocationBean = obLocationBean;
	}
	
	

}