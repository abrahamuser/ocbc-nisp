package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionBankRefRequestData extends VCRequest implements Serializable {
	  
	private String prod_cd;
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
}



	
