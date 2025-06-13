 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ObMessageMappingRequest extends ObRequest implements Serializable
{
	
	private String pageNum;
	private String recordPerPage;
	private String action;
	private ObMessageMappingBean msgMapping;
	
	
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(String recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	public ObMessageMappingBean getMsgMapping() {
		return msgMapping;
	}
	public void setMsgMapping(ObMessageMappingBean msgMapping) {
		this.msgMapping = msgMapping;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	
	
	
}

