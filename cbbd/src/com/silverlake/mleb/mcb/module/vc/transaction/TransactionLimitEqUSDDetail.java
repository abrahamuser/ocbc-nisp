package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;

public class TransactionLimitEqUSDDetail implements Serializable {

	private String eq_usd_before_trn;
	private String trn_amount_in_usd;
    private String eq_usd_after_trn;
    private String ccy_in_usd;
    private String eq_usd_remaining;
    
	public String getEq_usd_before_trn() {
		return eq_usd_before_trn;
	}
	public void setEq_usd_before_trn(String eq_usd_before_trn) {
		this.eq_usd_before_trn = eq_usd_before_trn;
	}
	public String getTrn_amount_in_usd() {
		return trn_amount_in_usd;
	}
	public void setTrn_amount_in_usd(String trn_amount_in_usd) {
		this.trn_amount_in_usd = trn_amount_in_usd;
	}
	public String getEq_usd_after_trn() {
		return eq_usd_after_trn;
	}
	public void setEq_usd_after_trn(String eq_usd_after_trn) {
		this.eq_usd_after_trn = eq_usd_after_trn;
	}
	public String getCcy_in_usd() {
		return ccy_in_usd;
	}
	public void setCcy_in_usd(String ccy_in_usd) {
		this.ccy_in_usd = ccy_in_usd;
	}
	public String getEq_usd_remaining() {
		return eq_usd_remaining;
	}
	public void setEq_usd_remaining(String eq_usd_remaining) {
		this.eq_usd_remaining = eq_usd_remaining;
	}
    
}
