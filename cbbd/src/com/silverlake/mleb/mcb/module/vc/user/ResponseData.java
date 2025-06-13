package com.silverlake.mleb.mcb.module.vc.user;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ResponseData extends VCResponseData {

	private String random_number;
	private String modulus_string;

	private String org_cd;
	private String usr_cd;
	private String usr_id;
	private String org_name;
	private String usr_name;
	private String usr_domain;
	private String email;
	private String phone;
	private List<Password_rules> password_rules;
	private List<List_role> list_role;
	private String session_id;
	private String user_status;
	private String user_state;
	private List<Menu_access_list> menu_access_list;
	private List<String> list_action;
	private List<String> list_restriction;
	private String profile_image;
	private String org_addr1;
	private String org_addr2;
	private String org_addr3;
	private Boolean purchase_fcy;
	
	private String activation_link;
	private String pbk_string;
	private String exponent_string;
	private String pin_block_flag;
	
	private String user_sec_status;
	private String remaining_cool_off;
	private String user_sec_check;

	public String getRandom_number() {
		return random_number;
	}

	public void setRandom_number(String random_number) {
		this.random_number = random_number;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public List<Menu_access_list> getMenu_access_list() {
		return menu_access_list;
	}

	public void setMenu_access_list(List<Menu_access_list> menu_access_list) {
		this.menu_access_list = menu_access_list;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getOrg_cd() {
		return org_cd;
	}

	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}

	public String getUsr_cd() {
		return usr_cd;
	}

	public void setUsr_cd(String usr_cd) {
		this.usr_cd = usr_cd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public String getModulus_string() {
		return modulus_string;
	}

	public void setModulus_string(String modulus_string) {
		this.modulus_string = modulus_string;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsr_name() {
		return usr_name;
	}

	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}

	public String getUsr_domain() {
		return usr_domain;
	}

	public void setUsr_domain(String usr_domain) {
		this.usr_domain = usr_domain;
	}

	public List<Password_rules> getPassword_rules() {
		return password_rules;
	}

	public void setPassword_rules(List<Password_rules> password_rules) {
		this.password_rules = password_rules;
	}

	public List<List_role> getList_role() {
		return list_role;
	}

	public void setList_role(List<List_role> list_role) {
		this.list_role = list_role;
	}

	public List<String> getList_action() {
		return list_action;
	}

	public void setList_action(List<String> list_action) {
		this.list_action = list_action;
	}

	public List<String> getList_restriction() {
		return list_restriction;
	}

	public void setList_restriction(List<String> list_restriction) {
		this.list_restriction = list_restriction;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_addr1() {
		return org_addr1;
	}

	public void setOrg_addr1(String org_addr1) {
		this.org_addr1 = org_addr1;
	}

	public String getOrg_addr2() {
		return org_addr2;
	}

	public void setOrg_addr2(String org_addr2) {
		this.org_addr2 = org_addr2;
	}

	public String getOrg_addr3() {
		return org_addr3;
	}

	public void setOrg_addr3(String org_addr3) {
		this.org_addr3 = org_addr3;
	}

	public String getActivation_link() {
		return activation_link;
	}

	public void setActivation_link(String activation_link) {
		this.activation_link = activation_link;
	}

	public Boolean getPurchase_fcy() {
		return purchase_fcy;
	}

	public void setPurchase_fcy(Boolean purchase_fcy) {
		this.purchase_fcy = purchase_fcy;
	}

	public String getPbk_string() {
		return pbk_string;
	}

	public void setPbk_string(String pbk_string) {
		this.pbk_string = pbk_string;
	}

	public String getExponent_string() {
		return exponent_string;
	}

	public void setExponent_string(String exponent_string) {
		this.exponent_string = exponent_string;
	}

	public String getPin_block_flag() {
		return pin_block_flag;
	}

	public void setPin_block_flag(String pin_block_flag) {
		this.pin_block_flag = pin_block_flag;
	}

	public String getUser_sec_status() {
		return user_sec_status;
	}

	public void setUser_sec_status(String user_sec_status) {
		this.user_sec_status = user_sec_status;
	}

	public String getRemaining_cool_off() {
		return remaining_cool_off;
	}

	public void setRemaining_cool_off(String remaining_cool_off) {
		this.remaining_cool_off = remaining_cool_off;
	}

	public String getUser_sec_check() {
		return user_sec_check;
	}

	public void setUser_sec_check(String user_sec_check) {
		this.user_sec_check = user_sec_check;
	}

}
