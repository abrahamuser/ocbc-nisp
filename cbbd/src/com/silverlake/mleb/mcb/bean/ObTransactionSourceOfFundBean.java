package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class ObTransactionSourceOfFundBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ObAccountBean> obAccountList;
	private String category;


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ObAccountBean> getObAccountList() {
		return obAccountList;
	}

	public void setObAccountList(List<ObAccountBean> obAccountList) {
		this.obAccountList = obAccountList;
	}
	
}
