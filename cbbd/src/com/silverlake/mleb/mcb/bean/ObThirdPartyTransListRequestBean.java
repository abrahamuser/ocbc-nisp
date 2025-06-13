package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObThirdPartyTransListRequestBean extends ObRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObAccountBean obFTAccountBean;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ObAccountBean getObFTAccountBean() {
		return obFTAccountBean;
	}

	public void setObFTAccountBean(ObAccountBean obFTAccountBean) {
		this.obFTAccountBean = obFTAccountBean;
	}
	
	

}
