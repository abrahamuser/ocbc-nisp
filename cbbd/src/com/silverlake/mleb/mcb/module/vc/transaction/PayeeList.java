package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;

public class PayeeList implements Serializable{
	 
	private String record_id;
    private String nick_name;
    private String email_address;
    private String phone_number;
    private String biller_name_id;
    private String biller_name_en;
	private String biller_name_cn;
    private String biller_code;
    private String billing_id;
    private String biller_type;
    
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getBiller_name_id() {
		return biller_name_id;
	}
	public void setBiller_name_id(String biller_name_id) {
		this.biller_name_id = biller_name_id;
	}
	public String getBiller_name_en() {
		return biller_name_en;
	}
	public void setBiller_name_en(String biller_name_en) {
		this.biller_name_en = biller_name_en;
	}
	public String getBiller_name_cn() {
		return biller_name_cn;
	}
	public void setBiller_name_cn(String biller_name_cn) {
		this.biller_name_cn = biller_name_cn;
	}
	public String getBiller_code() {
		return biller_code;
	}
	public void setBiller_code(String biller_code) {
		this.biller_code = biller_code;
	}
	public String getBilling_id() {
		return billing_id;
	}
	public void setBilling_id(String billing_id) {
		this.billing_id = billing_id;
	}
	public String getBiller_type() {
		return biller_type;
	}
	public void setBiller_type(String biller_type) {
		this.biller_type = biller_type;
	}
    
}

