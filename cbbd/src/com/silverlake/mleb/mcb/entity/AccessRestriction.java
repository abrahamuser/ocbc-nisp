package com.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_restriction")
public class AccessRestriction implements java.io.Serializable{
	private String menuId;
	private String menuParentId;
	private String menuDescription;
	private int accessValidate;
	private int roleValidate;
	private int menuValidate;
	private int blockValidate;
	private String roleType;
	private String status;
	private String menuValidateId;
	
	@Id
	@Column(name = "menu_id", nullable=false)
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Column(name = "menu_parent_id")
	public String getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}
	@Column(name = "menu_description")
	public String getMenuDescription() {
		return menuDescription;
	}
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	@Column(name = "access_validate")
	public int getAccessValidate() {
		return accessValidate;
	}
	public void setAccessValidate(int accessValidate) {
		this.accessValidate = accessValidate;
	}
	@Column(name = "role_validate")
	public int getRoleValidate() {
		return roleValidate;
	}
	public void setRoleValidate(int roleValidate) {
		this.roleValidate = roleValidate;
	}
	@Column(name = "role_type")
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	@Column(name = "menu_validate")
	public int getMenuValidate() {
		return menuValidate;
	}
	public void setMenuValidate(int menuValidate) {
		this.menuValidate = menuValidate;
	}
	@Column(name = "block_validate")
	public int getBlockValidate() {
		return blockValidate;
	}
	public void setBlockValidate(int blockValidate) {
		this.blockValidate = blockValidate;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "menu_validate_id")
	public String getMenuValidateId() {
		return menuValidateId;
	}
	public void setMenuValidateId(String menuValidateId) {
		this.menuValidateId = menuValidateId;
	}
}
