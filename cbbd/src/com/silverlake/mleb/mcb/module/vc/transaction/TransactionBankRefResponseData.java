package com.silverlake.mleb.mcb.module.vc.transaction;


import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TransactionBankRefResponseData extends VCResponseData{
	private String bank_ref;

	public String getBank_ref() {
		return bank_ref;
	}

	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}


}
