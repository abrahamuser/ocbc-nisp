package com.silverlake.mleb.mcb.module.vc.authorization;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class AuthCountRequestData extends VCRequest {
	private String category_cd;

	public String getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(String category_cd) {
		this.category_cd = category_cd;
	}
}
