package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObUpdateUserProfileRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -8056166441207128371L;

	private String nickName;
	private String imageID;
	private byte[] imageData;
	private String email;

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getImageID()
	{
		return imageID;
	}

	public void setImageID(String imageID)
	{
		this.imageID = imageID;
	}

	public byte[] getImageData()
	{
		return imageData;
	}

	public void setImageData(byte[] imageData)
	{
		this.imageData = imageData;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

}