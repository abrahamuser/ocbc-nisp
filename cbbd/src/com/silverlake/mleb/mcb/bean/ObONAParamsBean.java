 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObONAParamsBean extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<MapPojo> minimumPlafond;
	private List<MapPojo> maximumPlafond;
	private ListMapPojo listTenorYears;
	
	
	public List<MapPojo> getMinimumPlafond() {
		return minimumPlafond;
	}
	public void setMinimumPlafond(List<MapPojo> minimumPlafond) {
		this.minimumPlafond = minimumPlafond;
	}
	public List<MapPojo> getMaximumPlafond() {
		return maximumPlafond;
	}
	public void setMaximumPlafond(List<MapPojo> maximumPlafond) {
		this.maximumPlafond = maximumPlafond;
	}
	public ListMapPojo getListTenorYears() {
		return listTenorYears;
	}
	public void setListTenorYears(ListMapPojo listTenorYears) {
		this.listTenorYears = listTenorYears;
	}
	
	
}

