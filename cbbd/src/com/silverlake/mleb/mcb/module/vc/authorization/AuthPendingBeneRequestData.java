package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;

public class AuthPendingBeneRequestData extends VCRequest {
	private String action_cd;
	private List<BeneficiaryList> pending_bene_list;
	private String device_type;
    private String device_os;
	
	public String getAction_cd() {
		return action_cd;
	}
	public List<BeneficiaryList> getPending_bene_list() {
		return pending_bene_list;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public void setPending_bene_list(List<BeneficiaryList> pending_bene_list) {
		this.pending_bene_list = pending_bene_list;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_os() {
		return device_os;
	}
	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}
	
}
