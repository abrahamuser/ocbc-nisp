package com.silverlake.mleb.mcb.bean.mgmtbene;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.module.vc.transaction.management.ViewBeneficiaryResponseData;


public class ObTransactionManageBeneficiarySessionCache extends ObSessionCache implements Serializable  {
	ViewBeneficiaryResponseData viewBeneficiaryResponseData;

	public ViewBeneficiaryResponseData getViewBeneficiaryResponseData() {
		return viewBeneficiaryResponseData;
	}

	public void setViewBeneficiaryResponseData(ViewBeneficiaryResponseData viewBeneficiaryResponseData) {
		this.viewBeneficiaryResponseData = viewBeneficiaryResponseData;
	}
}
