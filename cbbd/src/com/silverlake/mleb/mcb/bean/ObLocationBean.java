package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;




public class ObLocationBean implements Serializable
{
	
	private int locId;
	private String locationName;
	private String street;
	private String city;
	private String state;
	private String country;
	private String serviceType;
	private String status;
	private String phoneNo1;
	private String faxNo1;
	private String longtitude;
	private String latitude;
	private String remark;
	private String address; 
	private String distance; 
	private String branchTime; 
	private String bizTime; 
	private String atmTime; 
	private String promo;
	private String serviceType_atm;
	private String serviceType_branch; 
	private String serviceType_ebanking; 
	private String serviceType_bureau; 
	private String serviceType_businessCenter;
	private String serviceType_cashDepoMach; 
	private String serviceType_chequeDepoMach; 
	private String serviceType_hleBroking; 
	private String serviceType_priorityBank; 
	private String serviceType_tradeService; 
	private String serviceType_CreditCard; 
	private String serviceType_DebitCard; 
	private String serviceType_EatFree; 
	private boolean isRadiants;
	private String ebanking_hours;
	private String serviceType_islamic; 
	private String serviceType_mach;
	private String serviceType_global;
	private String serviceType_loan;
	
	

	public String getServiceType_global() {
		return serviceType_global;
	}
	public void setServiceType_global(String serviceType_global) {
		this.serviceType_global = serviceType_global;
	}
	public String getServiceType_loan() {
		return serviceType_loan;
	}
	public void setServiceType_loan(String serviceType_loan) {
		this.serviceType_loan = serviceType_loan;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}	
	public String getPromo() {
		return promo;
	}
	public void setPromo(String promo) {
		this.promo = promo;
	}
	public String getServiceType_atm() {
		return serviceType_atm;
	}
	public void setServiceType_atm(String serviceType_atm) {
		this.serviceType_atm = serviceType_atm;
	}
	public String getServiceType_branch() {
		return serviceType_branch;
	}
	public void setServiceType_branch(String serviceType_branch) {
		this.serviceType_branch = serviceType_branch;
	}
	public String getServiceType_ebanking() {
		return serviceType_ebanking;
	}
	public void setServiceType_ebanking(String serviceType_ebanking) {
		this.serviceType_ebanking = serviceType_ebanking;
	}
	public String getServiceType_bureau() {
		return serviceType_bureau;
	}
	public void setServiceType_bureau(String serviceType_bureau) {
		this.serviceType_bureau = serviceType_bureau;
	}
	public String getServiceType_businessCenter() {
		return serviceType_businessCenter;
	}
	public void setServiceType_businessCenter(String serviceType_businessCenter) {
		this.serviceType_businessCenter = serviceType_businessCenter;
	}
	public String getServiceType_cashDepoMach() {
		return serviceType_cashDepoMach;
	}
	public void setServiceType_cashDepoMach(String serviceType_cashDepoMach) {
		this.serviceType_cashDepoMach = serviceType_cashDepoMach;
	}
	public String getServiceType_chequeDepoMach() {
		return serviceType_chequeDepoMach;
	}
	public void setServiceType_chequeDepoMach(String serviceType_chequeDepoMach) {
		this.serviceType_chequeDepoMach = serviceType_chequeDepoMach;
	}
	public String getServiceType_hleBroking() {
		return serviceType_hleBroking;
	}
	public void setServiceType_hleBroking(String serviceType_hleBroking) {
		this.serviceType_hleBroking = serviceType_hleBroking;
	}
	public String getServiceType_priorityBank() {
		return serviceType_priorityBank;
	}
	public void setServiceType_priorityBank(String serviceType_priorityBank) {
		this.serviceType_priorityBank = serviceType_priorityBank;
	}
	public String getServiceType_tradeService() {
		return serviceType_tradeService;
	}
	public void setServiceType_tradeService(String serviceType_tradeService) {
		this.serviceType_tradeService = serviceType_tradeService;
	}
	public String getServiceType_CreditCard() {
		return serviceType_CreditCard;
	}
	public void setServiceType_CreditCard(String serviceType_CreditCard) {
		this.serviceType_CreditCard = serviceType_CreditCard;
	}
	public String getServiceType_DebitCard() {
		return serviceType_DebitCard;
	}
	public void setServiceType_DebitCard(String serviceType_DebitCard) {
		this.serviceType_DebitCard = serviceType_DebitCard;
	}
	public String getServiceType_EatFree() {
		return serviceType_EatFree;
	}
	public void setServiceType_EatFree(String serviceType_EatFree) {
		this.serviceType_EatFree = serviceType_EatFree;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhoneNo1() {
		return phoneNo1;
	}
	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}
	public String getFaxNo1() {
		return faxNo1;
	}
	public void setFaxNo1(String faxNo1) {
		this.faxNo1 = faxNo1;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getBranchTime() {
		return branchTime;
	}
	public void setBranchTime(String branchTime) {
		this.branchTime = branchTime;
	}
	public String getBizTime() {
		return bizTime;
	}
	public void setBizTime(String bizTime) {
		this.bizTime = bizTime;
	}
	public String getAtmTime() {
		return atmTime;
	}
	public void setAtmTime(String atmTime) {
		this.atmTime = atmTime;
	}
	public boolean isRadiants() {
		return isRadiants;
	}
	public void setRadiants(boolean isRadiants) {
		this.isRadiants = isRadiants;
	}
	public String getEbanking_hours() {
		return ebanking_hours;
	}
	public void setEbanking_hours(String ebanking_hours) {
		this.ebanking_hours = ebanking_hours;
	}
	public String getServiceType_islamic() {
		return serviceType_islamic;
	}
	public void setServiceType_islamic(String serviceType_islamic) {
		this.serviceType_islamic = serviceType_islamic;
	}
	public String getServiceType_mach() {
		return serviceType_mach;
	}
	public void setServiceType_mach(String serviceType_mach) {
		this.serviceType_mach = serviceType_mach;
	}

	
}