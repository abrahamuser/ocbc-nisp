package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class AuthPendingBeneResponseData extends VCResponseData {
	private List<AuthorizationResult> bene_result_list;

	public List<AuthorizationResult> getBene_result_list() {
		return bene_result_list;
	}

	public void setBene_result_list(List<AuthorizationResult> bene_result_list) {
		this.bene_result_list = bene_result_list;
	}
}
