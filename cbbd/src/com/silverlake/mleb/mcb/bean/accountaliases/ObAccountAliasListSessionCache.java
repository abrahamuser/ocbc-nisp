package com.silverlake.mleb.mcb.bean.accountaliases;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.module.vc.administration.AccountAliasesResponseData;


public class ObAccountAliasListSessionCache extends ObSessionCache implements Serializable {
		
	private AccountAliasesResponseData accountAliasesResponseData;
	

	public AccountAliasesResponseData getAccountAliasesResponseData() {
		return accountAliasesResponseData;
	}

	public void setAccountAliasesResponseData(AccountAliasesResponseData accountAliasesResponseData) {
		this.accountAliasesResponseData = accountAliasesResponseData;
	}
	
	
}
