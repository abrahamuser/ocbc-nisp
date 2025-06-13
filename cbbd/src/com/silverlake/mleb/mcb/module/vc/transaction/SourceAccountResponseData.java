package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class SourceAccountResponseData extends VCResponseData{
	private List<ListAccount> list_account;

	public List<ListAccount> getList_account() {
		return list_account;
	}

	public void setList_account(List<ListAccount> list_account) {
		this.list_account = list_account;
	}
	
}
