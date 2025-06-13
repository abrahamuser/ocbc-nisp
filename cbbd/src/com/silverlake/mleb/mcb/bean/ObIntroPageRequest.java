package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObIntroPageRequest extends ObRequest implements Serializable {

	private static final long serialVersionUID = -5241364881086984256L;
	
	private String moduleCode;

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
}
