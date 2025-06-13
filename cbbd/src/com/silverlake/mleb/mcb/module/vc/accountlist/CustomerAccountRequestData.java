package com.silverlake.mleb.mcb.module.vc.accountlist;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class CustomerAccountRequestData extends VCRequest {
	
	private String search_cust_no ;
    private String search_cust_name;
    private String prod_cd ;
    private Integer page_size ;
    private Integer page_no ;
	        
    public String getSearch_cust_no() {
		return search_cust_no;
	}
	public void setSearch_cust_no(String search_cust_no) {
		this.search_cust_no = search_cust_no;
	}
	public String getSearch_cust_name() {
		return search_cust_name;
	}
	public void setSearch_cust_name(String search_cust_name) {
		this.search_cust_name = search_cust_name;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	public Integer getPage_no() {
		return page_no;
	}
	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}
    
    
}
