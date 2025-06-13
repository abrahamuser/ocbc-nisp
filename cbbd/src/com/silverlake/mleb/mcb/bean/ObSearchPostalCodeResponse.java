package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSearchPostalCodeResponse extends ObResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private ObListPostalCodeBean kelurahan;
	private ObListPostalCodeBean kecamatan;
	private ObListPostalCodeBean kabupaten;
	private ObListPostalCodeBean province;
	private String postalCode;
	
	public ObListPostalCodeBean getKelurahan() {
		return kelurahan;
	}
	public void setKelurahan(ObListPostalCodeBean kelurahan) {
		this.kelurahan = kelurahan;
	}
	public ObListPostalCodeBean getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(ObListPostalCodeBean kecamatan) {
		this.kecamatan = kecamatan;
	}
	public ObListPostalCodeBean getKabupaten() {
		return kabupaten;
	}
	public void setKabupaten(ObListPostalCodeBean kabupaten) {
		this.kabupaten = kabupaten;
	}
	public ObListPostalCodeBean getProvince() {
		return province;
	}
	public void setProvince(ObListPostalCodeBean province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	}
