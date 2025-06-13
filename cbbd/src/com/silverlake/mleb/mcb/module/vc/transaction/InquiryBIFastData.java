package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class InquiryBIFastData implements Serializable{
	
	private String session_id;
	private String inq_trx_status;
	private String inq_reason_code;
	private String bene_ktp_no;
	private String bene_resident_status;
	private String bene_cif_type;
	private String bene_city_code;
	
	
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getInq_trx_status() {
		return inq_trx_status;
	}
	public void setInq_trx_status(String inq_trx_status) {
		this.inq_trx_status = inq_trx_status;
	}
	public String getInq_reason_code() {
		return inq_reason_code;
	}
	public void setInq_reason_code(String inq_reason_code) {
		this.inq_reason_code = inq_reason_code;
	}
	public String getBene_ktp_no() {
		return bene_ktp_no;
	}
	public void setBene_ktp_no(String bene_ktp_no) {
		this.bene_ktp_no = bene_ktp_no;
	}
	public String getBene_resident_status() {
		return bene_resident_status;
	}
	public void setBene_resident_status(String bene_resident_status) {
		this.bene_resident_status = bene_resident_status;
	}
	public String getBene_cif_type() {
		return bene_cif_type;
	}
	public void setBene_cif_type(String bene_cif_type) {
		this.bene_cif_type = bene_cif_type;
	}
	public String getBene_city_code() {
		return bene_city_code;
	}
	public void setBene_city_code(String bene_city_code) {
		this.bene_city_code = bene_city_code;
	}
	
	

	
}

