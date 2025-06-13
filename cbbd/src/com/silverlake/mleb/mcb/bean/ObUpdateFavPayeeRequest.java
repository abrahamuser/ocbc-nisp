package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.util.List;


public class ObUpdateFavPayeeRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<String> listFavouritePayee;
	private String billerType;
	
	public List<String> getListFavouritePayee() {
		return listFavouritePayee;
	}

	public void setListFavouritePayee(List<String> listFavouritePayee) {
		this.listFavouritePayee = listFavouritePayee;
	}

	public String getBillerType() {
		return billerType;
	}

	public void setBillerType(String billerType) {
		this.billerType = billerType;
	}
}
