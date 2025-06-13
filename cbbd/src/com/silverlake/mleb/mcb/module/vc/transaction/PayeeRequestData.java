package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class PayeeRequestData extends VCRequest{
	private String group_name;
	private String biller_code;
	private Integer page_no;
	private Integer page_size;

	public Integer getPage_no() {
		return page_no;
	}

	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public String getBiller_code() {
		return biller_code;
	}

	public void setBiller_code(String biller_code) {
		this.biller_code = biller_code;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	
}
