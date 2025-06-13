package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class AuthPendingAccountResponseData extends VCResponseData {
	private List<AuthorizationResult> acctmtn_result_list;

	public List<AuthorizationResult> getAcctmtn_result_list() {
		return acctmtn_result_list;
	}

	public void setAcctmtn_result_list(List<AuthorizationResult> acctmtn_result_list) {
		this.acctmtn_result_list = acctmtn_result_list;
	}
}
