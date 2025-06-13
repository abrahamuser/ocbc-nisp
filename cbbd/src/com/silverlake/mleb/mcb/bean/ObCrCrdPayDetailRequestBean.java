package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObCrCrdPayDetailRequestBean extends ObRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private ObAccountBean obAccResult;
	private ObCreditCardBean obCrCrdResult;
	
	public ObAccountBean getObAccResult() {
		return obAccResult;
	}
	public void setObAccResult(ObAccountBean obAccResult) {
		this.obAccResult = obAccResult;
	}
	public ObCreditCardBean getObCrCrdResult() {
		return obCrCrdResult;
	}
	public void setObCrCrdResult(ObCreditCardBean obCrCrdResult) {
		this.obCrCrdResult = obCrCrdResult;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
