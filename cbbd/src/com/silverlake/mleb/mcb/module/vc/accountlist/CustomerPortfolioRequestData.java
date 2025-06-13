package com.silverlake.mleb.mcb.module.vc.accountlist;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class CustomerPortfolioRequestData extends VCRequest {
	
	private String customer_number;
    private String prod_cd;
    private String agreement_only;
   
    
	public String getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getAgreement_only() {
		return agreement_only;
	}
	public void setAgreement_only(String agreement_only) {
		this.agreement_only = agreement_only;
	}
	   
    
}
