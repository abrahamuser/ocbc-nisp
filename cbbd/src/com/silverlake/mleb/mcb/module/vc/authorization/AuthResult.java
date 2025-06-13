package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

public class AuthResult  implements Serializable
{
	  private String pymt_master_id;
	  private String error_message;
	  private String value_date;
	  
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
		 
	 
}



	
