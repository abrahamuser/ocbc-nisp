package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

public class PendingAuthCount implements Serializable{
	private String menu_item_id;
	private Integer count;
	
	public String getMenu_item_id() {
		return menu_item_id;
	}
	public Integer getCount() {
		return count;
	}
	public void setMenu_item_id(String menu_item_id) {
		this.menu_item_id = menu_item_id;
	}
	public void setCount(Integer count) {
		this.count = count;
	} 
}
