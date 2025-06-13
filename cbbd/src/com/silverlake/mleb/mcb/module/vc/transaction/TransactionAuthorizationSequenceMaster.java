package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

public class TransactionAuthorizationSequenceMaster {
	private String id;
	private Boolean is_seq;
	private String next_auth;
	private List<TransactionAuthorizationSequenceDetail> list_detail;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIs_seq() {
		return is_seq;
	}
	public void setIs_seq(Boolean is_seq) {
		this.is_seq = is_seq;
	}
	public String getNext_auth() {
		return next_auth;
	}
	public void setNext_auth(String next_auth) {
		this.next_auth = next_auth;
	}
	public List<TransactionAuthorizationSequenceDetail> getList_detail() {
		return list_detail;
	}
	public void setList_detail(List<TransactionAuthorizationSequenceDetail> list_detail) {
		this.list_detail = list_detail;
	}
}



	
