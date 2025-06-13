package com.silverlake.mleb.mcb.bean.vctransaction;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ObRequest;

public class ObVcTranxRequest extends ObRequest implements Serializable
{
 
	private String ndays;
	private String pageNo;
 

	public String getNdays() {
		return ndays;
	}

	public void setNdays(String ndays) {
		this.ndays = ndays;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
 
	
	
	
	
}