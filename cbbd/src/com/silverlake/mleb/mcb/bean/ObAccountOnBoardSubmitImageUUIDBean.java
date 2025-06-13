package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountOnBoardSubmitImageUUIDBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<String> uuid;

	public List<String> getUuid() {
		return uuid;
	}

	public void setUuid(List<String> uuid) {
		this.uuid = uuid;
	}
	
}
