package com.silverlake.mleb.pex.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObResponse;


public class ObPexResponse extends ObResponse implements Serializable
{
	private List<ObAccountBean> fromListAccount;
	private ObPexTransactionDetails pexTransactionDetails;
	private String mobileNumber;
	private ObPexUserDetails pexUserDetails;
	private List<String> atmDenomination;
	private List<ObPexTransactionDetails> pexTransactionHistory;
	public List<ObAccountBean> getFromListAccount() {
		return fromListAccount;
	}

	public void setFromListAccount(List<ObAccountBean> fromListAccount) {
		this.fromListAccount = fromListAccount;
	}

	public ObPexTransactionDetails getPexTransactionDetails() {
		return pexTransactionDetails;
	}

	public void setPexTransactionDetails(
			ObPexTransactionDetails pexTransactionDetails) {
		this.pexTransactionDetails = pexTransactionDetails;
	}

	public ObPexUserDetails getPexUserDetails() {
		return pexUserDetails;
	}

	public void setPexUserDetails(ObPexUserDetails pexUserDetails) {
		this.pexUserDetails = pexUserDetails;
	}

	public List<String> getAtmDenomination() {
		return atmDenomination;
	}

	public void setAtmDenomination(List<String> atmDenomination) {
		this.atmDenomination = atmDenomination;
	}

	public List<ObPexTransactionDetails> getPexTransactionHistory() {
		return pexTransactionHistory;
	}

	public void setPexTransactionHistory(
			List<ObPexTransactionDetails> pexTransactionHistory) {
		this.pexTransactionHistory = pexTransactionHistory;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	

	
}
