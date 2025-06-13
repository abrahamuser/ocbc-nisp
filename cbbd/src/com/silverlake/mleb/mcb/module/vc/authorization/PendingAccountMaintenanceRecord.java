package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

public class PendingAccountMaintenanceRecord implements Serializable{
	private String pending_record_id;
	private String id;
	private String account_no;
	private String ccy_code;
	private String account_name;
	private String alias_name;
	private Integer version;
	private String maintenance_type;
	private String auth_status_code;
	private String auth_status;
	
	public String getPending_record_id() {
		return pending_record_id;
	}
	public String getId() {
		return id;
	}
	public String getAccount_no() {
		return account_no;
	}
	public String getCcy_code() {
		return ccy_code;
	}
	public String getAccount_name() {
		return account_name;
	}
	public String getAlias_name() {
		return alias_name;
	}
	public Integer getVersion() {
		return version;
	}
	public void setPending_record_id(String pending_record_id) {
		this.pending_record_id = pending_record_id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public void setAlias_name(String alias_name) {
		this.alias_name = alias_name;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
	
}
