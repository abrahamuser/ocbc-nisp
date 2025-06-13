package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class UserManagementListRequestData extends VCRequest {
	
	private String src_usr_cd ;
    private String src_usr_name;
    private Integer page_no ;
    private Integer page_size ;
    
    
	public String getSrc_usr_cd() {
		return src_usr_cd;
	}
	public void setSrc_usr_cd(String src_usr_cd) {
		this.src_usr_cd = src_usr_cd;
	}
	public String getSrc_usr_name() {
		return src_usr_name;
	}
	public void setSrc_usr_name(String src_usr_name) {
		this.src_usr_name = src_usr_name;
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
