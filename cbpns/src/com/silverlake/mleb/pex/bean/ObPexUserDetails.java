package com.silverlake.mleb.pex.bean;

import java.io.Serializable;

import com.silverlake.hlb.mib.bean.ObAccountBean;


public class ObPexUserDetails implements Serializable
{
	private String cif;
	private String status;
	private String mobileNumber;
	private ObAccountBean pexAccount;
	private String createDate;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public ObAccountBean getPexAccount() {
		return pexAccount;
	}
	public void setPexAccount(ObAccountBean pexAccount) {
		this.pexAccount = pexAccount;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	
	
	
	
}
