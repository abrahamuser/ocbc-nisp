package com.silverlake.mleb.pex.bean;

import java.io.Serializable;

import com.silverlake.hlb.mib.bean.ObRequest;


public class ObPexRequest extends ObRequest implements Serializable
{

	private String pexTranxType;
	private ObPexTransactionDetails pexTransactionDetails;
	private ObPexUserDetails pexUserDetails;
	private String authorizationCode;
	
	
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
	public String getPexTranxType() {
		return pexTranxType;
	}
	public void setPexTranxType(String pexTranxType) {
		this.pexTranxType = pexTranxType;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	
	
	
	
}
