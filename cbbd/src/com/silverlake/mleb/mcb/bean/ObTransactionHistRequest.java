package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTransactionHistRequest extends ObRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObAccountBean accountResult;
	
	public ObAccountBean getAccountResult() {
		return accountResult;
	}
	public void setAccountResult(ObAccountBean accountResult) {
		this.accountResult = accountResult;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
