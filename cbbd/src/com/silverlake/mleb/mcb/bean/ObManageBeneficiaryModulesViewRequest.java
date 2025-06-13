package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryModulesViewRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 7670248385886578190L;
	
	private String beneficiaryId;

	public String getBeneficiaryId()
	{
		return beneficiaryId;
	}

	public void setBeneficiaryId(String beneficiaryId)
	{
		this.beneficiaryId = beneficiaryId;
	}

}