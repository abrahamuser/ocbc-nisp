package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObSmartNotificationSetupListResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 4300117728912141573L;

	private String emailAddr;
	private String isUpdated;
	private List<ObSmartNotificationSetupListBean> obSmartNotificationSetupListBeanList;

	public String getEmailAddr()
	{
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr)
	{
		this.emailAddr = emailAddr;
	}

	public String getIsUpdated()
	{
		return isUpdated;
	}

	public void setIsUpdated(String isUpdated)
	{
		this.isUpdated = isUpdated;
	}

	public List<ObSmartNotificationSetupListBean> getObSmartNotificationSetupListBeanList()
	{
		return obSmartNotificationSetupListBeanList;
	}

	public void setObSmartNotificationSetupListBeanList(
			List<ObSmartNotificationSetupListBean> obSmartNotificationSetupListBeanList)
	{
		this.obSmartNotificationSetupListBeanList = obSmartNotificationSetupListBeanList;
	}

}
