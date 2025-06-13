package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObASLoanRequest extends ObRequest implements Serializable{
	
	private ObASLoanBean obASLoanBean;

	public ObASLoanBean getObASLoanBean() {
		return obASLoanBean;
	}

	public void setObASLoanBean(ObASLoanBean obASLoanBean) {
		this.obASLoanBean = obASLoanBean;
	}

}