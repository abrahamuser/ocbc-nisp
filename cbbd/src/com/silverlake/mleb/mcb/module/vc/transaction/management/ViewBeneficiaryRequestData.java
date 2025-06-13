package com.silverlake.mleb.mcb.module.vc.transaction.management;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

import java.util.List;

public class ViewBeneficiaryRequestData extends VCRequest{
	private String bene_id;

	public String getBene_id() {
		return bene_id;
	}

	public void setBene_id(String bene_id) {
		this.bene_id = bene_id;
	}
}
