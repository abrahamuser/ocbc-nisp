package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObFAQRequest extends ObRequest implements Serializable{

 
	
	private ObFAQBean obFAQBean;

	public ObFAQBean getObFAQBean() {
		return obFAQBean;
	}

	public void setObFAQBean(ObFAQBean obFAQBean) {
		this.obFAQBean = obFAQBean;
	}


}