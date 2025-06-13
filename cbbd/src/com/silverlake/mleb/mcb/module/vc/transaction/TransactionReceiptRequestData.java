package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionReceiptRequestData extends VCRequest implements Serializable {
	  
	private String pymt_master_id;
	private String is_detail;
	private String is_additional_info;

	public String getPymt_master_id() {
		return pymt_master_id;
	}

	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}

	public String getIs_detail() {
		return is_detail;
	}

	public void setIs_detail(String is_detail) {
		this.is_detail = is_detail;
	}

	public String getIs_additional_info() {
		return is_additional_info;
	}

	public void setIs_additional_info(String is_additional_info) {
		this.is_additional_info = is_additional_info;
	}
	
	
}



	
