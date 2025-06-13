package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObMessageMappingResponse extends ObResponse implements Serializable
{

	
	private String totalRecord;
	private String currentPageNum;
	List<ObMessageMappingBean> msgMappingList;
	
	

	public String getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<ObMessageMappingBean> getMsgMappingList() {
		return msgMappingList;
	}

	public void setMsgMappingList(List<ObMessageMappingBean> msgMappingList) {
		this.msgMappingList = msgMappingList;
	}

	public String getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(String currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	
	
}

