package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class BillingOrganizationResponseData extends VCResponseData {
	private List<BillingOrganizationList> biller_list;

	public List<BillingOrganizationList> getBiller_list() {
		return biller_list;
	}

	public void setBiller_list(List<BillingOrganizationList> biller_list) {
		this.biller_list = biller_list;
	}
}
