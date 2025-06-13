package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ViewBeneficiaryResponseData extends VCResponseData{
	private String prod_cd;
	private String bene_name;
	private String nick_name;
	private String email_address;
	private String phone_number;
	private String account_number;
	private String account_currency;
	private String bene_address1;
	private String bene_address2;
	private String bene_address3;
	private String bank_country_code;
	private String bank_code;
	private String bank_city;
	private String bank_name;
	private String bank_network_clearing_code;
	private String is_shared;
	private String is_favorite;
	private String proxy_type;
	private String proxy_data;
	
	private String bene_id;//This field not in omni, used to validate the cache integrity
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getBene_name() {
		return bene_name;
	}
	public void setBene_name(String bene_name) {
		this.bene_name = bene_name;
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
	public String getBank_network_clearing_code() {
		return bank_network_clearing_code;
	}
	public void setBank_network_clearing_code(String bank_network_clearing_code) {
		this.bank_network_clearing_code = bank_network_clearing_code;
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
	public String getBene_id() {
		return bene_id;
	}
	public void setBene_id(String bene_id) {
		this.bene_id = bene_id;
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
