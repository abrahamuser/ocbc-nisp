package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Date;

public class ObBankBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String bankCode;
	private String bankName;
	private String branchName;
	private String provinceCode;
	private String countryCode;
	private String networkCode;
	private String address1;
	private String address2;
	private String address3;
	private String cityName;
	private String cityCode;
	private String bankNameLocale1;
	private String smlCardBankCode;
	private String smlCASABankCode;
	private Date updatedDate;
	private String isSupportCASATrsf;
	private String isIsSupportTrsfFromCASA;
	private String isSupportSML;
	private String rtgsMemberCode;
    private String participantBic;
    
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNameLocale1() {
		return bankNameLocale1;
	}
	public void setBankNameLocale1(String bankNameLocale1) {
		this.bankNameLocale1 = bankNameLocale1;
	}
	public String getSmlCardBankCode() {
		return smlCardBankCode;
	}
	public void setSmlCardBankCode(String smlCardBankCode) {
		this.smlCardBankCode = smlCardBankCode;
	}
	public String getSmlCASABankCode() {
		return smlCASABankCode;
	}
	public void setSmlCASABankCode(String smlCASABankCode) {
		this.smlCASABankCode = smlCASABankCode;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getIsSupportCASATrsf() {
		return isSupportCASATrsf;
	}
	public void setIsSupportCASATrsf(String isSupportCASATrsf) {
		this.isSupportCASATrsf = isSupportCASATrsf;
	}
	public String getIsIsSupportTrsfFromCASA() {
		return isIsSupportTrsfFromCASA;
	}
	public void setIsIsSupportTrsfFromCASA(String isIsSupportTrsfFromCASA) {
		this.isIsSupportTrsfFromCASA = isIsSupportTrsfFromCASA;
	}
	public String getIsSupportSML() {
		return isSupportSML;
	}
	public void setIsSupportSML(String isSupportSML) {
		this.isSupportSML = isSupportSML;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getNetworkCode() {
		return networkCode;
	}
	public void setNetworkCode(String networkCode) {
		this.networkCode = networkCode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getRtgsMemberCode() {
		return rtgsMemberCode;
	}
	public void setRtgsMemberCode(String rtgsMemberCode) {
		this.rtgsMemberCode = rtgsMemberCode;
	}
	public String getParticipantBic() {
		return participantBic;
	}
	public void setParticipantBic(String participantBic) {
		this.participantBic = participantBic;
	}
	
	
}
