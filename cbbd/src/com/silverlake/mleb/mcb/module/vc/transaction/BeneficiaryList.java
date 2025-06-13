package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;

public class BeneficiaryList implements Serializable{
	 
	private String uuid;
	private String record_id;
    private String prod_cd;
    private String nick_name;
    private String email_address;
    private String phone_number;
    private String account_number;
    private String account_currency;
    private String account_name;
    private String bene_address1;
    private String bene_address2;
    private String bene_address3;
    private String bank_country_code;
    private String bank_code;
    private String bank_city;
    private String bank_name;
    private String bank_branch_name;
    private String bank_network_clearing_code;
    private String bank_address1;
    private String bank_address2;
    private String bank_address3;
    private String province_code;
    private String city_code;
    private String is_shared;
    private String is_favorite;
    private String created_by_ucode;
    private String created_by_uname;
    private String created_date;
    private String maintenance_type;
    private String auth_status_code;
    private String auth_status;
    private String pending_record_id;
    private String p2p_flag;
    private String proxy_type;
    private String proxy_data;
    
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
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
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getAccount_currency() {
		return account_currency;
	}
	public void setAccount_currency(String account_currency) {
		this.account_currency = account_currency;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getBene_address1() {
		return bene_address1;
	}
	public void setBene_address1(String bene_address1) {
		this.bene_address1 = bene_address1;
	}
	public String getBene_address2() {
		return bene_address2;
	}
	public void setBene_address2(String bene_address2) {
		this.bene_address2 = bene_address2;
	}
	public String getBene_address3() {
		return bene_address3;
	}
	public void setBene_address3(String bene_address3) {
		this.bene_address3 = bene_address3;
	}
	public String getBank_country_code() {
		return bank_country_code;
	}
	public void setBank_country_code(String bank_country_code) {
		this.bank_country_code = bank_country_code;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_city() {
		return bank_city;
	}
	public void setBank_city(String bank_city) {
		this.bank_city = bank_city;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_branch_name() {
		return bank_branch_name;
	}
	public void setBank_branch_name(String bank_branch_name) {
		this.bank_branch_name = bank_branch_name;
	}
	public String getBank_network_clearing_code() {
		return bank_network_clearing_code;
	}
	public void setBank_network_clearing_code(String bank_network_clearing_code) {
		this.bank_network_clearing_code = bank_network_clearing_code;
	}
	public String getBank_address1() {
		return bank_address1;
	}
	public void setBank_address1(String bank_address1) {
		this.bank_address1 = bank_address1;
	}
	public String getBank_address2() {
		return bank_address2;
	}
	public void setBank_address2(String bank_address2) {
		this.bank_address2 = bank_address2;
	}
	public String getBank_address3() {
		return bank_address3;
	}
	public void setBank_address3(String bank_address3) {
		this.bank_address3 = bank_address3;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getIs_shared() {
		return is_shared;
	}
	public void setIs_shared(String is_shared) {
		this.is_shared = is_shared;
	}
	public String getIs_favorite() {
		return is_favorite;
	}
	public void setIs_favorite(String is_favorite) {
		this.is_favorite = is_favorite;
	}
	public String getCreated_by_ucode() {
		return created_by_ucode;
	}
	public String getCreated_by_uname() {
		return created_by_uname;
	}
	public String getCreated_date() {
		return created_date;
	}
	public String getMaintenance_type() {
		return maintenance_type;
	}
	public String getAuth_status_code() {
		return auth_status_code;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public String getPending_record_id() {
		return pending_record_id;
	}
	public void setCreated_by_ucode(String created_by_ucode) {
		this.created_by_ucode = created_by_ucode;
	}
	public void setCreated_by_uname(String created_by_uname) {
		this.created_by_uname = created_by_uname;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public void setMaintenance_type(String maintenance_type) {
		this.maintenance_type = maintenance_type;
	}
	public void setAuth_status_code(String auth_status_code) {
		this.auth_status_code = auth_status_code;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public void setPending_record_id(String pending_record_id) {
		this.pending_record_id = pending_record_id;
	}
	public String getP2p_flag() {
		return p2p_flag;
	}
	public void setP2p_flag(String p2p_flag) {
		this.p2p_flag = p2p_flag;
	}
	public String getProxy_type() {
		return proxy_type;
	}
	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	public String getProxy_data() {
		return proxy_data;
	}
	public void setProxy_data(String proxy_data) {
		this.proxy_data = proxy_data;
	}
    
	
}

