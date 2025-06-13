package com.silverlake.mleb.mcb.bean.administration;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.ObRequestCache;

public class ObAdminBeneficiaryListRequest extends ObRequestCache<ObAdminBeneficiaryListSessionCache> implements Serializable{
	private String transferServiceType;
	
	public String getTransferServiceType() {
		return transferServiceType;
	}
	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
	}
}
