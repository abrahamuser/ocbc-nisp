package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObPendingAuthCountBean implements Serializable{
	private String menuItemId;
	private Integer count;
	
	public String getMenuItemId() {
		return menuItemId;
	}
	public Integer getCount() {
		return count;
	}
	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
