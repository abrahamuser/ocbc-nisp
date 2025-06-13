package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Map;

import com.silverlake.mleb.mcb.module.vc.transaction.ChargeAccountRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.ChargeAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;


public class ObTransactionSourceOfFundSessionCache extends ObSessionCache implements Serializable  {
	private Map<String, SourceAccountResponseData> vcSourceResponseData;
	private Map<String, SourceAccountResponseData> vcBeneficiaryResponseData;
	private ChargeAccountRequestData chargeAccountRequestData;
	private ChargeAccountResponseData chargeAccountResponseData;
	
	public Map<String, SourceAccountResponseData> getVcSourceResponseData() {
		return vcSourceResponseData;
	}
	public void setVcSourceResponseData(Map<String, SourceAccountResponseData> vcSourceResponseData) {
		this.vcSourceResponseData = vcSourceResponseData;
	}
	public Map<String, SourceAccountResponseData> getVcBeneficiaryResponseData() {
		return vcBeneficiaryResponseData;
	}
	public void setVcBeneficiaryResponseData(Map<String, SourceAccountResponseData> vcBeneficiaryResponseData) {
		this.vcBeneficiaryResponseData = vcBeneficiaryResponseData;
	}
	public ChargeAccountRequestData getChargeAccountRequestData() {
		return chargeAccountRequestData;
	}
	public void setChargeAccountRequestData(ChargeAccountRequestData chargeAccountRequestData) {
		this.chargeAccountRequestData = chargeAccountRequestData;
	}
	public ChargeAccountResponseData getChargeAccountResponseData() {
		return chargeAccountResponseData;
	}
	public void setChargeAccountResponseData(ChargeAccountResponseData chargeAccountResponseData) {
		this.chargeAccountResponseData = chargeAccountResponseData;
	}
    
	
}
