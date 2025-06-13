package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTransactionHistSearchRequest extends ObRequest implements Serializable{

	/**
	 * transaction history search
	 */
	private static final long serialVersionUID = 1L;
	
	private ObTransactionBean obTransactionBean;

	public ObTransactionBean getObTransactionBean() {
		return obTransactionBean;
	}

	public void setObTransactionBean(ObTransactionBean obTransactionBean) {
		this.obTransactionBean = obTransactionBean;
	} 
	
	

}
