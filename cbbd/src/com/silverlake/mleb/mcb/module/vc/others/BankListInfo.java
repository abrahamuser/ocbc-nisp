package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class BankListInfo implements Serializable {
	private String bank_code;
    private String bank_name;
    private String branch_name;
    private String address_1;
    private String address_2;
    private String address_3;
    private String country_code;
    private String province_code;
    private String city_code;
    private String city_name;
    private String network_clearing_code;
    private String rtgs_member_code;
    private String participant_bic;
    
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getAddress_3() {
		return address_3;
	}
	public void setAddress_3(String address_3) {
		this.address_3 = address_3;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getNetwork_clearing_code() {
		return network_clearing_code;
	}
	public void setNetwork_clearing_code(String network_clearing_code) {
		this.network_clearing_code = network_clearing_code;
	}
	public String getRtgs_member_code() {
		return rtgs_member_code;
	}
	public void setRtgs_member_code(String rtgs_member_code) {
		this.rtgs_member_code = rtgs_member_code;
	}
	public String getParticipant_bic() {
		return participant_bic;
	}
	public void setParticipant_bic(String participant_bic) {
		this.participant_bic = participant_bic;
	}
    
    
}

