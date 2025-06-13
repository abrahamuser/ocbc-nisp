package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionAuditTrailRequestData extends VCRequest{
	private String pymt_master_id;

	public String getPymt_master_id() {
		return pymt_master_id;
	}

	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
}



	
