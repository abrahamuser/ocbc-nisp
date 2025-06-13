package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class AuthPendingUserResponseData extends VCResponseData {
	private List<AuthorizationResult> userauth_result_list;

	public List<AuthorizationResult> getUserauth_result_list() {
		return userauth_result_list;
	}

	public void setUserauth_result_list(List<AuthorizationResult> userauth_result_list) {
		this.userauth_result_list = userauth_result_list;
	}
}
