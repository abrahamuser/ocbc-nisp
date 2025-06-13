package com.silverlake.mleb.mcb.module.vc.accountlist;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class CustomerPortfolioRequestDatav2 extends VCRequest {
	
	private String prod_cd;
    private String cif_no;
    private String agreement_id;
  
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getCif_no() {
		return cif_no;
	}
	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}
	public String getAgreement_id() {
		return agreement_id;
	}
	public void setAgreement_id(String agreement_id) {
		this.agreement_id = agreement_id;
	}
	
	   
    
}
