package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ChargeAccountResponseData extends VCResponseData{
	
	private List<ListAccount> list_charges_account;

	public List<ListAccount> getList_charges_account() {
		return list_charges_account;
	}

	public void setList_charges_account(List<ListAccount> list_charges_account) {
		this.list_charges_account = list_charges_account;
	}
	
}
