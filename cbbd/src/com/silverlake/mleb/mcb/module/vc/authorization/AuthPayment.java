package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;

import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class AuthPayment extends Transaction  implements Serializable
{
	private String action_cd;
	private String error_message;//Not omni field
	
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
 
}



	
