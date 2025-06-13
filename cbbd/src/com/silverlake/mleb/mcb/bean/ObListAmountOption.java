package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;



public class ObListAmountOption extends ObResponse implements Serializable
{
private static final long serialVersionUID = 1L;
	
	private List<MapPojo> mapPojo;

	public List<MapPojo> getMapPojo() {
		return mapPojo;
	}

	public void setMapPojo(List<MapPojo> mapPojo) {
		this.mapPojo = mapPojo;
	}
}
