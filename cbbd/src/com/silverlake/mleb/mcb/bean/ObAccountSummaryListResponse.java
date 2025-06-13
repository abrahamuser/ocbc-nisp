package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObAccountSummaryListResponse extends ObResponse implements Serializable
{
	
	private List<ObAccountSummaryBean> obAccSummaryListBean;
	private List<ObAccountSpecificBean> obAccountSpecificBean; 

	
	public List<ObAccountSummaryBean> getObAccSummaryListBean() {
		return obAccSummaryListBean;
	}
	public void setObAccSummaryListBean(
			List<ObAccountSummaryBean> obAccSummaryListBean) {
		this.obAccSummaryListBean = obAccSummaryListBean;
	}
	public List<ObAccountSpecificBean> getObAccountSpecificBean() {
		return obAccountSpecificBean;
	}
	public void setObAccountSpecificBean(
			List<ObAccountSpecificBean> obAccountSpecificBean) {
		this.obAccountSpecificBean = obAccountSpecificBean;
	}
	
}
