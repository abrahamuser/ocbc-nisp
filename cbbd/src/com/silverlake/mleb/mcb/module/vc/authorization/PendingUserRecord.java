package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

public class PendingUserRecord implements Serializable{
	private String pending_record_id;
	private String user_cd;
	private Integer version;
	public String getPending_record_id() {
		return pending_record_id;
	}
	public String getUser_cd() {
		return user_cd;
	}
	public Integer getVersion() {
		return version;
	}
	public void setPending_record_id(String pending_record_id) {
		this.pending_record_id = pending_record_id;
	}
	public void setUser_cd(String user_cd) {
		this.user_cd = user_cd;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
