package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObASDebitCardRequest extends ObRequest implements Serializable{
	
	private ObASDebitCardBean obASDebitCardBean;

	public ObASDebitCardBean getObASDebitCardBean() {
		return obASDebitCardBean;
	}

	public void setObASDebitCardBean(ObASDebitCardBean obASDebitCardBean) {
		this.obASDebitCardBean = obASDebitCardBean;
	}
	
}