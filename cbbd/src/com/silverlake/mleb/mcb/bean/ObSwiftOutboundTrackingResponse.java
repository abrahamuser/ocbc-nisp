package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.registration.AccountDetails;
import com.silverlake.mleb.mcb.module.vc.registration.DocumentDetails;
import com.silverlake.mleb.mcb.module.vc.registration.InputterDetails;
import com.silverlake.mleb.mcb.module.vc.registration.ProvinceList;
import com.silverlake.mleb.mcb.module.vc.registration.SignerDetails;
import com.silverlake.mleb.mcb.module.vc.swift.SwiftGPIOutbound;


public class ObSwiftOutboundTrackingResponse extends ObResponseCache implements Serializable{
    	   
	
	private SwiftGPIOutbound gpi_data;

	public SwiftGPIOutbound getGpi_data() {
		return gpi_data;
	}

	public void setGpi_data(SwiftGPIOutbound gpi_data) {
		this.gpi_data = gpi_data;
	}

			
	
}
