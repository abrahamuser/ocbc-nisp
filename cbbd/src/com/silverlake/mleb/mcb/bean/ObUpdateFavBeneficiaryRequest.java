package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.util.List;


public class ObUpdateFavBeneficiaryRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<String> listFavouriteBeneficiary;
	
	private List<String> listUnfavouriteBeneficiary;

	public List<String> getListFavouriteBeneficiary() {
		return listFavouriteBeneficiary;
	}

	public void setListFavouriteBeneficiary(List<String> listFavouriteBeneficiary) {
		this.listFavouriteBeneficiary = listFavouriteBeneficiary;
	}

	public List<String> getListUnfavouriteBeneficiary() {
		return listUnfavouriteBeneficiary;
	}

	public void setListUnfavouriteBeneficiary(List<String> listUnfavouriteBeneficiary) {
		this.listUnfavouriteBeneficiary = listUnfavouriteBeneficiary;
	}
	
	

	
}
