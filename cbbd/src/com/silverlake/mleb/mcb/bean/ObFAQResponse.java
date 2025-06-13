package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObFAQResponse extends ObResponse implements Serializable
{

	private List<ObFAQBean> obFAQBean;

	public List<ObFAQBean> getObFAQBean() {
		return obFAQBean;
	}

	public void setObFAQBean(List<ObFAQBean> obFAQBean) {
		this.obFAQBean = obFAQBean;
	}


	
	
}
