package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.TransactionNotification;

public class SignerDetails  implements Serializable
{
	private String signature_flow;	
	private List<InputterDetails> signer_list;
	
	
	public String getSignature_flow() {
		return signature_flow;
	}
	public void setSignature_flow(String signature_flow) {
		this.signature_flow = signature_flow;
	}
	public List<InputterDetails> getSigner_list() {
		return signer_list;
	}
	public void setSigner_list(List<InputterDetails> signer_list) {
		this.signer_list = signer_list;
	}
	
	 
}



	
