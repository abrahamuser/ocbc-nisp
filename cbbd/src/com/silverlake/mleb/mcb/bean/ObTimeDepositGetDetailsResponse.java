package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTimeDepositGetDetailsResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = -107095875313979424L;
	
	private String currency;
	
	private ListMapPojo listMaturity;
	
	private ListMapPojo listPurpose;	
	
	private ListMapPojo listSourceofFund;
	
	private ListMapPojo listCurrency;
	
	private ListMapPojo listTenor;
	
	private String maxAmount;
	
	private String minAmount;
	
	private List<ObMaturityInstructionBean> maturityInstructionListing; //OCBC2BUIXU-694

	public ListMapPojo getListMaturity() {
		return listMaturity;
	}

	public void setListMaturity(ListMapPojo listMaturity) {
		this.listMaturity = listMaturity;
	}

	public ListMapPojo getListPurpose() {
		return listPurpose;
	}

	public void setListPurpose(ListMapPojo listPurpose) {
		this.listPurpose = listPurpose;
	}

	public ListMapPojo getListSourceofFund() {
		return listSourceofFund;
	}

	public void setListSourceofFund(ListMapPojo listSourceofFund) {
		this.listSourceofFund = listSourceofFund;
	}

	public ListMapPojo getListCurrency() {
		return listCurrency;
	}

	public void setListCurrency(ListMapPojo listCurrency) {
		this.listCurrency = listCurrency;
	}

	public ListMapPojo getListTenor() {
		return listTenor;
	}

	public void setListTenor(ListMapPojo listTenor) {
		this.listTenor = listTenor;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<ObMaturityInstructionBean> getMaturityInstructionListing() {
		return maturityInstructionListing;
	}

	public void setMaturityInstructionListing(
			List<ObMaturityInstructionBean> maturityInstructionListing) {
		this.maturityInstructionListing = maturityInstructionListing;
	}
	
}
