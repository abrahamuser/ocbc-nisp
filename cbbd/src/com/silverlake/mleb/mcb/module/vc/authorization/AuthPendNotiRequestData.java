package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class AuthPendNotiRequestData extends VCRequest
{
	

	 public static String method_auth_admin_trxnotif_pending_list = "authorization/administration/trxnotif/pending_auth_list";
	 public static String method_auth_admin_trxnotif_pending_maint  = "authorization/administration/trxnotif/maintenance_auth";
	
	 
	 private String page_no;
     private String page_size;
	 private String action_cd;
	 private String device_type;
	 private String device_os;
	 private List<TransactionNotification> pending_authpol_list;
	 
	 
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	public String getPage_size() {
		return page_size;
	}
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
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
	public List<TransactionNotification> getPending_authpol_list() {
		return pending_authpol_list;
	}
	public void setPending_authpol_list(List<TransactionNotification> pending_authpol_list) {
		this.pending_authpol_list = pending_authpol_list;
	}
	 
	

}



	
