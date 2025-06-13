package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObCustEventResponse extends ObResponse implements Serializable
{

	
	private String totalRecord;
	private String currentPageNum;
	List<ObCustEventBean> custEventBean;
	
	

	public String getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}

	

	public List<ObCustEventBean> getCustEventBean() {
		return custEventBean;
	}

	public void setCustEventBean(List<ObCustEventBean> custEventBean) {
		this.custEventBean = custEventBean;
	}

	public String getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(String currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	
	
	
}

