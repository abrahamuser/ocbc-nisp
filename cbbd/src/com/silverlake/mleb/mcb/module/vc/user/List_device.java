
package com.silverlake.mleb.mcb.module.vc.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class List_device implements Serializable {

    private String device_id;
    private String push_token;
    private String push_token_type;
    private String device_type;
    private String device_os;
    private String is_primary;
    private String bind_date;
    private String last_login_date;
    private String device_alias;
    private List<String> ext_channel_list;
    private List<String> list_appli_no;
    private String appliNo;
    private Map<String, String> extChannelListDesc;
    private String device_status;
    
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getPush_token() {
		return push_token;
	}
	public void setPush_token(String push_token) {
		this.push_token = push_token;
	}
	public String getPush_token_type() {
		return push_token_type;
	}
	public void setPush_token_type(String push_token_type) {
		this.push_token_type = push_token_type;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_os() {
		return device_os;
	}
	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}
	public String getIs_primary() {
		return is_primary;
	}
	public void setIs_primary(String is_primary) {
		this.is_primary = is_primary;
	}
	public String getBind_date() {
		return bind_date;
	}
	public void setBind_date(String bind_date) {
		this.bind_date = bind_date;
	}
	public String getLast_login_date() {
		return last_login_date;
	}
	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}
	public String getDevice_alias() {
		return device_alias;
	}
	public void setDevice_alias(String device_alias) {
		this.device_alias = device_alias;
	}
	public List<String> getExt_channel_list() {
		return ext_channel_list;
	}
	public void setExt_channel_list(List<String> ext_channel_list) {
		this.ext_channel_list = ext_channel_list;
	}
	public List<String> getList_appli_no() {
		return list_appli_no;
	}
	public void setList_appli_no(List<String> list_appli_no) {
		this.list_appli_no = list_appli_no;
	}
	public String getAppliNo() {
		return appliNo;
	}
	public void setAppliNo(String appliNo) {
		this.appliNo = appliNo;
	}
	public Map<String, String> getExtChannelListDesc() {
		return extChannelListDesc;
	}
	public void setExtChannelListDesc(Map<String, String> extChannelListDesc) {
		this.extChannelListDesc = extChannelListDesc;
	}
	public String getDevice_status() {
		return device_status;
	}
	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}
}
