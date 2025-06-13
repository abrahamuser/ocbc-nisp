package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTimeDepositCutOffTimeResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = 3082053565871430854L;
	
//	private Map<String, String> productCutOffTime;
	
	private String allowProcess;
	private String startCOT;
	private String endCOT;
	private String errorMessageCOT;

	public String getAllowProcess() {
		return allowProcess;
	}

	public void setAllowProcess(String allowProcess) {
		this.allowProcess = allowProcess;
	}

	public String getStartCOT() {
		return startCOT;
	}

	public void setStartCOT(String startCOT) {
		this.startCOT = startCOT;
	}

	public String getEndCOT() {
		return endCOT;
	}

	public void setEndCOT(String endCOT) {
		this.endCOT = endCOT;
	}

	public String getErrorMessageCOT() {
		return errorMessageCOT;
	}

	public void setErrorMessageCOT(String errorMessageCOT) {
		this.errorMessageCOT = errorMessageCOT;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
