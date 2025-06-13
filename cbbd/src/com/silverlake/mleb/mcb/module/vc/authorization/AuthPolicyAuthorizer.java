package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class AuthPolicyAuthorizer  implements Serializable
{
	  private String auth_policy_recid;
	  private String detail_record_id;
 
	  private int weight;
	  private String auth_profile_id;
	  private String  auth_profile_name;
	  private String status_desc;
	  private String  state_desc;
	  
	  
	  private String created_by_ucode;
	  private String created_by_uname;
	  private String updated_by_ucode;
	  private String updated_by_uname;
	  private String updated_date;
	  private int version;
	  
	  private String status_code;	  
	  
	public String getAuth_policy_recid() {
		return auth_policy_recid;
	}
	public void setAuth_policy_recid(String auth_policy_recid) {
		this.auth_policy_recid = auth_policy_recid;
	}
	public String getDetail_record_id() {
		return detail_record_id;
	}
	public void setDetail_record_id(String detail_record_id) {
		this.detail_record_id = detail_record_id;
	}
	 
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getAuth_profile_id() {
		return auth_profile_id;
	}
	public void setAuth_profile_id(String auth_profile_id) {
		this.auth_profile_id = auth_profile_id;
	}
	public String getAuth_profile_name() {
		return auth_profile_name;
	}
	public void setAuth_profile_name(String auth_profile_name) {
		this.auth_profile_name = auth_profile_name;
	}
	public String getStatus_desc() {
		return status_desc;
	}
	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}
	public String getState_desc() {
		return state_desc;
	}
	public void setState_desc(String state_desc) {
		this.state_desc = state_desc;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	  
		 
	 
}



	
