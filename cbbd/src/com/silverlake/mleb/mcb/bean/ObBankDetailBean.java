package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.util.List;

public class ObBankDetailBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String bankName;
	private List<ObSubBean> detailList;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<ObSubBean> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ObSubBean> detailList) {
		this.detailList = detailList;
	}

}
