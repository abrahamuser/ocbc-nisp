package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class AccountAliasesRequestData extends VCRequest {
	
	private String account_no ;
    private String account_name;
    private String alias_name ;
    private Integer page_no ;
    private Integer page_size ;
    
    
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAlias_name() {
		return alias_name;
	}
	public void setAlias_name(String alias_name) {
		this.alias_name = alias_name;
	}
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
	        
       
    
}
