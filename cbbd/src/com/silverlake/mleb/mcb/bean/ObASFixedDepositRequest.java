
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObASFixedDepositRequest extends ObRequest implements Serializable{
	
	private ObASFixedDepositBean obASFixedDepositBean;
	private ObAccountSummaryBean obAccountSummaryBean; 

	public ObASFixedDepositBean getObASFixedDepositBean() {
		return obASFixedDepositBean;
	}

	public void setObASFixedDepositBean(ObASFixedDepositBean obASFixedDepositBean) {
		this.obASFixedDepositBean = obASFixedDepositBean;
	}

	public ObAccountSummaryBean getObAccountSummaryBean() {
		return obAccountSummaryBean;
	}

	public void setObAccountSummaryBean(ObAccountSummaryBean obAccountSummaryBean) {
		this.obAccountSummaryBean = obAccountSummaryBean;
	}
	
	
}