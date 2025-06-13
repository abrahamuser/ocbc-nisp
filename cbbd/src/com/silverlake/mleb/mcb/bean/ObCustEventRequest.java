 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ObCustEventRequest extends ObRequest implements Serializable
{
	
	private String cif;
	private String pageNum;
	private String recordPerPage;
	private Date startDate;
	private Date endDate;
	
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(String recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	
	
	
	
	
}

