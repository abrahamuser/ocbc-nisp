package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import com.silverlake.mleb.mcb.module.vc.transaction.TransactionLimitEqUSDDetail;

public class ObTransactionLimitEqUSDResponse extends ObResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3969323301368307547L;
	
	private String is_limit;
	private TransactionLimitEqUSDDetail detail;
	
	public String getIs_limit() {
		return is_limit;
	}
	public void setIs_limit(String is_limit) {
		this.is_limit = is_limit;
	}
	public TransactionLimitEqUSDDetail getDetail() {
		return detail;
	}
	public void setDetail(TransactionLimitEqUSDDetail detail) {
		this.detail = detail;
	}
	
}
