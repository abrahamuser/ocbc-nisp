package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class NotificationSetup  implements Serializable
{
	private List<String> list_status;
	private List<String> list_role;
	private List<String> list_custom_email;
	
	
	public List<String> getList_status() {
		return list_status;
	}
	public void setList_status(List<String> list_status) {
		this.list_status = list_status;
	}
	public List<String> getList_role() {
		return list_role;
	}
	public void setList_role(List<String> list_role) {
		this.list_role = list_role;
	}
	public List<String> getList_custom_email() {
		return list_custom_email;
	}
	public void setList_custom_email(List<String> list_custom_email) {
		this.list_custom_email = list_custom_email;
	}
	
	
	 
}



	
