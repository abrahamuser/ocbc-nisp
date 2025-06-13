package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTransactionLimitEqUSDRequest extends ObRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5953582764663654936L;
	
	private String fromCcy;
	private String toCcy;
	private String fromAmount;
	private String toAmount;
	private String accountNo;
	private String cancelBit;
	
	public String getFromCcy() {
		return fromCcy;
	}
	public void setFromCcy(String fromCcy) {
		this.fromCcy = fromCcy;
	}
	public String getToCcy() {
		return toCcy;
	}
	public void setToCcy(String toCcy) {
		this.toCcy = toCcy;
	}
	public String getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(String fromAmount) {
		this.fromAmount = fromAmount;
	}
	public String getToAmount() {
		return toAmount;
	}
	public void setToAmount(String toAmount) {
		this.toAmount = toAmount;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCancelBit() {
		return cancelBit;
	}
	public void setCancelBit(String cancelBit) {
		this.cancelBit = cancelBit;
	}
	
}

