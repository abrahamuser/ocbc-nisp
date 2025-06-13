package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class SourceAccountRequestData extends VCRequest{
	private String prod_cd;
	private String ccy_cd;
	private String acct_no;

	public String getProd_cd() {
		return prod_cd;
	}

	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}

	public String getCcy_cd() {
		return ccy_cd;
	}

	public void setCcy_cd(String ccy_cd) {
		this.ccy_cd = ccy_cd;
	}

	public String getAcct_no() {
		return acct_no;
	}

	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
}
