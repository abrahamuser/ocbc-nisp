package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ObRecurringTypeBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> listRecurringType;
	private List<MapPojo> mapPojo;
	
	public Map<String, String> getListRecurringType() {
		return listRecurringType;
	}
	public void setListRecurringType(Map<String, String> listRecurringType) {
		this.listRecurringType = listRecurringType;
	}
	public List<MapPojo> getMapPojo() {
		return mapPojo;
	}
	public void setMapPojo(List<MapPojo> mapPojo) {
		this.mapPojo = mapPojo;
	}
	
	
}
