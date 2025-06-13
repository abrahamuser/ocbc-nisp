package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObSearchPostalCodeRequest extends ObRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String province;
	private String kabupaten;
	private String kecamatan;
	private String kelurahan;
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getKabupaten() {
		return kabupaten;
	}
	public void setKabupaten(String kabupaten) {
		this.kabupaten = kabupaten;
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
