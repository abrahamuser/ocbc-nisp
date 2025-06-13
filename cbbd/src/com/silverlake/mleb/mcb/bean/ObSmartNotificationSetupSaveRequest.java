package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObSmartNotificationSetupSaveRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 2447359191995313576L;

	private String emailAddr;
	private List<ObSmartNotificationSetupListBeanInfo> obSmartNotificationSetupListBeanInfoList;

	public String getEmailAddr()
	{
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr)
	{
		this.emailAddr = emailAddr;
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