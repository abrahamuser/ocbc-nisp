package com.silverlake.mleb.mcb.module.vc.authorization;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class PendingAccountListRequestData extends VCRequest {
	private String src_account_no;
	private String src_account_name;
	private Integer page_no;
	private Integer page_size;
	public String getSrc_account_no() {
		return src_account_no;
	}
	public String getSrc_account_name() {
		return src_account_name;
	}
	public Integer getPage_no() {
		return page_no;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setSrc_account_no(String src_account_no) {
		this.src_account_no = src_account_no;
	}
	public void setSrc_account_name(String src_account_name) {
		this.src_account_name = src_account_name;
	}
	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
}
