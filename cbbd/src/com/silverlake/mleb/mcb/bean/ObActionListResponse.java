package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.AuthResult;
import com.silverlake.mleb.mcb.module.vc.authorization.Backdate;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.user.List_role;
import com.silverlake.mleb.mcb.module.vc.user.Menu_access_list;
import com.silverlake.mleb.mcb.module.vc.user.Password_rules;

public class ObActionListResponse extends ObResponse implements Serializable
{
	private String random_number;
	private String modulus_string;
	
	private String org_cd;
	private String usr_cd;
	private String usr_id;
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

	public String getRandom_number() {
		return random_number;
	}

	public void setRandom_number(String random_number) {
		this.random_number = random_number;
	}

	public String getModulus_string() {
		return modulus_string;
	}

	public void setModulus_string(String modulus_string) {
		this.modulus_string = modulus_string;
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

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public List<Menu_access_list> getMenu_access_list() {
		return menu_access_list;
	}

	public void setMenu_access_list(List<Menu_access_list> menu_access_list) {
		this.menu_access_list = menu_access_list;
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
	
	
}
