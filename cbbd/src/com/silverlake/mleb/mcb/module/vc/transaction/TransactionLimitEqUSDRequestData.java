package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionLimitEqUSDRequestData extends VCRequest {

	private String from_ccy;
	private String to_ccy;
    private String from_amount;
    private String to_amount;
    private String account_no;
    
    public String getFrom_ccy() {
		return from_ccy;
	}
	public void setFrom_ccy(String from_ccy) {
		this.from_ccy = from_ccy;
	}
	public String getTo_ccy() {
		return to_ccy;
	}
	public void setTo_ccy(String to_ccy) {
		this.to_ccy = to_ccy;
	}
	public String getFrom_amount() {
		return from_amount;
	}
	public void setFrom_amount(String from_amount) {
		this.from_amount = from_amount;
	}
	public String getTo_amount() {
		return to_amount;
	}
	public void setTo_amount(String to_amount) {
		this.to_amount = to_amount;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getCancel_bit() {
		return cancel_bit;
	}
	public void setCancel_bit(String cancel_bit) {
		this.cancel_bit = cancel_bit;
	}
	private String cancel_bit;
}
