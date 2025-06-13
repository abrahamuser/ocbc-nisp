package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

public class AuthorizationResult implements Serializable{
	private String pending_record_id;
	private String action_code;
	private String result_status_cd;
	private String result_status_desc;
	
	//For account result
	private String id;
	private String account_no;
	private String ccy_code;
	
	public String getPending_record_id() {
		return pending_record_id;
	}
	public String getAction_code() {
		return action_code;
	}
	public String getResult_status_cd() {
		return result_status_cd;
	}
	public String getResult_status_desc() {
		return result_status_desc;
	}
	public void setPending_record_id(String pending_record_id) {
		this.pending_record_id = pending_record_id;
	}
	public void setAction_code(String action_code) {
		this.action_code = action_code;
	}
	public void setResult_status_cd(String result_status_cd) {
		this.result_status_cd = result_status_cd;
	}
	public void setResult_status_desc(String result_status_desc) {
		this.result_status_desc = result_status_desc;
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
	public void setId(String id) {
		this.id = id;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}
}
