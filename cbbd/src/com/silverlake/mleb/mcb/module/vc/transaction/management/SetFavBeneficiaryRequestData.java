package com.silverlake.mleb.mcb.module.vc.transaction.management;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

import java.util.List;

public class SetFavBeneficiaryRequestData extends VCRequest{
	private String bene_id;
	private String is_favorite;

	public String getBene_id() {
		return bene_id;
	}

	public void setBene_id(String bene_id) {
		this.bene_id = bene_id;
	}

	public String getIs_favorite() {
		return is_favorite;
	}

	public void setIs_favorite(String is_favorite) {
		this.is_favorite = is_favorite;
	}
}
