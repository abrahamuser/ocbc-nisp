package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class UserGeneralMaintenanceRequestData extends VCRequest {
	
	private String action_cd ;
    private String usr_id_mnt;
    private String usr_cd_mnt ;
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public String getUsr_id_mnt() {
		return usr_id_mnt;
	}
	public void setUsr_id_mnt(String usr_id_mnt) {
		this.usr_id_mnt = usr_id_mnt;
	}
	public String getUsr_cd_mnt() {
		return usr_cd_mnt;
	}
	public void setUsr_cd_mnt(String usr_cd_mnt) {
		this.usr_cd_mnt = usr_cd_mnt;
	}
    
           
    
}
