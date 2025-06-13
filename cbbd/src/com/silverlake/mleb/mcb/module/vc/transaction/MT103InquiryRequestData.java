package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class MT103InquiryRequestData extends VCRequest implements Serializable {
	
	private String prod_cd;
	private String pymt_master_id;
	private String bank_ref;
	
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
	public String getBank_ref() {
		return bank_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}

	
	
}



	
