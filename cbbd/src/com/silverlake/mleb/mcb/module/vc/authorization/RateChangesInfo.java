package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

public class RateChangesInfo  implements Serializable
{
	private String prod_cd;
	private String bank_ref;
	private String pymt_master_id;
	private String base_interest_rate;
	private String base_interest_rate_changes;
	
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getBank_ref() {
		return bank_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
	public String getBase_interest_rate() {
		return base_interest_rate;
	}
	public void setBase_interest_rate(String base_interest_rate) {
		this.base_interest_rate = base_interest_rate;
	}
	public String getBase_interest_rate_changes() {
		return base_interest_rate_changes;
	}
	public void setBase_interest_rate_changes(String base_interest_rate_changes) {
		this.base_interest_rate_changes = base_interest_rate_changes;
	}
	 
}



	
