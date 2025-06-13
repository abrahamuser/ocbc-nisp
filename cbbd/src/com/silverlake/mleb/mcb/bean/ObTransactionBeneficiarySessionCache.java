package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Map;

import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.FastBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.FastBeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.OnlineBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.OnlineBeneficiaryResponseData;


public class ObTransactionBeneficiarySessionCache extends ObSessionCache implements Serializable  {
	private Map<String, BeneficiaryResponseData> vcBeneficiaryResponseData;
	
	private com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData accountDetailsResponseData;
	private OnlineBeneficiaryResponseData onlineBeneficiaryResponseData;
	private OnlineBeneficiaryRequestData onlineBeneficiaryRequestData;
	private FastBeneficiaryResponseData fastBeneficiaryResponseData;
	private FastBeneficiaryRequestData fastBeneficiaryRequestData;
	private ObBankBean beneBankDetails;

	public Map<String, BeneficiaryResponseData> getVcBeneficiaryResponseData() {
		return vcBeneficiaryResponseData;
	}

	public void setVcBeneficiaryResponseData(Map<String, BeneficiaryResponseData> vcBeneficiaryResponseData) {
		this.vcBeneficiaryResponseData = vcBeneficiaryResponseData;
	}

	public com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData getAccountDetailsResponseData() {
		return accountDetailsResponseData;
	}

	public void setAccountDetailsResponseData(
			com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData accountDetailsResponseData) {
		this.accountDetailsResponseData = accountDetailsResponseData;
	}

	public OnlineBeneficiaryResponseData getOnlineBeneficiaryResponseData() {
		return onlineBeneficiaryResponseData;
	}

	public void setOnlineBeneficiaryResponseData(OnlineBeneficiaryResponseData onlineBeneficiaryResponseData) {
		this.onlineBeneficiaryResponseData = onlineBeneficiaryResponseData;
	}

	public OnlineBeneficiaryRequestData getOnlineBeneficiaryRequestData() {
		return onlineBeneficiaryRequestData;
	}

	public void setOnlineBeneficiaryRequestData(OnlineBeneficiaryRequestData onlineBeneficiaryRequestData) {
		this.onlineBeneficiaryRequestData = onlineBeneficiaryRequestData;
	}

	public ObBankBean getBeneBankDetails() {
		return beneBankDetails;
	}

	public void setBeneBankDetails(ObBankBean beneBankDetails) {
		this.beneBankDetails = beneBankDetails;
	}

	public FastBeneficiaryResponseData getFastBeneficiaryResponseData() {
		return fastBeneficiaryResponseData;
	}

	public void setFastBeneficiaryResponseData(FastBeneficiaryResponseData fastBeneficiaryResponseData) {
		this.fastBeneficiaryResponseData = fastBeneficiaryResponseData;
	}

	public FastBeneficiaryRequestData getFastBeneficiaryRequestData() {
		return fastBeneficiaryRequestData;
	}

	public void setFastBeneficiaryRequestData(FastBeneficiaryRequestData fastBeneficiaryRequestData) {
		this.fastBeneficiaryRequestData = fastBeneficiaryRequestData;
	}
	
}
