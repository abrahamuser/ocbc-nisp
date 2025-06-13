package com.silverlake.mleb.mcb.module.vc.notification;

import java.io.Serializable;
import java.util.Date;

public class BoardNotification implements Serializable {

    private String id;
    private String notify_date;
    private String notify_exp;
    private String title;
    private String notes;
    private String notify_type;
    private String receive_list;
    private Boolean is_include_me;
    private String org_cd;
    private String created_by;
    private String time_created;
    private String updated_by;
    private String time_updated;
    private String version;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNotify_date() {
		return notify_date;
	}
	public void setNotify_date(String notify_date) {
		this.notify_date = notify_date;
	}
	public String getNotify_exp() {
		return notify_exp;
	}
	public void setNotify_exp(String notify_exp) {
		this.notify_exp = notify_exp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}
	public String getReceive_list() {
		return receive_list;
	}
	public void setReceive_list(String receive_list) {
		this.receive_list = receive_list;
	}
	public Boolean getIs_include_me() {
		return is_include_me;
	}
	public void setIs_include_me(Boolean is_include_me) {
		this.is_include_me = is_include_me;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getTime_created() {
		return time_created;
	}
	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public String getTime_updated() {
		return time_updated;
	}
	public void setTime_updated(String time_updated) {
		this.time_updated = time_updated;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}

