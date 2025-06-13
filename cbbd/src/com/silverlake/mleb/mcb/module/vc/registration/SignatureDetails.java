package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

public class SignatureDetails  implements Serializable
{
	private Integer order_id;
	  private Integer master_order_id;
	  private String order_create_date;
	  private String signingcode_sent_date;
	  private String sign_status;
	  private String sign_status_desc;
	  private String sign_sequence;
	  private String sign_check_date;
	  private String cb_sign_date;
	  
	  
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getMaster_order_id() {
		return master_order_id;
	}
	public void setMaster_order_id(Integer master_order_id) {
		this.master_order_id = master_order_id;
	}
	public String getOrder_create_date() {
		return order_create_date;
	}
	public void setOrder_create_date(String order_create_date) {
		this.order_create_date = order_create_date;
	}
	public String getSigningcode_sent_date() {
		return signingcode_sent_date;
	}
	public void setSigningcode_sent_date(String signingcode_sent_date) {
		this.signingcode_sent_date = signingcode_sent_date;
	}
	public String getSign_status() {
		return sign_status;
	}
	public void setSign_status(String sign_status) {
		this.sign_status = sign_status;
	}
	public String getSign_status_desc() {
		return sign_status_desc;
	}
	public void setSign_status_desc(String sign_status_desc) {
		this.sign_status_desc = sign_status_desc;
	}
	public String getSign_sequence() {
		return sign_sequence;
	}
	public void setSign_sequence(String sign_sequence) {
		this.sign_sequence = sign_sequence;
	}
	public String getSign_check_date() {
		return sign_check_date;
	}
	public void setSign_check_date(String sign_check_date) {
		this.sign_check_date = sign_check_date;
	}
	public String getCb_sign_date() {
		return cb_sign_date;
	}
	public void setCb_sign_date(String cb_sign_date) {
		this.cb_sign_date = cb_sign_date;
	}
	
	 
}



	
