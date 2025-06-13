package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class BeneficiaryListRequestData extends VCRequest {
	private String src_prod_code;
	private String src_acct_no;
	private String src_nick_name;
	private String src_ccy_code;
	private String src_created_date;
	private Integer page_no;
	private Integer page_size;
	
	public String getSrc_prod_code() {
		return src_prod_code;
	}
	public String getSrc_acct_no() {
		return src_acct_no;
	}
	public String getSrc_nick_name() {
		return src_nick_name;
	}
	public String getSrc_ccy_code() {
		return src_ccy_code;
	}
	public String getSrc_created_date() {
		return src_created_date;
	}
	public Integer getPage_no() {
		return page_no;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setSrc_prod_code(String src_prod_code) {
		this.src_prod_code = src_prod_code;
	}
	public void setSrc_acct_no(String src_acct_no) {
		this.src_acct_no = src_acct_no;
	}
	public void setSrc_nick_name(String src_nick_name) {
		this.src_nick_name = src_nick_name;
	}
	public void setSrc_ccy_code(String src_ccy_code) {
		this.src_ccy_code = src_ccy_code;
	}
	public void setSrc_created_date(String src_created_date) {
		this.src_created_date = src_created_date;
	}
	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
}
