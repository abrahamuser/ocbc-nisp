package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;

public class AuthCountResponseData extends VCResponseData {
	private List<PendingAuthCount> pending_auth_count_list;

	public List<PendingAuthCount> getPending_auth_count_list() {
		return pending_auth_count_list;
	}

	public void setPending_auth_count_list(List<PendingAuthCount> pending_auth_count_list) {
		this.pending_auth_count_list = pending_auth_count_list;
	}
}
