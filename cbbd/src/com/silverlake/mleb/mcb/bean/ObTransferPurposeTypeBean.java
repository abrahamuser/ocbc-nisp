package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTransferPurposeTypeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ListPurposeCode listPurposeCode;
	private List<MapPojo> mapPojo;
	
	public ListPurposeCode getListPurposeCode() {
		return listPurposeCode;
	}
	public void setListPurposeCode(ListPurposeCode listPurposeCode) {
		this.listPurposeCode = listPurposeCode;
	}
	public List<MapPojo> getMapPojo() {
		return mapPojo;
	}
	public void setMapPojo(List<MapPojo> mapPojo) {
		this.mapPojo = mapPojo;
	}

	
}
