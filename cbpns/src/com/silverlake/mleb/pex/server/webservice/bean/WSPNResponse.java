package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;

public class WSPNResponse extends WSResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String referenceId;

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	
	
	
}
