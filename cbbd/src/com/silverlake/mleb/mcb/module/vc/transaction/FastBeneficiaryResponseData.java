package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.Map;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class FastBeneficiaryResponseData extends VCResponseData{
	
	private String bene_name;
	private String bene_acct_no;
	private String bene_acct_ccy;
	private String bene_bank_id;
	private String bene_bank_name;
	private InquiryBIFastData inquiry_bifast_data;
	
	
	public String getBene_name() {
		return bene_name;
	}
	public void setBene_name(String bene_name) {
		this.bene_name = bene_name;
	}
	public String getBene_acct_no() {
		return bene_acct_no;
	}
	public void setBene_acct_no(String bene_acct_no) {
		this.bene_acct_no = bene_acct_no;
	}
	public String getBene_bank_id() {
		return bene_bank_id;
	}
	public void setBene_bank_id(String bene_bank_id) {
		this.bene_bank_id = bene_bank_id;
	}
	public String getBene_bank_name() {
		return bene_bank_name;
	}
	public void setBene_bank_name(String bene_bank_name) {
		this.bene_bank_name = bene_bank_name;
	}
	public InquiryBIFastData getInquiry_bifast_data() {
		return inquiry_bifast_data;
	}
	public void setInquiry_bifast_data(InquiryBIFastData inquiry_bifast_data) {
		this.inquiry_bifast_data = inquiry_bifast_data;
	}
	public String getBene_acct_ccy() {
		return bene_acct_ccy;
	}
	public void setBene_acct_ccy(String bene_acct_ccy) {
		this.bene_acct_ccy = bene_acct_ccy;
	}
	
	
		
	
}
