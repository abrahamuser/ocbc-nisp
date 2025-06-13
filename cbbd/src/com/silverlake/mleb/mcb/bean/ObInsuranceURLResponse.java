package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObInsuranceURLResponse extends ObResponse implements Serializable
{
	private List<ObInsuranceURLBean> obInsuranceURLBean;

	public List<ObInsuranceURLBean> getObInsuranceURLBean() {
		return obInsuranceURLBean;
	}

	public void setObInsuranceURLBean(List<ObInsuranceURLBean> obInsuranceURLBean) {
		this.obInsuranceURLBean = obInsuranceURLBean;
	}
	
	
}
