package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObCustAddressBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private String postalCode;
	private String province;
	private String districtCity;
	private String kecamatan;
	private String kelurahan;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrictCity() {
		return districtCity;
	}
	public void setDistrictCity(String districtCity) {
		this.districtCity = districtCity;
	}
	public String getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}
	public String getKelurahan() {
		return kelurahan;
	}
	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}
	
	
}
