package com.silverlake.mleb.mcb.module.vc.authorization;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class PendingBeneListRequestData extends VCRequest {
	private Integer page_no;
	private Integer page_size;
	
	public Integer getPage_no() {
		return page_no;
	}
	public Integer getPage_size() {
		return page_size;
	}
	
	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
}
