package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObListCutOffBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String cutOffType;

	private String endDateTime;
	
	private String startDateTime;

	private String flagLLG;
	
	private String flagRTGS;
	
	public String getCutOffType() {
		return cutOffType;
	}

	public void setCutOffType(String cutOffType) {
		this.cutOffType = cutOffType;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getFlagLLG() {
		return flagLLG;
	}

	public void setFlagLLG(String flagLLG) {
		this.flagLLG = flagLLG;
	}

	public String getFlagRTGS() {
		return flagRTGS;
	}

	public void setFlagRTGS(String flagRTGS) {
		this.flagRTGS = flagRTGS;
	}
	
	
	
}
