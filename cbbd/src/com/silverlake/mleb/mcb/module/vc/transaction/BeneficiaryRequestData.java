package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

import java.util.List;

public class BeneficiaryRequestData extends VCRequest{
	private List<String> prod_cd;
	private Integer page_no;
	private Integer page_size;
	private String debit_acct_no;
	private String debit_acct_ccy;

	public List<String> getProd_cd() {
		return prod_cd;
	}

	public void setProd_cd(List<String> prod_cd) {
		this.prod_cd = prod_cd;
	}

	public Integer getPage_no() {
		return page_no;
	}

	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public String getDebit_acct_no() {
		return debit_acct_no;
	}

	public void setDebit_acct_no(String debit_acct_no) {
		this.debit_acct_no = debit_acct_no;
	}

	public String getDebit_acct_ccy() {
		return debit_acct_ccy;
	}

	public void setDebit_acct_ccy(String debit_acct_ccy) {
		this.debit_acct_ccy = debit_acct_ccy;
	}

	
}
