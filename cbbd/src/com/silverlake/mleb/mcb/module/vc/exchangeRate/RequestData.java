package com.silverlake.mleb.mcb.module.vc.exchangeRate;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class RequestData extends VCRequest {
	
	private String ccy_code;
	private String period_from;
	private String period_to;
	
	public String getCcy_code() {
		return ccy_code;
	}
	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
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
}