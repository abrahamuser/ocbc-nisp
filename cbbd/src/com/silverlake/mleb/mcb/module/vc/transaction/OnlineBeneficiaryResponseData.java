package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.Map;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class OnlineBeneficiaryResponseData extends VCResponseData{
	private Map<String, Object> details;
	private String inq_session_id;
	private String bill_ref_no;
	private String beneficiary_name;
	
	public Map<String, Object> getDetails() {
		return details;
	}
	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}
	public String getInq_session_id() {
		return inq_session_id;
	}
	public void setInq_session_id(String inq_session_id) {
		this.inq_session_id = inq_session_id;
	}
	public String getBill_ref_no() {
		return bill_ref_no;
	}
	public void setBill_ref_no(String bill_ref_no) {
		this.bill_ref_no = bill_ref_no;
	}
	public String getBeneficiary_name() {
		return beneficiary_name;
	}
	public void setBeneficiary_name(String beneficiary_name) {
		this.beneficiary_name = beneficiary_name;
	}
	
	
}
