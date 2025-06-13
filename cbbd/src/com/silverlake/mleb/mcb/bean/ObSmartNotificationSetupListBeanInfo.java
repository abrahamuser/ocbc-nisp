package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSmartNotificationSetupListBeanInfo implements Serializable
{
	private static final long serialVersionUID = 6192554923064090634L;

	private String id;
	private boolean isPersonalEmail; // External Email
	private String notificationDesc;
	private String createdBy;
	private String cif;
	private boolean isSecuredInbox; // Internal Email
	private String categoryCode;
	private String timeCreated;
	private String notificationCode;
	private Integer version;
	private String notificationDescEn;
	private String updatedBy;
	private String timeUpdated;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public boolean isPersonalEmail()
	{
		return isPersonalEmail;
	}

	public void setPersonalEmail(boolean isPersonalEmail)
	{
		this.isPersonalEmail = isPersonalEmail;
	}

	public String getNotificationDesc()
	{
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc)
	{
		this.notificationDesc = notificationDesc;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getCif()
	{
		return cif;
	}

	public void setCif(String cif)
	{
		this.cif = cif;
	}

	public boolean isSecuredInbox()
	{
		return isSecuredInbox;
	}

	public void setSecuredInbox(boolean isSecuredInbox)
	{
		this.isSecuredInbox = isSecuredInbox;
	}

	public String getCategoryCode()
	{
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode)
	{
		this.categoryCode = categoryCode;
	}

	public String getTimeCreated()
	{
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated)
	{
		this.timeCreated = timeCreated;
	}

	public String getNotificationCode()
	{
		return notificationCode;
	}

	public void setNotificationCode(String notificationCode)
	{
		this.notificationCode = notificationCode;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	public String getNotificationDescEn() {
		return notificationDescEn;
	}

	public void setNotificationDescEn(String notificationDescEn) {
		this.notificationDescEn = notificationDescEn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(String timeUpdated) {
		this.timeUpdated = timeUpdated;
	}
	

}
