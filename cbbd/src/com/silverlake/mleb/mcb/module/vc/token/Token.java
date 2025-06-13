package com.silverlake.mleb.mcb.module.vc.token;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.user.List_device;

public class Token  implements Serializable
{
	 private String token_type;
	 private String token_no;
	 private String is_default;
	 private List<List_device> device_information;
	 
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getToken_no() {
		return token_no;
	}
	public void setToken_no(String token_no) {
		this.token_no = token_no;
	}
	public String getIs_default() {
		return is_default;
	}
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	public List<List_device> getDevice_information() {
		return device_information;
	}
	public void setDevice_information(List<List_device> device_information) {
		this.device_information = device_information;
	}
 
}



	
