package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class BillingOrganizationRequestData extends VCRequest {
	private String locale;
	private String grp_name;
	private String biller_name;
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getGrp_name() {
		return grp_name;
	}
	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}
	public String getBiller_name() {
		return biller_name;
	}
	public void setBiller_name(String biller_name) {
		this.biller_name = biller_name;
	}
}