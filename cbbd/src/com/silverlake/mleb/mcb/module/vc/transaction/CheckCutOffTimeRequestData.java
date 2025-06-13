package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class CheckCutOffTimeRequestData extends VCRequest{
	private String prod_cd;

	public String getProd_cd() {
		return prod_cd;
	}

	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
}
