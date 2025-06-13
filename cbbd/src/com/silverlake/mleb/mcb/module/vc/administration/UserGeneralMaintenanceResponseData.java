package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

import java.util.List;

public class UserGeneralMaintenanceResponseData extends VCResponseData {
	
	private String usr_id;
	private String usr_cd;
	private String usr_nm;
	private String addr_1;
	private String addr_2;
	private String city_cd;
	private String prov_cd;
	private String ctry_cd;
	private String segment_cd;
	private String post_cd;
	private String prt_org_cd;
	private String cntct_cd;
	private String cntct_nm;
	private String cntct_typ;
	private String cntct_val;
	private String created_by_uid;
	private String created_by_ucd;
	private String created_by_unm;
	private String updated_by;
	private String updated_by_ucd;
	private String updated_by_unm;
	private String time_updated;
	private String time_created;
	private Integer version;
	private String org_cd;
	private String email;
	private String addr_3;
	private String usr_status_cd;
	private String usr_state_cd;
	private String user_profile_cd;
	private String user_profile_nm;
	private String exp_date;
	private String last_datetime_login;
	private String is_change_pass;
	private String is_logged;
	private String is_del;
	private String is_email;
	private String last_sent_email;
	private Integer login_attempt;
	private Integer token_cr_ctr;
	private Integer token_otp_ctr;
	private String is_auth_notif_alert;
	private Integer pin_block_flag;
	private String phone1;
	private String phone2;
	private String usr_group;
	private String session_timestamp;
	private String default_token;
	private String sw_token_eligibility;
	private Integer sw_token_cr_ctr;
	private Integer sw_token_otp_ctr;
	private String maker_auth_own;
	private List<List_role>list_role;
	private String maintenance_type;
	private String action_code;
	private String auth_status_code;
	private String auth_status;
	private String pending_record_id;
	private String org_name;
	public String getUsr_id() {
		return usr_id;
	}
	public String getUsr_cd() {
		return usr_cd;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public String getAddr_1() {
		return addr_1;
	}
	public String getAddr_2() {
		return addr_2;
	}
	public String getCity_cd() {
		return city_cd;
	}
	public String getProv_cd() {
		return prov_cd;
	}
	public String getCtry_cd() {
		return ctry_cd;
	}
	public String getSegment_cd() {
		return segment_cd;
	}
	public String getPost_cd() {
		return post_cd;
	}
	public String getPrt_org_cd() {
		return prt_org_cd;
	}
	public String getCntct_cd() {
		return cntct_cd;
	}
	public String getCntct_nm() {
		return cntct_nm;
	}
	public String getCntct_typ() {
		return cntct_typ;
	}
	public String getCntct_val() {
		return cntct_val;
	}
	public String getCreated_by_uid() {
		return created_by_uid;
	}
	public String getCreated_by_ucd() {
		return created_by_ucd;
	}
	public String getCreated_by_unm() {
		return created_by_unm;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public String getUpdated_by_ucd() {
		return updated_by_ucd;
	}
	public String getUpdated_by_unm() {
		return updated_by_unm;
	}
	public String getTime_updated() {
		return time_updated;
	}
	public String getTime_created() {
		return time_created;
	}
	public Integer getVersion() {
		return version;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public String getEmail() {
		return email;
	}
	public String getAddr_3() {
		return addr_3;
	}
	public String getUsr_status_cd() {
		return usr_status_cd;
	}
	public String getUsr_state_cd() {
		return usr_state_cd;
	}
	public String getUser_profile_cd() {
		return user_profile_cd;
	}
	public String getUser_profile_nm() {
		return user_profile_nm;
	}
	public String getExp_date() {
		return exp_date;
	}
	public String getLast_datetime_login() {
		return last_datetime_login;
	}
	public String getIs_change_pass() {
		return is_change_pass;
	}
	public String getIs_logged() {
		return is_logged;
	}
	public String getIs_del() {
		return is_del;
	}
	public String getIs_email() {
		return is_email;
	}
	public String getLast_sent_email() {
		return last_sent_email;
	}
	public Integer getLogin_attempt() {
		return login_attempt;
	}
	public Integer getToken_cr_ctr() {
		return token_cr_ctr;
	}
	public Integer getToken_otp_ctr() {
		return token_otp_ctr;
	}
	public String getIs_auth_notif_alert() {
		return is_auth_notif_alert;
	}
	public Integer getPin_block_flag() {
		return pin_block_flag;
	}
	public String getPhone1() {
		return phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public String getUsr_group() {
		return usr_group;
	}
	public String getSession_timestamp() {
		return session_timestamp;
	}
	public String getDefault_token() {
		return default_token;
	}
	public String getSw_token_eligibility() {
		return sw_token_eligibility;
	}
	public Integer getSw_token_cr_ctr() {
		return sw_token_cr_ctr;
	}
	public Integer getSw_token_otp_ctr() {
		return sw_token_otp_ctr;
	}
	public String getMaker_auth_own() {
		return maker_auth_own;
	}
	public List<List_role> getList_role() {
		return list_role;
	}
	public String getMaintenance_type() {
		return maintenance_type;
	}
	public String getAction_code() {
		return action_code;
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
	public String getOrg_name() {
		return org_name;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public void setUsr_cd(String usr_cd) {
		this.usr_cd = usr_cd;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	public void setAddr_1(String addr_1) {
		this.addr_1 = addr_1;
	}
	public void setAddr_2(String addr_2) {
		this.addr_2 = addr_2;
	}
	public void setCity_cd(String city_cd) {
		this.city_cd = city_cd;
	}
	public void setProv_cd(String prov_cd) {
		this.prov_cd = prov_cd;
	}
	public void setCtry_cd(String ctry_cd) {
		this.ctry_cd = ctry_cd;
	}
	public void setSegment_cd(String segment_cd) {
		this.segment_cd = segment_cd;
	}
	public void setPost_cd(String post_cd) {
		this.post_cd = post_cd;
	}
	public void setPrt_org_cd(String prt_org_cd) {
		this.prt_org_cd = prt_org_cd;
	}
	public void setCntct_cd(String cntct_cd) {
		this.cntct_cd = cntct_cd;
	}
	public void setCntct_nm(String cntct_nm) {
		this.cntct_nm = cntct_nm;
	}
	public void setCntct_typ(String cntct_typ) {
		this.cntct_typ = cntct_typ;
	}
	public void setCntct_val(String cntct_val) {
		this.cntct_val = cntct_val;
	}
	public void setCreated_by_uid(String created_by_uid) {
		this.created_by_uid = created_by_uid;
	}
	public void setCreated_by_ucd(String created_by_ucd) {
		this.created_by_ucd = created_by_ucd;
	}
	public void setCreated_by_unm(String created_by_unm) {
		this.created_by_unm = created_by_unm;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public void setUpdated_by_ucd(String updated_by_ucd) {
		this.updated_by_ucd = updated_by_ucd;
	}
	public void setUpdated_by_unm(String updated_by_unm) {
		this.updated_by_unm = updated_by_unm;
	}
	public void setTime_updated(String time_updated) {
		this.time_updated = time_updated;
	}
	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddr_3(String addr_3) {
		this.addr_3 = addr_3;
	}
	public void setUsr_status_cd(String usr_status_cd) {
		this.usr_status_cd = usr_status_cd;
	}
	public void setUsr_state_cd(String usr_state_cd) {
		this.usr_state_cd = usr_state_cd;
	}
	public void setUser_profile_cd(String user_profile_cd) {
		this.user_profile_cd = user_profile_cd;
	}
	public void setUser_profile_nm(String user_profile_nm) {
		this.user_profile_nm = user_profile_nm;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public void setLast_datetime_login(String last_datetime_login) {
		this.last_datetime_login = last_datetime_login;
	}
	public void setIs_change_pass(String is_change_pass) {
		this.is_change_pass = is_change_pass;
	}
	public void setIs_logged(String is_logged) {
		this.is_logged = is_logged;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public void setIs_email(String is_email) {
		this.is_email = is_email;
	}
	public void setLast_sent_email(String last_sent_email) {
		this.last_sent_email = last_sent_email;
	}
	public void setLogin_attempt(Integer login_attempt) {
		this.login_attempt = login_attempt;
	}
	public void setToken_cr_ctr(Integer token_cr_ctr) {
		this.token_cr_ctr = token_cr_ctr;
	}
	public void setToken_otp_ctr(Integer token_otp_ctr) {
		this.token_otp_ctr = token_otp_ctr;
	}
	public void setIs_auth_notif_alert(String is_auth_notif_alert) {
		this.is_auth_notif_alert = is_auth_notif_alert;
	}
	public void setPin_block_flag(Integer pin_block_flag) {
		this.pin_block_flag = pin_block_flag;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public void setUsr_group(String usr_group) {
		this.usr_group = usr_group;
	}
	public void setSession_timestamp(String session_timestamp) {
		this.session_timestamp = session_timestamp;
	}
	public void setDefault_token(String default_token) {
		this.default_token = default_token;
	}
	public void setSw_token_eligibility(String sw_token_eligibility) {
		this.sw_token_eligibility = sw_token_eligibility;
	}
	public void setSw_token_cr_ctr(Integer sw_token_cr_ctr) {
		this.sw_token_cr_ctr = sw_token_cr_ctr;
	}
	public void setSw_token_otp_ctr(Integer sw_token_otp_ctr) {
		this.sw_token_otp_ctr = sw_token_otp_ctr;
	}
	public void setMaker_auth_own(String maker_auth_own) {
		this.maker_auth_own = maker_auth_own;
	}
	public void setList_role(List<List_role> list_role) {
		this.list_role = list_role;
	}
	public void setMaintenance_type(String maintenance_type) {
		this.maintenance_type = maintenance_type;
	}
	public void setAction_code(String action_code) {
		this.action_code = action_code;
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
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	      
    }
