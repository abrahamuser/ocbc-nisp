package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObASFixedDepositResponse extends ObResponse implements Serializable
{
	
	private ObASFixedDepositBean obASAccountDetailBean; 
	private List<ObASFixedDepositBean> obASFixedDepositSublistBean;
	
	
	public ObASFixedDepositBean getObASAccountDetailBean() {
		return obASAccountDetailBean;
	}
	
	public void setObASAccountDetailBean(ObASFixedDepositBean obASAccountDetailBean) {
		this.obASAccountDetailBean = obASAccountDetailBean;
	}
	
	public List<ObASFixedDepositBean> getObASFixedDepositSublistBean() {
		return obASFixedDepositSublistBean;
	}
	
	public void setObASFixedDepositSublistBean(
			List<ObASFixedDepositBean> obASFixedDepositSublistBean) {
		this.obASFixedDepositSublistBean = obASFixedDepositSublistBean;
	}

}
