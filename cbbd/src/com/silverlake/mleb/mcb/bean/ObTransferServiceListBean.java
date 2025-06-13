package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ObTransferServiceListBean implements Serializable{

	private List<MapPojo> mapPojo;
	private Map<String, String> transferService;
	private List<ObListCutOffBean> lsCutOffTime;
	
	public List<MapPojo> getMapPojo() {
		return mapPojo;
	}
	public void setMapPojo(List<MapPojo> mapPojo) {
		this.mapPojo = mapPojo;
	}
	public Map<String, String> getTransferService() {
		return transferService;
	}
	public void setTransferService(Map<String, String> transferService) {
		this.transferService = transferService;
	}
	public List<ObListCutOffBean> getLsCutOffTime() {
		return lsCutOffTime;
	}
	public void setLsCutOffTime(List<ObListCutOffBean> lsCutOffTime) {
		this.lsCutOffTime = lsCutOffTime;
	}
	
	
	
}
