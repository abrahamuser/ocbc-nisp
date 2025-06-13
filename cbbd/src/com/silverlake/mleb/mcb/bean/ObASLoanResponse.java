package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObASLoanResponse extends ObResponse implements Serializable
{
	private ObASLoanBean obASLoanBean;
	private List<ObASLoanBean> ObASLoanSublistBean;

	public ObASLoanBean getObASLoanBean() {
		return obASLoanBean;
	}

	public void setObASLoanBean(ObASLoanBean obASLoanBean) {
		this.obASLoanBean = obASLoanBean;
	}

	public List<ObASLoanBean> getObASLoanSublistBean() {
		return ObASLoanSublistBean;
	}

	public void setObASLoanSublistBean(List<ObASLoanBean> obASLoanSublistBean) {
		ObASLoanSublistBean = obASLoanSublistBean;
	}
	
	
}
