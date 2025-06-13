package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ObCrCrdSummaryResponseBean extends ObResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<? extends ObAccountBean> obAccountList;
	
	public List<? extends ObAccountBean> getObAccountList() {
		return obAccountList;
	}
	public void setObAccountList(List<? extends ObAccountBean> obAccountList) {
		this.obAccountList = obAccountList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
