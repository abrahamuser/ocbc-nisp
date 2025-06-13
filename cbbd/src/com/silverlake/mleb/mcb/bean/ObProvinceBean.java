package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Date;

public class ObProvinceBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String provinceCode;
	private String provinceName;
	private String provinceNameLocale1;
	private Date updatedDate;
	
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceNameLocale1() {
		return provinceNameLocale1;
	}
	public void setProvinceNameLocale1(String provinceNameLocale1) {
		this.provinceNameLocale1 = provinceNameLocale1;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
