package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccessRestrictionBean implements Serializable{
	private String menuId;
	private String menuParentId;
	private String menuDescription;
	private Boolean isHide;
	
	private List<ObAccessRestrictionBean> childs;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public Boolean getIsHide() {
		return isHide;
	}

	public void setIsHide(Boolean isHide) {
		this.isHide = isHide;
	}

	public List<ObAccessRestrictionBean> getChilds() {
		return childs;
	}

	public void setChilds(List<ObAccessRestrictionBean> childs) {
		this.childs = childs;
	}
}
