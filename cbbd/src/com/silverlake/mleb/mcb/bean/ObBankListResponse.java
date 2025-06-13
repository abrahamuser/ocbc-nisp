package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObBankListResponse extends ObResponse implements Serializable{

	private List<ObBankBean> bankList;

	public List<ObBankBean> getBankList() {
		return bankList;
	}

	public void setBankList(List<ObBankBean> bankList) {
		this.bankList = bankList;
	}
	
}
