 package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObSysConfRequest extends ObRequest implements Serializable
{
	

	private String maxDevBinding;
	private String sessionExpiration;
	
	public String getMaxDevBinding() {
		return maxDevBinding;
	}
	public void setMaxDevBinding(String maxDevBinding) {
		this.maxDevBinding = maxDevBinding;
	}
	public String getSessionExpiration() {
		return sessionExpiration;
	}
	public void setSessionExpiration(String sessionExpiration) {
		this.sessionExpiration = sessionExpiration;
	}
	
	
	
}

