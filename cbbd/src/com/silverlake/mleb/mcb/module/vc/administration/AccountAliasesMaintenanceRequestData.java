package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class AccountAliasesMaintenanceRequestData extends VCRequest {
	
	private String action_cd ;
    private String record_id;
    private String account_no ;
    private String account_name ;
    private String alias_name ;
    private Integer version ;
    
    
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
    
    
		        
       
    
}
