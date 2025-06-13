package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class AcctAccess  implements Serializable
{
	  private String record_id;
	  private String org_cd;
	  
	  private String role_cd;
	  private String role_desc;
	  private String role_base;
	  
	  private int version;
	  private String created_by_ucode;
	  
	  private String created_by_uname;
	  private String created_date;
	  
	  private String updated_by_ucode;
	  private String updated_by_uname;
	  
	  private String updated_date;
	  private String maintenance_type;
	  private String auth_status_code;
	  private String auth_status;
	  private String pending_record_id;
	  private String role_id_tmp;

	  private List<AcctAccessDetails> acct_accprofile_list;

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public String getOrg_cd() {
		return org_cd;
	}

	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}

	public String getRole_cd() {
		return role_cd;
	}

	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCreated_by_ucode() {
		return created_by_ucode;
	}

	public void setCreated_by_ucode(String created_by_ucode) {
		this.created_by_ucode = created_by_ucode;
	}

	public String getCreated_by_uname() {
		return created_by_uname;
	}

	public void setCreated_by_uname(String created_by_uname) {
		this.created_by_uname = created_by_uname;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_by_ucode() {
		return updated_by_ucode;
	}

	public void setUpdated_by_ucode(String updated_by_ucode) {
		this.updated_by_ucode = updated_by_ucode;
	}

	public String getUpdated_by_uname() {
		return updated_by_uname;
	}

	public void setUpdated_by_uname(String updated_by_uname) {
		this.updated_by_uname = updated_by_uname;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public String getMaintenance_type() {
		return maintenance_type;
	}

	public void setMaintenance_type(String maintenance_type) {
		this.maintenance_type = maintenance_type;
	}

	public String getAuth_status_code() {
		return auth_status_code;
	}

	public void setAuth_status_code(String auth_status_code) {
		this.auth_status_code = auth_status_code;
	}

	public String getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}

	public String getPending_record_id() {
		return pending_record_id;
	}

	public void setPending_record_id(String pending_record_id) {
		this.pending_record_id = pending_record_id;
	}

	public String getRole_id_tmp() {
		return role_id_tmp;
	}

	public void setRole_id_tmp(String role_id_tmp) {
		this.role_id_tmp = role_id_tmp;
	}

	public List<AcctAccessDetails> getAcct_accprofile_list() {
		return acct_accprofile_list;
	}

	public void setAcct_accprofile_list(List<AcctAccessDetails> acct_accprofile_list) {
		this.acct_accprofile_list = acct_accprofile_list;
	}

	public String getRole_base() {
		return role_base;
	}

	public void setRole_base(String role_base) {
		this.role_base = role_base;
	}
 
	 
}



	
