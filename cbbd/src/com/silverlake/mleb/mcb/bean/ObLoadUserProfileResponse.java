package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObLoadUserProfileResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1992072771041387680L;
	private String responseCode;
	private String email;
	private String image;
	private String nickName;

	public String getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(String responseCode)
	{
		this.responseCode = responseCode;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

}
