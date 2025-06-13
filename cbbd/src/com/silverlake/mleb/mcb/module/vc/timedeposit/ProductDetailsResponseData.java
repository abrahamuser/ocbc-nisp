package com.silverlake.mleb.mcb.module.vc.timedeposit;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ProductDetailsResponseData extends VCResponseData {
    private ListAccount account;

	public ListAccount getAccount() {
		return account;
	}

	public void setAccount(ListAccount account) {
		this.account = account;
	}
}
