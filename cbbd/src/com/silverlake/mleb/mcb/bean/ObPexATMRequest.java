package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObPexATMRequest extends ObRequest implements Serializable{
	
	private ObAccountBean obAccountBean; 
	private ObPexATMBean obPexATMBean;

	public ObPexATMBean getObPexATMBean() {
		return obPexATMBean;
	}

	public void setObPexATMBean(ObPexATMBean obPexATMBean) {
		this.obPexATMBean = obPexATMBean;
	}

	public ObAccountBean getObAccountBean() {
		return obAccountBean;
	}

	public void setObAccountBean(ObAccountBean obAccountBean) {
		this.obAccountBean = obAccountBean;
	}

}