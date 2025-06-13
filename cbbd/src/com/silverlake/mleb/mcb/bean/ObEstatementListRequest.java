package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObEstatementListRequest extends ObRequest implements Serializable {

	private static final long serialVersionUID = -2964689237426171564L;

	private String index;
	
	private String accountType;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
