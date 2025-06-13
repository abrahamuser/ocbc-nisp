package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class ObTransactionSourceOfFundResponse extends ObResponseCache implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ObTransactionSourceOfFundBean> categoryList;

	public List<ObTransactionSourceOfFundBean> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<ObTransactionSourceOfFundBean> categoryList) {
		this.categoryList = categoryList;
	}
}
