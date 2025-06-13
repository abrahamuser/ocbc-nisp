package com.silverlake.mleb.mcb.module.vc.administration;

import java.util.List;

import com.silverlake.mleb.mcb.bean.common.ObUserRoleBean;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class UserManagementCreateModifyRequestData extends VCRequest {
	
	private String action_cd ;
    private String usr_id_input;
    private String usr_cd_input ;
    private String usr_nm_input ;
    private String usr_profile_cd_input;
    private String usr_email_addr_input;
    private List<ObUserRoleBean> usr_role_list_input;
    private String maker_auth_own; 
        
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public String getUsr_id_input() {
		return usr_id_input;
	}
	public void setUsr_id_input(String usr_id_input) {
		this.usr_id_input = usr_id_input;
	}
	public String getUsr_cd_input() {
		return usr_cd_input;
	}
	public void setUsr_cd_input(String usr_cd_input) {
		this.usr_cd_input = usr_cd_input;
	}
	public String getUsr_nm_input() {
		return usr_nm_input;
	}
	public void setUsr_nm_input(String usr_nm_input) {
		this.usr_nm_input = usr_nm_input;
	}
	public String getUsr_profile_cd_input() {
		return usr_profile_cd_input;
	}
	public void setUsr_profile_cd_input(String usr_profile_cd_input) {
		this.usr_profile_cd_input = usr_profile_cd_input;
	}
	public String getUsr_email_addr_input() {
		return usr_email_addr_input;
	}
	public void setUsr_email_addr_input(String usr_email_addr_input) {
		this.usr_email_addr_input = usr_email_addr_input;
	}
	public List<ObUserRoleBean> getUsr_role_list_input() {
		return usr_role_list_input;
	}
	public void setUsr_role_list_input(List<ObUserRoleBean> usr_role_list_input) {
		this.usr_role_list_input = usr_role_list_input;
	}
	public String getMaker_auth_own() {
		return maker_auth_own;
	}
	public void setMaker_auth_own(String maker_auth_own) {
		this.maker_auth_own = maker_auth_own;
	} 
    
      
}
