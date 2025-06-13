package com.silverlake.mleb.mcb.module.vc.timedeposit;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

import java.util.List;

public class ProductDetailsRequestData extends VCRequest {
    private String acct_no;
    private String acct_ccy;
    
	public String getAcct_no() {
		return acct_no;
	}
	public String getAcct_ccy() {
		return acct_ccy;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public void setAcct_ccy(String acct_ccy) {
		this.acct_ccy = acct_ccy;
	}
}
