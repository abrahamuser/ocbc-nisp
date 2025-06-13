package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


import com.silverlake.mleb.mcb.bean.ObRequestCache;



public class ObSwiftOutboundTrackingRequest extends ObRequestCache<ObSWIFTOutboundTrackingCache>
		implements Serializable {

	private String remittance_no;

	public String getRemittance_no() {
		return remittance_no;
	}

	public void setRemittance_no(String remittance_no) {
		this.remittance_no = remittance_no;
	}

}
