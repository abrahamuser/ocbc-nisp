package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObFavAccountRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -3822270038236396582L;
	
	private String prd_cd;

	List<ObCorporateAccountOverview> newFavAccountList ;
	
	List<ObCorporateAccountOverview> oldFavAccountList ;

	public List<ObCorporateAccountOverview> getNewFavAccountList() {
		return newFavAccountList;
	}

	public void setNewFavAccountList(List<ObCorporateAccountOverview> newFavAccountList) {
		this.newFavAccountList = newFavAccountList;
	}

	public List<ObCorporateAccountOverview> getOldFavAccountList() {
		return oldFavAccountList;
	}

	public void setOldFavAccountList(List<ObCorporateAccountOverview> oldFavAccountList) {
		this.oldFavAccountList = oldFavAccountList;
	}

	public String getPrd_cd() {
		return prd_cd;
	}

	public void setPrd_cd(String prd_cd) {
		this.prd_cd = prd_cd;
	}

	 

	 
	
	
	
	
}