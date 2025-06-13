package com.silverlake.mleb.mcb.module.vc.accountManagement;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class EstatementDownloadRequestData extends VCRequest {
	
	private String acct_no;
    private String acct_ccy;
    private String periode;
    private String device_type;
    private String device_os;
    
    
	public String getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public String getAcct_ccy() {
		return acct_ccy;
	}
	public void setAcct_ccy(String acct_ccy) {
		this.acct_ccy = acct_ccy;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_os() {
		return device_os;
	}
	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}
    
    
		 
}
