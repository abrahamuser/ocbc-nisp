package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class AuthPolicyAccount  implements Serializable
{
	  private String auth_policy_recid;
	  private String detail_record_id;
	  private String account_number;
	  
	  private String currency_code;
	  private String created_by_ucode;
	  
	  
	  private String created_by_uname;
	  private String updated_by_ucode;
	  private String updated_by_uname;
	  private String updated_date;
	  private int version;
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
	 
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
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
	  
	  
	 
	 
	 
}



	
