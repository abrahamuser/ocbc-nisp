package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObCrCrdPayListResponseBean extends ObResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<ObAccountBean> ObAccountList;
	private List<ObCreditCardBean> ObCreditCardList;
	
	public List<ObAccountBean> getObAccountList() {
		return ObAccountList;
	}
	public void setObAccountList(List<ObAccountBean> obAccountList) {
		ObAccountList = obAccountList;
	}
	public List<ObCreditCardBean> getObCreditCardList() {
		return ObCreditCardList;
	}
	public void setObCreditCardList(List<ObCreditCardBean> obCreditCardList) {
		ObCreditCardList = obCreditCardList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
