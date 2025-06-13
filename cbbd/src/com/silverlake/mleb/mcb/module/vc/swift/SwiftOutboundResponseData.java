package com.silverlake.mleb.mcb.module.vc.swift;


import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class SwiftOutboundResponseData extends VCResponseData {
	
	private SwiftGPIOutbound gpi_data;

	public SwiftGPIOutbound getGpi_data() {
		return gpi_data;
	}

	public void setGpi_data(SwiftGPIOutbound gpi_data) {
		this.gpi_data = gpi_data;
	}	
	
		
}
