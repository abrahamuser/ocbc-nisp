package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObCrCrdPayDetailResponseBean extends ObResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private ObAccountBean obAccResponseResult;
	private ObCreditCardBean obCrCrdResponseResult;
	
	public ObAccountBean getObAccResponseResult() {
		return obAccResponseResult;
	}
	public void setObAccResponseResult(ObAccountBean obAccResponseResult) {
		this.obAccResponseResult = obAccResponseResult;
	}
	public ObCreditCardBean getObCrCrdResponseResult() {
		return obCrCrdResponseResult;
	}
	public void setObCrCrdResponseResult(ObCreditCardBean obCrCrdResponseResult) {
		this.obCrCrdResponseResult = obCrCrdResponseResult;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
