package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObASSavingResponse extends ObResponse implements Serializable
{
	
	private ObASSavingBean ObSavingAccountDetailBean; 
	private List<ObASSavingBean> ObASSavingSublistBean;
	
	
	public ObASSavingBean getObSavingAccountDetailBean() {
		return ObSavingAccountDetailBean;
	}
	public void setObSavingAccountDetailBean(
			ObASSavingBean obSavingAccountDetailBean) {
		ObSavingAccountDetailBean = obSavingAccountDetailBean;
	}
	public List<ObASSavingBean> getObASSavingSublistBean() {
		return ObASSavingSublistBean;
	}
	public void setObASSavingSublistBean(List<ObASSavingBean> obASSavingSublistBean) {
		ObASSavingSublistBean = obASSavingSublistBean;
	}
	

}
