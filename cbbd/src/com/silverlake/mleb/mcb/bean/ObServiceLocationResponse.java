package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObServiceLocationResponse extends ObResponse implements Serializable
{
	
	private List<ObLocationBean> obLocationBeans;

	public List<ObLocationBean> getObLocationBeans() {
		return obLocationBeans;
	}

	public void setObLocationBeans(List<ObLocationBean> obLocationBeans) {
		this.obLocationBeans = obLocationBeans;
	}
	
	
}
