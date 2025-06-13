package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObFundTransferListRequestBean extends ObRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObAccountBean obFTAccountBean;
	private ObTrxLimitBean obTrxLimitBean;

	public ObAccountBean getObFTAccountBean() {
		return obFTAccountBean;
	}

	public void setObFTAccountBean(ObAccountBean obFTAccountBean) {
		this.obFTAccountBean = obFTAccountBean;
	}

	public ObTrxLimitBean getObTrxLimitBean() {
		return obTrxLimitBean;
	}

	public void setObTrxLimitBean(ObTrxLimitBean obTrxLimitBean) {
		this.obTrxLimitBean = obTrxLimitBean;
	}
	
	
}
