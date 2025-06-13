package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class AuthPolicy  implements Serializable
{
	  private String record_id;
	  private String org_cd;
	  
	  private int version;
	  private String auth_policy_id;
	  
	  private String auth_policy_name;
	  private String created_by_ucode;
	  
	  private String created_by_uname;
	  private String created_date;
	  
	  private String updated_by_ucode;
	  private String updated_by_uname;
	  private String updated_date;
	  private String status_flag_cd;
	  private String status_flag_desc;
	  private String maintenance_type;
	  private String auth_status_code;
	  private String auth_status;
	  private String pending_record_id;

	  private List<AuthPolicyRule> authpol_rule_list;

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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAuth_policy_id() {
		return auth_policy_id;
	}

	public void setAuth_policy_id(String auth_policy_id) {
		this.auth_policy_id = auth_policy_id;
	}

	public String getAuth_policy_name() {
		return auth_policy_name;
	}

	public void setAuth_policy_name(String auth_policy_name) {
		this.auth_policy_name = auth_policy_name;
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

	public String getStatus_flag_cd() {
		return status_flag_cd;
	}

	public void setStatus_flag_cd(String status_flag_cd) {
		this.status_flag_cd = status_flag_cd;
	}

	public String getStatus_flag_desc() {
		return status_flag_desc;
	}

	public void setStatus_flag_desc(String status_flag_desc) {
		this.status_flag_desc = status_flag_desc;
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

	public List<AuthPolicyRule> getAuthpol_rule_list() {
		return authpol_rule_list;
	}

	public void setAuthpol_rule_list(List<AuthPolicyRule> authpol_rule_list) {
		this.authpol_rule_list = authpol_rule_list;
	}
	  
	 
	 
	 
}



	
