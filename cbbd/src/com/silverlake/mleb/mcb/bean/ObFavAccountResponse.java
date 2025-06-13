package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObFavAccountResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 7709279929407878829L;

	private List<ObCorporateAccountOverview> accountList;

	public List<ObCorporateAccountOverview> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<ObCorporateAccountOverview> accountList) {
		this.accountList = accountList;
	}
    
	
	
	
}
