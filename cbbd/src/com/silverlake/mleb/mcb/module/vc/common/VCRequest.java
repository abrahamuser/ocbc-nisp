package com.silverlake.mleb.mcb.module.vc.common;

import java.io.Serializable;

public class VCRequest implements Serializable
{
	private String org_cd;
    private String usr_cd;
    
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getUsr_cd() {
		return usr_cd;
	}
	public void setUsr_cd(String usr_cd) {
		this.usr_cd = usr_cd;
	}
}