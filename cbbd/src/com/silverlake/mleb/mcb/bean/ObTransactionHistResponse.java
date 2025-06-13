package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTransactionHistResponse extends ObResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ObFavoriteAccountBean> favAccList;
	private ObAccountBean accountResult;
	private List<ObTransactionBean> transTypeCodeList; 
	
	
	public List<ObFavoriteAccountBean> getFavAccList() {
		return favAccList;
	}
	public void setFavAccList(List<ObFavoriteAccountBean> favAccList) {
		this.favAccList = favAccList;
	}
	
	public ObAccountBean getAccountResult() {
		return accountResult;
	}
	public void setAccountResult(ObAccountBean accountResult) {
		this.accountResult = accountResult;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ObTransactionBean> getTransTypeCodeList() {
		return transTypeCodeList;
	}
	public void setTransTypeCodeList(List<ObTransactionBean> transTypeCodeList) {
		this.transTypeCodeList = transTypeCodeList;
	}
	
	
	

}
