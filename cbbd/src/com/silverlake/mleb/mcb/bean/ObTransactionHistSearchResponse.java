package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTransactionHistSearchResponse extends ObResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ObAccountBean> accResultList;
	private List<ObTransactionBean> transHistResultList;
	
	public List<ObAccountBean> getAccResultList() {
		return accResultList;
	}
	public void setAccResultList(List<ObAccountBean> accResultList) {
		this.accResultList = accResultList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ObTransactionBean> getTransHistResultList() {
		return transHistResultList;
	}
	public void setTransHistResultList(List<ObTransactionBean> transHistResultList) {
		this.transHistResultList = transHistResultList;
	}
	

}
