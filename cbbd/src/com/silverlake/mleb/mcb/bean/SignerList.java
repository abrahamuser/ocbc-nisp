package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class SignerList implements Serializable {
	private String user_id;
	private String ktp_no;
	private Integer sequence;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getKtp_no() {
		return ktp_no;
	}
	public void setKtp_no(String ktp_no) {
		this.ktp_no = ktp_no;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	
}
