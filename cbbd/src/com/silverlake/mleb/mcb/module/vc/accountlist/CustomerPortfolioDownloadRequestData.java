package com.silverlake.mleb.mcb.module.vc.accountlist;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class CustomerPortfolioDownloadRequestData extends VCRequest {
	
	private String customer_number;
    private String prod_cd;
    private String period_from;
    private String period_to;
    private String device_type;
    private String device_os;
    private CustomerLoanDatav2 summary;
    
	public String getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getPeriod_from() {
		return period_from;
	}
	public void setPeriod_from(String period_from) {
		this.period_from = period_from;
	}
	public String getPeriod_to() {
		return period_to;
	}
	public void setPeriod_to(String period_to) {
		this.period_to = period_to;
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
	public CustomerLoanDatav2 getSummary() {
		return summary;
	}
	public void setSummary(CustomerLoanDatav2 summary) {
		this.summary = summary;
	}
	
	 
}
