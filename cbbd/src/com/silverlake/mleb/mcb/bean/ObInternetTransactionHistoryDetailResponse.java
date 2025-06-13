package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObInternetTransactionHistoryDetailResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 3700497138189604626L;

	private List<MapPojo> mapPojo;
	
	private String status;
	
	private String statusDesc;
	
	private String referenceNo;
	
	private String transferDate;

	public List<MapPojo> getMapPojo()
	{
		return mapPojo;
	}

	public void setMapPojo(List<MapPojo> mapPojo)
	{
		this.mapPojo = mapPojo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	
	

	
}
