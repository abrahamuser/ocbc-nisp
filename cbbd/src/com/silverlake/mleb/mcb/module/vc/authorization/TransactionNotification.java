package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class TransactionNotification  implements Serializable
{
      private String record_id;
	  private String org_cd;
	  private String contact_name;
	  private String contact_value;
	  private String contact_type;
	  private String created_by_ucode;
	  private String created_by_uname;
	  private String created_date;
	  private String updated_by_ucode;
	  private String updated_by_uname;
	  private String updated_date;
	  private int version;
	  private String status_flag_cd;
	  private String status_flag_desc;
	  private String maintenance_type;
	  private String auth_status_code;
	  private String auth_status;
	  private String pending_record_id;

	  private List<TransactionNotificationDetail> trx_notif_dtl_list;

	
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

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getContact_value() {
		return contact_value;
	}

	public void setContact_value(String contact_value) {
		this.contact_value = contact_value;
	}

	public String getContact_type() {
		return contact_type;
	}

	public void setContact_type(String contact_type) {
		this.contact_type = contact_type;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public List<TransactionNotificationDetail> getTrx_notif_dtl_list() {
		return trx_notif_dtl_list;
	}

	public void setTrx_notif_dtl_list(List<TransactionNotificationDetail> trx_notif_dtl_list) {
		this.trx_notif_dtl_list = trx_notif_dtl_list;
	}
	  
		
	 
}



	
