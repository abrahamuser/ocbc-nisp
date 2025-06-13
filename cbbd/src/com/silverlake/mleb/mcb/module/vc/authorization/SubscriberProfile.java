package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

public class SubscriberProfile  implements Serializable
{
	  private String profile_cd;
	  private String profile_desc;
	  
	  private int version;
	  private String profile_record_id;
	  
	  private String maintenance_type;
	  private String auth_status_code;
	  
	  private String auth_status;
	  private String pending_record_id;
	public String getProfile_cd() {
		return profile_cd;
	}
	public void setProfile_cd(String profile_cd) {
		this.profile_cd = profile_cd;
	}
	public String getProfile_desc() {
		return profile_desc;
	}
	public void setProfile_desc(String profile_desc) {
		this.profile_desc = profile_desc;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getProfile_record_id() {
		return profile_record_id;
	}
	public void setProfile_record_id(String profile_record_id) {
		this.profile_record_id = profile_record_id;
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
	  
	 
	  
	
	 
	 
	 
	 
	 
}



	
