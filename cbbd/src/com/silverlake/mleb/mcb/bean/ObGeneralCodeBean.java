package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObGeneralCodeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gnCode;
	private String gnCodeDescription;
	private String gnCodeType;
	
	public String getGnCode() {
		return gnCode;
	}
	public void setGnCode(String gnCode) {
		this.gnCode = gnCode;
	}
	public String getGnCodeDescription() {
		return gnCodeDescription;
	}
	public void setGnCodeDescription(String gnCodeDescription) {
		this.gnCodeDescription = gnCodeDescription;
	}
	public String getGnCodeType() {
		return gnCodeType;
	}
	public void setGnCodeType(String gnCodeType) {
		this.gnCodeType = gnCodeType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
