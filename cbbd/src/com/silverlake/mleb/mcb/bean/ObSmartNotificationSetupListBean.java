package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObSmartNotificationSetupListBean implements Serializable
{
	private static final long serialVersionUID = 3279180968380880958L;

	private String categoryCode;
	private String categoryName;
	private List<ObSmartNotificationSetupListBeanInfo> obSmartNotificationSetupListBeanInfoList;

	public String getCategoryCode()
	{
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode)
	{
		this.categoryCode = categoryCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ObSmartNotificationSetupListBeanInfo> getObSmartNotificationSetupListBeanInfoList()
	{
		return obSmartNotificationSetupListBeanInfoList;
	}

	public void setObSmartNotificationSetupListBeanInfoList(
			List<ObSmartNotificationSetupListBeanInfo> obSmartNotificationSetupListBeanInfoList)
	{
		this.obSmartNotificationSetupListBeanInfoList = obSmartNotificationSetupListBeanInfoList;
	}

}
