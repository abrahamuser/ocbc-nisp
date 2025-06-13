package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObGeneralCodeResponseBean extends ObResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ObGeneralCodeBean> generalCodeList;
	private List<ObGeneralCodeBean> genStatusCodeList;
	
	public List<ObGeneralCodeBean> getGeneralCodeList() {
		return generalCodeList;
	}
	public void setGeneralCodeList(List<ObGeneralCodeBean> generalCodeList) {
		this.generalCodeList = generalCodeList;
	}
	public List<ObGeneralCodeBean> getGenStatusCodeList() {
		return genStatusCodeList;
	}
	public void setGenStatusCodeList(List<ObGeneralCodeBean> genStatusCodeList) {
		this.genStatusCodeList = genStatusCodeList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
