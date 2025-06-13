package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObViewPayeeMgmtRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String payeeId;

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
}