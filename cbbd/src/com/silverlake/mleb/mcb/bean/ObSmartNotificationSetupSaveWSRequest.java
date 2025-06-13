package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSmartNotificationSetupSaveWSRequest implements Serializable
{
	private static final long serialVersionUID = -7110721826983053158L;
	private String id;
	private Boolean externalEmailFlag;
	private Boolean internetSecureFlag;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Boolean isExternalEmailFlag()
	{
		return externalEmailFlag;
	}

	public void setExternalEmailFlag(Boolean externalEmailFlag)
	{
		this.externalEmailFlag = externalEmailFlag;
	}

	public Boolean isInternetSecureFlag() {
		return internetSecureFlag;
	}

	public void setInternetSecureFlag(Boolean internetSecureFlag) {
		this.internetSecureFlag = internetSecureFlag;
	}
	
}