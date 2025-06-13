package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryModulesDeleteRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 2863931667184634653L;

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