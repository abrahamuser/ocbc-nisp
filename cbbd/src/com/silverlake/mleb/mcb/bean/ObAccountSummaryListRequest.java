package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;



public class ObAccountSummaryListRequest extends ObRequest implements Serializable{
	
	private ObAccountSummaryBean obAccountSummaryBean;
	private ObAccountSpecificBean obAccountSpecificBean;
	private List<ObAccountSpecificBean> obAccountBeanSorting;
	public ObAccountSummaryBean getObAccountSummaryBean() {
		return obAccountSummaryBean;
	}

	public void setObAccountSummaryBean(ObAccountSummaryBean obAccountSummaryBean) {
		this.obAccountSummaryBean = obAccountSummaryBean;
	}

	public ObAccountSpecificBean getObAccountSpecificBean() {
		return obAccountSpecificBean;
	}

	public void setObAccountSpecificBean(ObAccountSpecificBean obAccountSpecificBean) {
		this.obAccountSpecificBean = obAccountSpecificBean;
	}

	public List<ObAccountSpecificBean> getObAccountBeanSorting() {
		return obAccountBeanSorting;
	}

	public void setObAccountBeanSorting(
			List<ObAccountSpecificBean> obAccountBeanSorting) {
		this.obAccountBeanSorting = obAccountBeanSorting;
	}



	

}