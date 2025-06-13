package com.silverlake.mleb.mcb.module.vc.swift;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class SwiftOutboundRequestData extends VCRequest {

	private String remittance_no;

	public String getRemittance_no() {
		return remittance_no;
	}

	public void setRemittance_no(String remittance_no) {
		this.remittance_no = remittance_no;
	}

}
