package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObASDebitCardResponse extends ObResponse implements Serializable
{
	
	private ObASDebitCardBean obASDebitCardDetailBean; 
	private List<ObASDebitCardBean> obASDebitCardListBean;
	
	
	public ObASDebitCardBean getObASDebitCardDetailBean() {
		return obASDebitCardDetailBean;
	}
	public void setObASDebitCardDetailBean(ObASDebitCardBean obASDebitCardDetailBean) {
		this.obASDebitCardDetailBean = obASDebitCardDetailBean;
	}
	public List<ObASDebitCardBean> getObASDebitCardListBean() {
		return obASDebitCardListBean;
	}
	public void setObASDebitCardListBean(
			List<ObASDebitCardBean> obASDebitCardListBean) {
		this.obASDebitCardListBean = obASDebitCardListBean;
	}


}
